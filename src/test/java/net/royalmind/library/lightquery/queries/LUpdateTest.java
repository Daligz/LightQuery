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
                .update("test_last_seen", new Date(System.currentTimeMillis()).toLocalDate().getYear())
                .getQuery();
        assertEquals("UPDATE TBL_TEST SET test_money = 2500.25,test_last_seen = 2021,test_age = 25,test_name = 'NewName';", query);
    }

    @Test
    void whereQuery() {
        final String query = new LUpdate()
                .table("TBL_TEST")
                .update("test_name", "NewName")
                .update("test_age", 25)
                .update("test_money", 2500.25)
                .update("test_last_seen", new Date(System.currentTimeMillis()).toLocalDate().getYear())
                .where("1", "=", 1)
                .getQuery();
        assertEquals("UPDATE TBL_TEST SET test_money = 2500.25,test_last_seen = 2021,test_age = 25,test_name = 'NewName' WHERE 1 = 1;", query);
    }

    @Test
    void notInterpret() {
        final String query = new LUpdate()
                .table("TBL_TEST")
                .update("count", "count + 1")
                .where("id", "=", 2)
                .notInterpret()
                .getQuery();
        assertEquals("UPDATE TBL_TEST SET count = count + 1 WHERE id = 2;", query);
    }
}