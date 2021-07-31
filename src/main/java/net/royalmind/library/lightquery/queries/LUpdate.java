package net.royalmind.library.lightquery.queries;

import net.royalmind.library.lightquery.exceptions.EmptyValueException;
import net.royalmind.library.lightquery.interpreters.ValueInterpreter;
import net.royalmind.library.lightquery.queries.objects.Where;

import java.util.HashMap;
import java.util.Map;

public class LUpdate implements Query, UpdateQueryBuilder<LUpdate> {

    public static final String FORMAT_VALUE = "UPDATE %S SET %s = %s, %s = %s;";
    public static final String FROM_EXCEPTION = "LUpdate builder";

    private String table;
    private Where where;
    private boolean interpret = true;
    private Map<String, Object> updates = new HashMap<>();

    @Override
    public LUpdate table(final String table) {
        this.table = table;
        return this;
    }

    @Override
    public LUpdate update(final String column, final Object value) {
        this.updates.put(column, value);
        return this;
    }

    @Override
    public LUpdate where(final String column, final String operation, final Object value) {
        this.where = new Where(column, operation, value);
        return this;
    }

    @Override
    public LUpdate and(final String column, final String operation, final Object value) {
        this.where.and(column, operation, value);
        return this;
    }

    @Override
    public LUpdate or(final String column, final String operation, final Object value) {
        this.where.or(column, operation, value);
        return this;
    }

    @Override
    public LUpdate notInterpret() {
        this.interpret = false;
        return this;
    }

    @Override
    public String getQuery() {
        if (this.table == null || this.table.isEmpty()) {
            throw new EmptyValueException("table", FROM_EXCEPTION);
        } else if (this.updates == null || this.updates.size() <= 0) {
            throw new EmptyValueException("updates values", FROM_EXCEPTION);
        }
        final StringBuilder lQuery = new StringBuilder(
                String.format("UPDATE %s SET ", this.table)
        );
        this.updates.forEach((s, o) -> {
            lQuery
                    .append(s)
                    .append(" = ");
            lQuery.append(
                    (this.interpret) ? ValueInterpreter.interpretSQL(o) : o
            );
            lQuery
                    .append(",");
        });
        if (this.where != null) {
            lQuery.deleteCharAt(lQuery.toString().length() - 1);
            lQuery
                    .append(" WHERE ")
                    .append(this.where.toQuery());
        } else {
            lQuery.deleteCharAt(lQuery.toString().length() - 1);
        }
        return lQuery
                .append(";")
                .toString();
    }
}

