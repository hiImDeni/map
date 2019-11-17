package Model.Expression;

import Model.iDictionary;
import Model.myException;
import Model.Value.value;

public class VariableExpression implements Expression {
    String id;

    public VariableExpression(String var) { id = var; }

    @Override
    public value eval(iDictionary<String, value> table) throws myException {
        return table.get(id);
    }

    @Override
    public String toString() { return id; }
}
