package Model.Statement;

import Model.Type.type;
import Model.Containers.iDictionary;
import Model.Containers.iStack;
import Model.Containers.myException;
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
        return null;
    }

    @Override
    public iDictionary<String, type> typecheck(iDictionary<String, type> typeEnvironment) throws myException { //?
        //iDictionary<String, type> firstTypeEnvironment = first.typecheck(typeEnvironment);
        //iDictionary<String, type> secondTypeEnvironment = second.typecheck(typeEnvironment);
        return second.typecheck(first.typecheck(typeEnvironment));
    }

    @Override
    public String toString(){
        return first.toString() + " " + second.toString();
    }
}
