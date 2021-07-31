package net.royalmind.library.lightquery.queries.objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.royalmind.library.lightquery.interpreters.ValueInterpreter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Where {

    private final String first_value, operation;
    private final Object second_value;

    private final List<And> ands = new ArrayList<>();
    private final List<Or> ors = new ArrayList<>();

    @Getter @Setter
    @AllArgsConstructor
    public static class And {
        private final String first_value, operation;
        private final Object second_value;

        public String toQuery() {
            return " AND "
                    .concat(this.first_value)
                    .concat(" ")
                    .concat(this.operation)
                    .concat(" ")
                    .concat(ValueInterpreter.interpretSQL(this.second_value));
        }
    }

    @Getter @Setter
    @AllArgsConstructor
    public static class Or {
        private final String first_value, operation;
        private final Object second_value;

        public String toQuery() {
            return " OR "
                    .concat(this.first_value)
                    .concat(" ")
                    .concat(this.operation)
                    .concat(" ")
                    .concat(ValueInterpreter.interpretSQL(this.second_value));
        }
    }

    public Where(final String first_value, final String operation, final Object second_value) {
        this.first_value = first_value;
        this.operation = operation;
        this.second_value = second_value;
    }

    public void and(final String first_value, final String operation, final Object second_value) {
        this.ands.add(
                new And(first_value, operation, second_value)
        );
    }

    public void or(final String first_value, final String operation, final Object second_value) {
        this.ors.add(
                new Or(first_value, operation, second_value)
        );
    }

    public String toQuery() {
        final StringBuilder strBuilder = new StringBuilder(
                this.first_value
                        .concat(" ")
                        .concat(this.operation)
                        .concat(" ")
                        .concat(ValueInterpreter.interpretSQL(this.second_value))
        );
        for (final And and : this.ands) {
            strBuilder.append(and.toQuery());
        }
        for (final Or or : this.ors) {
            strBuilder.append(or.toQuery());
        }
        return strBuilder.toString();
    }
}
