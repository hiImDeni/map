package Model;

public class AssignStatement implements iStatement {
    String id;
    Expression exp;

    public AssignStatement(String name, Expression e) { id = name; exp = e; }

    @Override
    public String toString() { return id + "=" + exp.toString() + ";"; }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        iStack<iStatement> st = state.getExecutionStack();
        iDictionary<String, Value> sym = state.getSymbolsTable();
        Value val = exp.eval(sym);
        if (sym.exists(id)){
            Type typeId = sym.get(id).getType();
            if (val.getType().equals(typeId))
                sym.put(id, val);
            else
                throw new MyException("Declared type of variable " + id + " and type of teh assigned expression do not match!");
        }
        else
            throw new MyException("The variable " + id + " was not declared!");
        return state;
    }
}
