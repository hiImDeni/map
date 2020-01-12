package Model.Statement;

import Model.Type.type;
import Model.Containers.iDictionary;
import Model.Containers.myException;
import Model.programState;

public class NOPStatement implements IStatement {
    @Override
    public String toString() { return "nop;"; }

    @Override
    public iDictionary<String, type> typecheck(iDictionary<String, type> typeEnvironment) throws myException {
        return typeEnvironment;
    }

    @Override
    public programState execute(programState state) throws myException {
        return null;
    }
}
