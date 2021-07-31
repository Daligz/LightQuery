package net.royalmind.library.lightquery.queries;

import net.royalmind.library.lightquery.exceptions.EmptyValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LSelectTest {

    @Test
    void emptyValueException() {
        assertThrows(EmptyValueException.class, () -> {
            final String tbl_test = new LSelect().from("TBL_TEST").where("id", "=", "test").getQuery();
            System.out.println(tbl_test);
        });

        assertThrows(EmptyValueException.class, () -> {
            final String tbl_test = new LSelect().value("").from("").where("id", "=", "test").getQuery();
            System.out.println(tbl_test);
        });
    }

    @Test
    void queryFormat() {
        final String tbl_test = new LSelect().value("*").from("TBL_TEST").getQuery();
        assertEquals("SELECT * FROM TBL_TEST;", tbl_test);
    }

    @Test
    void queryFormatWithWhere() {
        final String tbl_test = new LSelect().value("*").from("TBL_TEST").where("id", "=", "test").getQuery();
        assertEquals("SELECT * FROM TBL_TEST WHERE id = 'test';", tbl_test);
    }

    @Test
    void and() {
        final String tbl_test = new LSelect().value("*").from("TBL_TEST").where("id", "=", "test")
                .and("1", "=", 1).getQuery();
        assertEquals("SELECT * FROM TBL_TEST WHERE id = 'test' AND 1 = 1;", tbl_test);
    }

    @Test
    void or() {
        final String tbl_test = new LSelect().value("*").from("TBL_TEST").where("id", "=", "test")
                .or("playerName", "=", "Player1").getQuery();
        assertEquals("SELECT * FROM TBL_TEST WHERE id = 'test' OR playerName = 'Player1';", tbl_test);
    }

    @Test
    void andOr() {
        final String tbl_test = new LSelect().value("*").from("TBL_TEST").where("id", "=", "test")
                .and("id2", ">=", 5).or("playerName", "=", "Player1").getQuery();
        assertEquals("SELECT * FROM TBL_TEST WHERE id = 'test' AND id2 >= 5 OR playerName = 'Player1';", tbl_test);
    }

    @Test
    void andMultiple() {
        final String tbl_test = new LSelect().value("*").from("TBL_TEST").where("id", "=", "test")
                .and("id2", "=", 1).and("id3", "=", 2)
                .and("id4", "=", 3).getQuery();
        assertEquals("SELECT * FROM TBL_TEST WHERE id = 'test' AND id2 = 1 AND id3 = 2 AND id4 = 3;", tbl_test);
    }

    @Test
    void orMultiple() {
        final String tbl_test = new LSelect().value("*").from("TBL_TEST").where("id", "=", "test")
                .or("id2", "=", 1).or("id3", "=", 2)
                .or("id4", "=", 3).getQuery();
        assertEquals("SELECT * FROM TBL_TEST WHERE id = 'test' OR id2 = 1 OR id3 = 2 OR id4 = 3;", tbl_test);
    }

    @Test
    void innerJoin() {
        final String tbl_test = new LSelect()
                .value("*")
                .from("TBL_TEST")
                .where("id", "=", "test")
                .join("TBL_TEST2", "TBL_TEST.id", "=", "TBL_TEST_2.id")
                .getQuery();
        assertEquals("SELECT * FROM TBL_TEST INNER JOIN TBL_TEST2 ON TBL_TEST.id = TBL_TEST_2.id WHERE id = 'test';", tbl_test);
    }
}