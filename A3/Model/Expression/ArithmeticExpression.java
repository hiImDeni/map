package Model.Expression;

import Model.*;
import Model.Type.intType;
import Model.Value.intValue;
import Model.Value.value;

public class ArithmeticExpression implements Expression {
    Expression e1, e2;
    char operator;

    public ArithmeticExpression(char o, Expression ex1, Expression ex2) {
        operator = o;
        e1 = ex1;
        e2 = ex2;
    }

    public value eval(iDictionary<String, value> table) throws myException {
        value v1, v2;
        //System.out.println(e1 + " " + e2);
        v1 = e1.eval(table);
        if (v1.getType().equals(new intType())){
            v2 = e2.eval(table);
            if (v2.getType().equals(new intType())) {
                intValue i1 = (intValue) v1; //cast
                intValue i2 = (intValue) v2;
                int n1 = i1.getValue();
                int n2 = i2.getValue();
                switch (operator) {
                    case '+':
                        return new intValue(n1 + n2);
                    case '-':
                        return new intValue(n1 - n2);
                    case '*':
                        return new intValue(n1 * n2);
                    case '/':
                        if (n2 == 0) {
                            throw new myException("Division by zero");
                        }
                        return new intValue(n1 / n2);
                    default:
                        throw new myException("Incorrect operand");
                }
            }
            else{
               throw new myException("Second operand is not an integer");
            }
        }
        else{
            throw new myException("First operand is not an integer");
        }
    }

    public String toString() { return e1.toString() + operator + e2.toString(); }
}
