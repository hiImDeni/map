package Model;

import javax.swing.*;

public class logicExpression implements Expression{
    Expression e1, e2;
    char operator; //1 - and, 2 - or, 3 - xor ...and that's it?

    public logicExpression(Expression ex1, Expression ex2, char o) {
        e1 = ex1;
        e2 = ex2;
        operator = o;
    }

    @Override
    public  Value eval(iDictionary<String, Value> table) throws MyException {
        Value v1, v2;
        v1 = e1.eval(table);
        if (v1.getType().equals(new boolType())) {
            v2 = e2.eval(table);
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
                throw new MyException("Second operadn is not a boolean!");
        } else
            throw new MyException("First operand is not a boolean!");
        return v1;
    }

    @Override
    public String toString() { return e1.toString() + operator + e2.toString(); }
}
