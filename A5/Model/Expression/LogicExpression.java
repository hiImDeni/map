package Model.Expression;

import Model.*;
import Model.Type.boolType;
import Model.Value.boolValue;
import Model.Value.value;

public class LogicExpression implements Expression {
    Expression e1, e2;
    char operator; //1 - and, 2 - or, 3 - xor ...and that's it?

    public LogicExpression(Expression ex1, Expression ex2, char o) {
        e1 = ex1;
        e2 = ex2;
        operator = o;
    }

    @Override
    public value eval(iDictionary<String, value> table, iHeap<value> heap) throws myException {
        value v1, v2;
        v1 = e1.eval(table, heap);
        if (v1.getType().equals(new boolType())) {
            v2 = e2.eval(table, heap);
            if (v2.getType().equals(new boolType())) {
                boolValue i1 = (boolValue) v1;
                boolValue i2 = (boolValue) v2;
                boolean b1 = i1.getValue();
                boolean b2 = i2.getValue();

                switch (operator) {
                    case '&':
                        return new boolValue(b1 && b2);
                    case '|':
                        return new boolValue(b1 || b2);
                    case '^':
                        return new boolValue(b1 ^ b2);
                }
            } else
                throw new myException("Second operadn is not a boolean!");
        } else
            throw new myException("First operand is not a boolean!");
        return v1;
    }

    @Override
    public String toString() { return e1.toString() + operator + e2.toString(); }
}
