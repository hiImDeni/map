package Model.Expression;

import Model.*;
import Model.Type.intType;
import Model.Value.boolValue;
import Model.Value.intValue;
import Model.Value.value;

public class RelationalExpression implements Expression {
    Expression exp1, exp2;
    String operator;

    public RelationalExpression(Expression e1, Expression e2, String op) { exp1 = e1; exp2 = e2; operator = op; }

    @Override
    public value eval(iDictionary<String, value> table) throws myException {
        value v1, v2;
        v1 = exp1.eval(table);
        if(v1.getType().equals(new intType())){
            v2 = exp2.eval(table);
            if(v1.getType().equals(new intType())){
                intValue i1 = (intValue) v1;
                intValue i2 = (intValue) v2;
                int n1 = i1.getValue();
                int n2 = i2.getValue();
                switch (operator){
                    case "<":
                        return new boolValue(n1 < n2);
                    case "<=":
                        return new boolValue(n1 <= n2);
                    case "==":
                        return new boolValue(n1 == n2);
                    case "!=":
                        return new boolValue(n1 != n2);
                    case ">":
                        return new boolValue(n1 > n2);
                    case ">=":
                        return new boolValue(n1 >= n2);
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
}
