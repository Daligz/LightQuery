package net.royalmind.library.lightquery.interpreters.table;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TableValuesTypes {
    PROP("->", ""), ID_VALUE(":", ":"), NN("NN", "NOT NULL"),
    AI("AI", "AUTO_INCREMENT"), PK("PK", "PRIMARY KEY(%s)"), EMPTY(" ", " ");
    private final String value;
    private final String defSyntax;
}
