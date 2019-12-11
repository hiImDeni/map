package Model.Statement;

import Model.Expression.Expression;
import Model.iList;
import Model.myException;
import Model.programState;

public class PrintStatement implements IStatement {
    Expression exp;

    public PrintStatement(Expression e) { exp = e; }

    @Override
    public String toString() { return "print(" + exp.toString() + ")" + ";"; }

    @Override
    public programState execute(programState state) throws myException { //TODO
        iList<String> out = state.getOutput();
        out.add(exp.eval(state.getSymbolsTable(), state.getHeapTable()).toString());
        return null;
    }
}
