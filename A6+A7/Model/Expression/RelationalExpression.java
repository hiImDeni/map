package Model.Expression;

import Model.Containers.iDictionary;
import Model.Containers.iHeap;
import Model.Containers.myException;
import Model.Type.boolType;
import Model.Type.intType;
import Model.Type.type;
import Model.Value.boolValue;
import Model.Value.intValue;
import Model.Value.value;

public class RelationalExpression implements Expression {
    Expression exp1, exp2;
    String operator;

    public RelationalExpression(Expression e1, Expression e2, String op) { exp1 = e1; exp2 = e2; operator = op; }

    @Override
    public type typecheck(iDictionary<String, type> typeEnvironment) throws myException {
        type type1, type2;
        type1 = exp1.typecheck(typeEnvironment);
        type2 = exp2.typecheck(typeEnvironment);
        if (type1.equals(new intType())) {
            if (type2.equals(new intType())) {
                return new boolType(); //type of relational expression is bool
            } else throw new myException("Second operand is not an integer!");
        }
        else throw new myException("First operand is not an integer!");
    }

    @Override
    public value eval(iDictionary<String, value> table, iHeap<value> heap) throws myException {
        value v1, v2;
        v1 = exp1.eval(table, heap);
        if(v1.getType().equals(new intType())){
            v2 = exp2.eval(table, heap);
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

    @Override
    public String toString() { return exp1 + operator  + exp2; }
}
