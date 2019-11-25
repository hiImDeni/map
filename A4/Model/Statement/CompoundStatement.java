package Model.Statement;

import Model.iStack;
import Model.myException;
import Model.programState;

public class CompoundStatement implements IStatement {
    IStatement first, second;

    public CompoundStatement(IStatement st1, IStatement st2){
        first = st1;
        second = st2;
    }

    @Override
    public programState execute(programState state) throws myException {
        iStack<IStatement> mySt = state.getExecutionStack();
        mySt.push(second);
        mySt.push(first);
        return state;
    }

    @Override
    public String toString(){
        return first.toString() + "; " + second.toString();
    }
}
