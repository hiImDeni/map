package Model.Statement;

import Model.*;
import Model.Expression.Expression;
import Model.Type.boolType;
import Model.Value.boolValue;
import Model.Value.value;

public class IfStatement implements IStatement {
    Expression exp;
    IStatement thenStatement, elseStatement;

    public IfStatement(Expression ex, IStatement i, IStatement e){
        exp = ex;
        thenStatement = i;
        elseStatement = e;
    }

    @Override
    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenStatement.toString() + ") ELSE(" + elseStatement.toString() + ")";
    }

    @Override
    public programState execute(programState state) throws myException {
        iDictionary<String , value> sym = state.getSymbolsTable();
        iStack<IStatement> exe = state.getExecutionStack();
        iHeap<value> heap = state.getHeapTable();
        value res = exp.eval(sym, heap);
        if (res.getType().equals(new boolType())){
            boolValue bres = (boolValue) res;
            boolean bool = bres.getValue();
            if (bool == true)
                exe.push(thenStatement);
            else
                exe.push(elseStatement);
        }
        else
            throw new myException("If statement must contain a boolValue!");
        return null;
    }
}
