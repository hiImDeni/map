package Model.Statement;

import Model.*;
import Model.Containers.*;
import Model.Type.type;
import Model.Value.value;

import java.util.Map;

public class ForkStatement implements IStatement {
    IStatement statement;
    public ForkStatement(IStatement stmt) { statement = stmt; }

    @Override
    public iDictionary<String, type> typecheck(iDictionary<String, type> typeEnvironment) throws myException {
        iDictionary<String, type> cloneEnvironment = new myDictionary<String, type>();
        for (Map.Entry<String, type> var: typeEnvironment.getContent().entrySet())
            cloneEnvironment.put(var.getKey(), var.getValue());
        statement.typecheck(cloneEnvironment); //?
        return typeEnvironment;
    }

    @Override
    public programState execute(programState program){
        iStack<IStatement> forkExecution = new myStack<IStatement>();
        iDictionary<String, value> cloneSym = new myDictionary<String,value>();

        forkExecution.push(statement);
        for (Map.Entry<String,value> var: program.getSymbolsTable().getContent().entrySet())
            cloneSym.put(var.getKey(), var.getValue());

        programState forkProgram = new programState(forkExecution, cloneSym, program.getOutput(), program.getFileTable(), program.getHeapTable());

        return forkProgram;
    }

    @Override
    public String toString() { return "fork(" + statement + ");"; }
}
