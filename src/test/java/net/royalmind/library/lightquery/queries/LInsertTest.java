package net.royalmind.library.lightquery.queries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LInsertTest {

    @Test
    void queryFormat() {
        final String query = new LInsert().table("TBL_TEST").values(1, true, "test", 5.5d, 5.1f, 15.15).getQuery();
        assertEquals("INSERT INTO TBL_TEST VALUES (1, true, 'test', 5.5, 5.1, 15.15);", query);
    }
}