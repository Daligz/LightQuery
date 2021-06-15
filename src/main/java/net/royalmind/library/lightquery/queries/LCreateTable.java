package net.royalmind.library.lightquery.queries;

import lombok.AllArgsConstructor;
import net.royalmind.library.lightquery.interpreters.QueryInterpreter;

@AllArgsConstructor
public class LCreateTable implements Query {

    /**
     * You need to use LightQuery syntax
     */
    private final String lQuery;

    @Override
    public String getQuery() {
        return QueryInterpreter.interpret(this.lQuery);
    }
}
