package Model;

public class arithmeticExpression implements Expression {
    Expression e1, e2;
    char operator;

    public arithmeticExpression(char o, Expression ex1, Expression ex2) {
        operator = o;
        e1 = ex1;
        e2 = ex2;
    }

    public Value eval(iDictionary<String, Value> table) throws MyException{
        Value v1, v2;
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
                            throw new MyException("Division by zero");
                        }
                        return new intValue(n1 / n2);
                }
            }
            else{
               throw new MyException("Second operand is not an integer");
            }
        }
        else{
            throw new MyException("First operand is not an integer");
        }
        return v1;
    }

    public String toString() { return e1.toString() + operator + e2.toString(); }
}
