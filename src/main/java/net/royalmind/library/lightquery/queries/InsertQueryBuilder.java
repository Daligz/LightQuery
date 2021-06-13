package net.royalmind.library.lightquery.queries;

public interface InsertQueryBuilder<T> {
    T table(final String table);
    T values(final Object... values);
}
