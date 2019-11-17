package Model;

import Model.Statement.IStatement;
import Model.Value.stringValue;
import Model.Value.value;

import java.io.BufferedReader;

public class programState {
    iStack<IStatement> executionStack;
    iDictionary<String, value> symbolsTable;
    iList<String> output;
    iDictionary<stringValue, BufferedReader> fileTable;
    IStatement originalProgram; //optional field, exists for some reason

    public programState(iStack<IStatement> exe, iDictionary<String, value> sym, iList<String> out, iDictionary<stringValue, BufferedReader> files){
        executionStack = exe;
        symbolsTable = sym;
        output = out;
        fileTable = files;
        //originalProgram = program; //TO DO: deep copy
        //originalProgram = (iStatement) program.clone();

        //exe.push(program);
    }

    /*public programState(iStack<IStatement> exe, iDictionary<String, value> sym, iList<String> out) {
        executionStack = exe;
        symbolsTable = sym;
        output = out;
    }*/

    public iStack<IStatement> getExecutionStack() { return executionStack; }
    public iDictionary<String, value> getSymbolsTable() { return symbolsTable; }
    public iList<String> getOutput() { return output; }
    public iDictionary<stringValue, BufferedReader> getFileTable() { return fileTable; }

    public void setExecutionStack(iStack st) { executionStack = st; }
    public void setSymbolsTable(iDictionary<String, value> dict) { symbolsTable = dict; }
    public void setOutput(iList<String> l) { output = l; }

    public void addStatement(IStatement st){
        executionStack.push(st);
    }

    public IStatement deleteStatement() throws Exception{
        IStatement st = executionStack.pop();
        return st;
    }

    @Override
    public String toString(){
        return "EXECUTION STACK:\n" + executionStack.toString()  + "\nSYMBOLS TABLE:\n" + symbolsTable.toString() +
                "\nOUTPUT:\n" +  output.toString() + "\nFILE TABLE:\n" + fileTable.toString() + "\n";
    }
}
