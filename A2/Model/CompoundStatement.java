package Model;

public class CompoundStatement implements iStatement{
    iStatement first, second;

    public CompoundStatement(iStatement st1, iStatement st2){
        first = st1;
        second = st2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        iStack<iStatement> mySt = state.getExecutionStack();
        mySt.push(second);
        mySt.push(first);
        return state;
    }

    @Override
    public String toString(){
        return first.toString() + "; " + second.toString();
    }
}
