package net.royalmind.library.lightquery.queries;

import net.royalmind.library.lightquery.exceptions.EmptyValueException;
import net.royalmind.library.lightquery.interpreters.ValueInterpreter;

public class LInsert implements Query, InsertQueryBuilder<LInsert> {

    public static final String FORMAT_VALUE = "INSERT INTO %s VALUES (%s);";
    public static final String FROM_EXCEPTION = "LInsert builder";

    private String table;
    private Object[] values;

    @Override
    public LInsert table(final String table) {
        this.table = table;
        return this;
    }

    @Override
    public LInsert values(final Object... values) {
        this.values = values;
        return this;
    }

    @Override
    public String getQuery() {
        if (this.table == null || this.table.isEmpty()) {
            throw new EmptyValueException("table", FROM_EXCEPTION);
        } else if (this.values == null || this.values.length <= 0) {
            throw new EmptyValueException("values", FROM_EXCEPTION);
        }
        final StringBuilder lQuery = new StringBuilder(
                String.format("INSERT INTO %s VALUES (", this.table)
        );

        for (final Object value : this.values) {
            lQuery.append(ValueInterpreter.interpretSQL(value)).append(", ");
        }

        return lQuery
                .substring(0, lQuery.toString().length() - 2)
                .concat(");");
    }
}
