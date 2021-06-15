package net.royalmind.library.lightquery.queries;

import net.royalmind.library.lightquery.exceptions.EmptyValueException;
import net.royalmind.library.lightquery.queries.objects.Where;

public class LSelect implements Query, SelectQueryBuilder<LSelect> {

    public static final String FORMAT_VALUE = "SELECT %s FROM %s WHERE %s;";
    public static final String FROM_EXCEPTION = "LSelect builder";

    private String value, from;
    private Where where;

    @Override
    public LSelect value(final String value) {
        this.value = value;
        return this;
    }

    @Override
    public LSelect from(final String from) {
        this.from = from;
        return this;
    }

    @Override
    public LSelect where(final String column, final String operation, final Object value) {
        this.where = new Where(column, operation, value);
        return this;
    }

    @Override
    public String getQuery() {
        if (this.value == null || this.value.isEmpty()) {
            throw new EmptyValueException("value", FROM_EXCEPTION);
        } else if (this.from == null || this.from.isEmpty()) {
            throw new EmptyValueException("from", FROM_EXCEPTION);
        }
        final StringBuilder lQuery = new StringBuilder(
                String.format("SELECT %s FROM %s", this.value, this.from)
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
