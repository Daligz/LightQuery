package net.royalmind.library.lightquery.interpreters;

import lombok.experimental.UtilityClass;
import net.royalmind.library.lightquery.interpreters.table.TableValuesTypes;

@UtilityClass
public class QueryInterpreter {

    public static final String TABLE_DATA_TYPE_FORMAT = "%s %s(%s)";
    public static final String TABLE_DATA_TYPE_FORMAT_SPEC = "%s %s";

    /**
     *
     * Examples:
     * Default query: "id INT(4) NOT NULL AUTO_INCREMENT, value VARCHAR(5) NOT NULL, PRIMARY KEY(id)"
     * lQuery: "TABLE => id INT:4 -> NN AI, value STRING:5 -> NN, PK:id"
     *
     */
    public String interpret(final String query) {
        for (final QueryValuesTypes value : QueryValuesTypes.values()) {
            if (query.trim().startsWith(value.getLQSyntax())) {
                if (value == QueryValuesTypes.TABLE) return table(query);
            }
        }
        return "";
    }

    // TABLE => id INT:4 -> NN AI, value STRING:5 -> NN, PK:id
    private String table(final String query) {
        final QueryValuesTypes table = QueryValuesTypes.TABLE;
        String slQuery = query.replace(table.getLQSyntax(), "").trim();
        // %s is the table name
        final StringBuilder lQuery = new StringBuilder(table.getDefSyntax() + " %s (");

        final String[] split = slQuery.split(",");

        lQuery.append(checkV(split));

        return lQuery.substring(0, lQuery.toString().length() - 1).concat(");");
    }

    private String checkV(final String[] split) {
        final StringBuilder lQuery = new StringBuilder();

        // Default values
        final String idValue = TableValuesTypes.ID_VALUE.getValue();
        final String pkValue = TableValuesTypes.PK.getValue();
        final String emptyValue = TableValuesTypes.EMPTY.getValue();

        for (final String s : split) {
            final String[] subSplit = s.trim().split(emptyValue);
            boolean haveProps = false;
            for (int j = 1; j < subSplit.length; j++) {
                final String cStrKey = subSplit[0];
                final String cStr = subSplit[j].toUpperCase();
                if (haveProps) {
                    final String defSyntax = TableValuesTypes.valueOf(cStr).getDefSyntax();
                    lQuery.append(" ").append(defSyntax);
                } else {
                    if (cStr.contains(idValue) && !(cStr.contains(pkValue))) {
                        final String[] sStrs = cStr.split(idValue);
                        if (sStrs.length > 1) {
                            final ObjectInterpreter objectInterpreter = ObjectInterpreter.valueOf(sStrs[0].toUpperCase());
                            final String interpret = ValueInterpreter.Interpret(objectInterpreter);
                            lQuery.append(String.format(TABLE_DATA_TYPE_FORMAT, cStrKey, interpret, sStrs[1]));
                        }
                    } else if (cStr.contains(ObjectInterpreter.DAT.toString()) || cStr.contains(ObjectInterpreter.BOO.toString())) {
                        final ObjectInterpreter objectInterpreter = ObjectInterpreter.valueOf(cStr.toUpperCase());
                        final String interpret = ValueInterpreter.Interpret(objectInterpreter);
                        lQuery.append(String.format(TABLE_DATA_TYPE_FORMAT_SPEC, cStrKey, interpret));
                    } else if (cStr.contains(TableValuesTypes.PROP.getValue())) { haveProps = true; }
                }
            }
            if (s.contains(pkValue)) {
                final String primaryValue = s.replace(pkValue.concat(idValue), emptyValue).trim();
                lQuery.append(" ").append(String.format(TableValuesTypes.PK.getDefSyntax(), primaryValue));
            }
            lQuery.append(",");
        }
        return lQuery.toString();
    }
}
