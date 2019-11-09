package Model;

public class variableExpression implements Expression {
    String id;

    public variableExpression(String var) { id = var; }

    @Override
    public Value eval(iDictionary<String, Value> table) throws MyException{
        return table.get(id);
    }

    @Override
    public String toString() { return id; }
}
