package net.royalmind.library.lightquery.queries;

public interface DeleteQueryBuilder<T> {
    T from(final String from);
    T where(final String column, final String operation, final Object value);
}
