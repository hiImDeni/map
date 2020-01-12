package Model.Statement;

import Model.Type.type;
import Model.Value.value;
import Model.Containers.iDictionary;
import Model.Containers.myException;
import Model.programState;

public class VariableDeclarationStatement implements IStatement {
    String name;
    Model.Type.type variableType;
    public VariableDeclarationStatement(String n, Model.Type.type t) { name = n; variableType = t; }

    @Override
    public String toString() { return variableType.toString() + " " + name + ";"; }

    public iDictionary<String, Model.Type.type> typecheck(iDictionary<String, type> typeEnvironment) throws myException {
        typeEnvironment.put(name, variableType);
        return typeEnvironment;
    }

    @Override
    public programState execute(programState state) throws myException {
        iDictionary<String, value> sym = state.getSymbolsTable();
        if (sym.exists(name))
            throw new myException("Variable " + name + " already declared");
        else{
            sym.put(name, variableType.defaultValue());
        }
        return null;
    }
}
