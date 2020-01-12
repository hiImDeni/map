package Model;

import Model.Containers.*;
import Model.Statement.IStatement;
import Model.Value.stringValue;
import Model.Value.value;

import java.io.BufferedReader;

public class programState {
    static int id = 0;
    int currentId;
    iStack<IStatement> executionStack;
    iDictionary<String, value> symbolsTable;
    iList<String> output;
    iDictionary<stringValue, BufferedReader> fileTable;
    iHeap<value> heapTable;
    IStatement originalProgram; //optional field, exists for some reason

    public programState(iStack<IStatement> exe, iDictionary<String, value> sym, iList<String> out, iDictionary<stringValue, BufferedReader> files, iHeap<value> heap){
        executionStack = exe;
        symbolsTable = sym;
        output = out;
        fileTable = files;
        heapTable = heap;
        id++;
        currentId = id;
        //originalProgram = program; //TO DO: deep copy
        //originalProgram = (iStatement) program.clone();

        //exe.push(program);
    }

    public iStack<IStatement> getExecutionStack() { return executionStack; }
    public iDictionary<String, value> getSymbolsTable() { return symbolsTable; }
    public iList<String> getOutput() { return output; }
    public iDictionary<stringValue, BufferedReader> getFileTable() { return fileTable; }
    public iHeap<value> getHeapTable() { return heapTable; }


    public void setExecutionStack(iStack st) { executionStack = st; }
    public void setSymbolsTable(iDictionary<String, value> dict) { symbolsTable = dict; }
    public void setOutput(iList<String> l) { output = l; }

    public void addStatement(IStatement st){
        executionStack.push(st);
    }

    public boolean isNotCompleted() { return !executionStack.empty(); }

    public int getIndex() { return currentId; }

    public programState oneStep() throws myException {
        try{
            IStatement currentStatement = executionStack.pop();
            return currentStatement.execute(this);
        }
        catch (Exception ex) {
            throw new myException(ex.getMessage());
        }
    }

    public static void newId() { id++; }

    @Override
    public String toString(){
        return "Program " + currentId + "\nEXECUTION STACK:\n" + executionStack.toString()  + "\nSYMBOLS TABLE:\n" + symbolsTable.toString() +
                "\nOUTPUT:\n" +  output.toString() + "\nFILE TABLE:\n" + fileTable.toString() +
                "\nHEAP TABLE\n" + heapTable.toString() + "\n";
    }
}
