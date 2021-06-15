package net.royalmind.library.lightquery.queries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LDeleteTest {

    @Test
    void testQuery() {
        final String tbl_test = new LDelete().from("TBL_TEST").getQuery();
        assertEquals("DELETE FROM TBL_TEST;", tbl_test);
    }

    @Test
    void whereQuery() {
        final String tbl_test = new LDelete().from("TBL_TEST").where("1", "=", 1).getQuery();
        assertEquals("DELETE FROM TBL_TEST WHERE 1 = 1;", tbl_test);
    }
}