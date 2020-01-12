package Model.Statement;

import Model.Expression.Expression;
import Model.Type.type;
import Model.Containers.iDictionary;
import Model.Containers.iList;
import Model.Containers.myException;
import Model.programState;

public class PrintStatement implements IStatement {
    Expression exp;

    public PrintStatement(Expression e) { exp = e; }

    @Override
    public String toString() { return "print(" + exp.toString() + ")" + ";"; }

    public iDictionary<String, type> typecheck(iDictionary<String, type> typeEnvironment) throws myException {
        exp.typecheck(typeEnvironment);
        return typeEnvironment;
    }

    @Override
    public programState execute(programState state) throws myException { //TODO
        iList<String> out = state.getOutput();
        out.add(exp.eval(state.getSymbolsTable(), state.getHeapTable()).toString());
        return null;
    }
}
