package net.royalmind.library.lightquery.queries;

public interface SelectQueryBuilder<T> {
    T value(final String value);
    T from(final String from);
    T where(final String column, final String operation, final Object value);
    T and(final String column, final String operation, final Object value);
    T or(final String column, final String operation, final Object value);
    T join(final String table, final String column, final String operation, final String value);
}
