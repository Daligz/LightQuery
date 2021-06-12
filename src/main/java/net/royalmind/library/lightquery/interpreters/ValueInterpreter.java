package net.royalmind.library.lightquery.interpreters;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValueInterpreter {

    public String Interpret(final ObjectInterpreter objectInterpreter) {
        final String type;
        switch (objectInterpreter) {
            case STR:
                type = "VARCHAR";
                break;
            case INT:
                type = "INT";
                break;
            case BIT:
                type = "BIT";
                break;
            case BOO:
                type = "BOOLEAN";
                break;
            case CHA:
                type = "CHAR";
                break;
            case DAT:
                type = "DATE";
                break;
            case DEC:
                type = "DECIMAL";
                break;
            case DOU:
                type = "DOUBLE";
                break;
            case FLO:
                type = "FLOAT";
                break;
            default:
                type = "";
        }
        return type;
    }

    public ObjectInterpreter Interpret(final String value) {
        return ObjectInterpreter.valueOf(value);
    }
}
