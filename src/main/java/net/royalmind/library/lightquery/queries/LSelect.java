package net.royalmind.library.lightquery.queries;

import net.royalmind.library.lightquery.exceptions.EmptyValueException;

public class LSelect implements Query, SelectQueryBuilder<LSelect> {

    public static final String FORMAT_VALUE = "SELECT %s FROM %s WHERE %s;";
    public static final String FROM_EXCEPTION = "LSelect builder";

    private String value, from, where;

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
    public LSelect where(final String where) {
        this.where = where;
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
