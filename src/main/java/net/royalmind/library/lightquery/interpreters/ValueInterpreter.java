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
                type = "null";
        }
        return type;
    }

    public ObjectInterpreter Interpret(final String value) {
        return ObjectInterpreter.valueOf(value);
    }

    public String interpretSQL(final Object object) {
        if (object == null) return "null";
        final String name = object.getClass().getSimpleName();
        final String type;
        switch (name.toLowerCase()) {
            case "string":
                type = String.format("'%s'", object);
                break;
            case "integer":
            case "double":
            case "float":
            case "boolean":
                type = String.valueOf(object);
                break;
            case "date":
                type = object.toString();
                break;
            default:
                type = "null";
        }
        return type;
    }
}
