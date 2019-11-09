package Model;

public class VariableDeclarationStatement implements iStatement {
    String name;
    Type type;
    public VariableDeclarationStatement(String n, Type t) { name = n; type = t; }

    @Override
    public String toString() { return type.toString() + " " + name; }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        iDictionary<String, Value> sym = state.getSymbolsTable();
        if (sym.exists(name))
            throw new MyException("Variable " + name + " already declared");
        else{
            sym.put(name, type.defaultValue());
        }
        return state;
    }
}
