package net.royalmind.library.lightquery.queries.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Join {

    private String table, first_value, operation, second_value;

    public String toQuery() {
        return this.table
                .concat(" ON ")
                .concat(this.first_value)
                .concat(" ")
                .concat(this.operation)
                .concat(" ")
                .concat(this.second_value);
    }
}
