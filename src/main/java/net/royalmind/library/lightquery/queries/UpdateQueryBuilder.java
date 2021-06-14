package net.royalmind.library.lightquery.queries;

public interface UpdateQueryBuilder<T> {
    T table(final String table);
    T update(final String column, final Object value);
    T where(final String where);
}
