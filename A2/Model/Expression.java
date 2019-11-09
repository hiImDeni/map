package Model;

public interface Expression {
    Value eval(iDictionary<String, Value> table) throws MyException;
}
