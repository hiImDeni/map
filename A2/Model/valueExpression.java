package Model;

public class valueExpression implements Expression {
    Value e;

    public valueExpression(Value val) { e = val; }
    @Override
    public Value eval(iDictionary<String, Value> table) throws MyException { return e; }

    @Override
    public String toString() { return e.toString(); }
}
