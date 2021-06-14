package net.royalmind.library.lightquery.queries;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LUpdateTest {

    @Test
    void testFormat() {
        final String query = new LUpdate()
                .table("TBL_TEST")
                .update("test_name", "NewName")
                .update("test_age", 25)
                .update("test_money", 2500.25)
                .update("test_last_seen", new Date(System.currentTimeMillis()))
                .getQuery();
        assertEquals("UPDATE TBL_TEST SET test_money = 2500.25,test_last_seen = 2021-06-14,test_age = 25,test_name = 'NewName;", query);
    }
}