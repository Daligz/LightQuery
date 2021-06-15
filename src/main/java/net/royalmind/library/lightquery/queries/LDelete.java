package net.royalmind.library.lightquery.queries;

import net.royalmind.library.lightquery.exceptions.EmptyValueException;
import net.royalmind.library.lightquery.queries.objects.Where;

public class LDelete implements Query, DeleteQueryBuilder<LDelete> {

    public static final String FORMAT_VALUE = "DELETE FROM %s WHERE %s = %s;";
    public static final String FROM_EXCEPTION = "LDelete builder";

    private String from;
    private Where where;

    @Override
    public LDelete from(final String from) {
        this.from = from;
        return this;
    }

    @Override
    public LDelete where(final String column, final String operation, final Object value) {
        this.where = new Where(column, operation, value);
        return this;
    }

    @Override
    public String getQuery() {
        if (this.from == null || this.from.isEmpty()) {
            throw new EmptyValueException("from", FROM_EXCEPTION);
        }
        final StringBuilder lQuery = new StringBuilder(
                String.format("DELETE FROM %s", this.from)
        );
        if (this.where != null) {
            lQuery
                    .append(" WHERE ")
                    .append(this.where.toQuery());
        }
        return lQuery
                .append(";")
                .toString();
    }
}
