package Model.Statement;

import Model.myException;
import Model.programState;

public class NOPStatement implements IStatement {
    @Override
    public String toString() { return "nop;"; }

    @Override
    public programState execute(programState state) throws myException {
        return state;
    }
}
