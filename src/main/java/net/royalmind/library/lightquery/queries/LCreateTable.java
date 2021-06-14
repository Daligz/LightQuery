package net.royalmind.library.lightquery.queries;

import net.royalmind.library.lightquery.interpreters.QueryInterpreter;

public class LCreateTable implements Query {

    private String lQuery;

    /**
     * You need to use LightQuery syntax
     *
     * @param lQuery @{@link String}
     */
    public LCreateTable(final String lQuery) {
        this.lQuery = lQuery;
    }

    @Override
    public String getQuery() {
        return QueryInterpreter.interpret(this.lQuery);
    }
}
