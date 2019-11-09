package Model;

public class NOPStatement implements iStatement {
    @Override
    public String toString() { return "nop;"; }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        return state;
    }
}
