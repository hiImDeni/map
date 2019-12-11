package Model.Statement;

import Model.*;
import Model.Value.value;

import java.util.Map;

public class ForkStatement implements IStatement {
    IStatement statement;
    public ForkStatement(IStatement stmt) { statement = stmt; }

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
