package net.royalmind.library.lightquery.queries;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LDeleteTable implements Query {

    private String table;

    @Override
    public String getQuery() {
        return "DROP TABLE "
                .concat(this.table);
    }
}
