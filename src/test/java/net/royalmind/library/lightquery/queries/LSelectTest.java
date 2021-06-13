package net.royalmind.library.lightquery.queries;

import net.royalmind.library.lightquery.exceptions.EmptyValueException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LSelectTest {

    @Test
    void emptyValueException() {
        assertThrows(EmptyValueException.class, new Executable() {
            @Override
            public void execute() throws EmptyValueException {
                final String tbl_test = new LSelect().from("TBL_TEST").where("id = test").getQuery();
                System.out.println(tbl_test);
            }
        });

        assertThrows(EmptyValueException.class, new Executable() {
            @Override
            public void execute() throws EmptyValueException {
                final String tbl_test = new LSelect().value("").from("").where("id = test").getQuery();
                System.out.println(tbl_test);
            }
        });
    }

    @Test
    void queryFormat() {
        final String tbl_test = new LSelect().value("*").from("TBL_TEST").getQuery();
        assertEquals("SELECT * FROM TBL_TEST;", tbl_test);
    }

    @Test
    void queryFormatWithWhere() {
        final String tbl_test = new LSelect().value("*").from("TBL_TEST").where("id = test").getQuery();
        assertEquals("SELECT * FROM TBL_TEST WHERE id = test;", tbl_test);
    }
}