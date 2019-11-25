package Model.Statement;

import Model.*;
import Model.Expression.Expression;
import Model.Type.type;
import Model.Value.value;

public class AssignStatement implements IStatement {
    String id;
    Expression exp;

    public AssignStatement(String name, Expression e) { id = name; exp = e; }

    @Override
    public String toString() { return id + "=" + exp.toString() + ";"; }

    @Override
    public programState execute(programState state) throws myException {
        iStack<IStatement> st = state.getExecutionStack();
        iDictionary<String, value> sym = state.getSymbolsTable();
        iHeap<value> heap = state.getHeapTable();
        value val = exp.eval(sym, heap);
        if (sym.exists(id)){
            type typeId = sym.get(id).getType();
            if (val.getType().equals(typeId))
                sym.put(id, val);
            else
                throw new myException("Declared type of variable " + id + " and type of the assigned expression do not match!");
        }
        else
            throw new myException("The variable " + id + " was not declared!");
        return state;
    }
}
