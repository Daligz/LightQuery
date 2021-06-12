package net.royalmind.library.lightquery.interpreters;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QueryValuesTypes {
    TABLE("TABLE =>", "CREATE TABLE");
    private final String lQSyntax;
    private final String defSyntax;
}
