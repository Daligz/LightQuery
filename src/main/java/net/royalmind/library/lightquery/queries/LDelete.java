package net.royalmind.library.lightquery.queries;

import net.royalmind.library.lightquery.exceptions.EmptyValueException;

public class LDelete implements Query, DeleteQueryBuilder<LDelete> {

    public static final String FORMAT_VALUE = "DELETE FROM %s WHERE %s = %s;";
    public static final String FROM_EXCEPTION = "LDelete builder";

    private String from, where;

    @Override
    public LDelete from(final String from) {
        this.from = from;
        return this;
    }

    @Override
    public LDelete where(final String where) {
        this.where = where;
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
        if (this.where != null && !(this.where.isEmpty())) {
            lQuery
                    .append(" WHERE ")
                    .append(this.where);
        }
        return lQuery
                .append(";")
                .toString();
    }
}
