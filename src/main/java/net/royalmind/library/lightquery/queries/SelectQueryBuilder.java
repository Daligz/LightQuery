package net.royalmind.library.lightquery.queries;

public interface SelectQueryBuilder<T> {
    T value(final String value);
    T from(final String from);
    T where(final String where);
}
