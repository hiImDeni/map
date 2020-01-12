package Model.Expression;

import Model.Type.type;
import Model.Containers.iDictionary;
import Model.Containers.iHeap;
import Model.Containers.myException;
import Model.Value.value;

public class ValueExpression implements Expression {
    value e;

    public ValueExpression(value val) { e = val; }
    @Override
    public value eval(iDictionary<String, value> table, iHeap<value> heap) throws myException { return e; }

    @Override
    public type typecheck(iDictionary<String, type> myEnvironment) { return e.getType(); }

    @Override
    public String toString() { return e.toString(); }
}
