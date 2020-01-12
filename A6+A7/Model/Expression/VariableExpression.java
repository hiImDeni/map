package Model.Expression;

import Model.Type.type;
import Model.Containers.iDictionary;
import Model.Containers.iHeap;
import Model.Containers.myException;
import Model.Value.value;

public class VariableExpression implements Expression {
    String id;

    public VariableExpression(String var) { id = var; }

    @Override
    public value eval(iDictionary<String, value> table, iHeap<value> heap) throws myException {
        return table.get(id);
    }

    @Override
    public type typecheck(iDictionary<String, type> typeEnvironment) { return typeEnvironment.get(id); }

    @Override
    public String toString() { return id; }
}
