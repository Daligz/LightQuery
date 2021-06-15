package net.royalmind.library.lightquery.queries.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.royalmind.library.lightquery.interpreters.ValueInterpreter;

/**
 * POJO Class
 */
@Getter @Setter
@AllArgsConstructor
public class Where {

    private String first_value, operation;
    private Object second_value;

    public String toQuery() {
        return this.first_value
                .concat(" ")
                .concat(this.operation)
                .concat(" ")
                .concat(ValueInterpreter.interpretSQL(this.second_value));
    }
}
