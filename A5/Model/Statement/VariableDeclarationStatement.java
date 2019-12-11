package Model.Statement;

import Model.Value.value;
import Model.iDictionary;
import Model.myException;
import Model.programState;

public class VariableDeclarationStatement implements IStatement {
    String name;
    Model.Type.type type;
    public VariableDeclarationStatement(String n, Model.Type.type t) { name = n; type = t; }

    @Override
    public String toString() { return type.toString() + " " + name; }

    @Override
    public programState execute(programState state) throws myException {
        iDictionary<String, value> sym = state.getSymbolsTable();
        if (sym.exists(name))
            throw new myException("Variable " + name + " already declared");
        else{
            sym.put(name, type.defaultValue());
        }
        return null;
    }
}
