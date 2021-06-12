package net.royalmind.library.lightquery.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    /**
     * Table name
     *
     * @return {@link String}
     */
    String name() default "TableName";

    /**
     * Create table if not exists
     *
     * @return @{@link Boolean}
     */
    boolean create() default false;

    /**
     * Force drop table if exist and create table
     *
     * @return @{@link Boolean}
     */
    boolean force() default false;
}

