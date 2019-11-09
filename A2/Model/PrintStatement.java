package Model;

public class PrintStatement implements iStatement {
    Expression exp;

    public PrintStatement(Expression e) { exp = e; }

    @Override
    public String toString() { return "print(" + exp.toString() + ")" + ";"; }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{ //TODO
        iList<String> out = state.getOutput();
        out.add(exp.toString());
        return state;
    }
}
