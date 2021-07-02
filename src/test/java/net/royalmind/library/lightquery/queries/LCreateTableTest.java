package net.royalmind.library.lightquery.queries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LCreateTableTest {

    @Test
    void create() {
        final String result = "CREATE TABLE IF NOT EXISTS %s (id INT(4) NOT NULL AUTO_INCREMENT,value VARCHAR(5) NOT NULL, PRIMARY KEY(id), FOREIGN KEY (id) REFERENCES table(id));";
        final String lQuery = new LCreateTable("TABLE => id INT:4 -> NN AI, value STR:5 -> NN, PK:id, FK:id:table.id").getQuery();
        assertEquals(result, lQuery);
    }

    @Test
    void withDecimal() {
        final String result = "CREATE TABLE IF NOT EXISTS %s (id INT(4) NOT NULL AUTO_INCREMENT,value DATE NOT NULL,test DOUBLE(5,2) NOT NULL, PRIMARY KEY(id));";
        final String lQuery = new LCreateTable("TABLE => id INT:4 -> NN AI, value DAT -> NN, test DOU:5.2 -> NN, PK:id").getQuery();
        assertEquals(result, lQuery);
    }

}