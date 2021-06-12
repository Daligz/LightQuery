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
    String name();

    /**
     * Create table if not exists
     *
     * @return @{@link Boolean}
     */
    boolean create();

    /**
     * Force drop table if exist and create table
     *
     * @return @{@link Boolean}
     */
    boolean clear() default false;

    /**
     * Set table types values using lQuery syntax
     *
     * Examples:
     * Default query: "id INT(4) NOT NULL AUTO_INCREMENT, value VARCHAR(5) NOT NULL, PRIMARY KEY(id)"
     * lQuery: "id INT:4 -> NN AI, value STRING:5 -> NN, PK:id"
     *
     * @return @{@link String}
     */
    String values() default "TABLE => id INT:4 -> NN AI, value STR:5 -> NN, PK:id";
}

