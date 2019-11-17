package Model.Expression;

import Model.iDictionary;
import Model.myException;
import Model.Value.value;

public class ValueExpression implements Expression {
    value e;

    public ValueExpression(value val) { e = val; }
    @Override
    public value eval(iDictionary<String, value> table) throws myException { return e; }

    @Override
    public String toString() { return e.toString(); }
}
