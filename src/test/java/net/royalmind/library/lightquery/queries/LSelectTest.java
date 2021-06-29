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