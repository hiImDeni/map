package Model;

import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class ProgramState {
    iStack<iStatement> executionStack;
    iDictionary<String, Value> symbolsTable;
    iList<String> output;
    iStatement originalProgram; //optional field, exists for some reason

    public ProgramState(iStack<iStatement> exe, iDictionary<String, Value> sym, iList<String> out, iStatement program){
        executionStack = exe;
        symbolsTable = sym;
        output = out;
        originalProgram = program; //TO DO: deep copy
        exe.push(program);
    }

    public ProgramState(iStack<iStatement> exe, iDictionary<String, Value> sym, iList<String> out) {
        executionStack = exe;
        symbolsTable = sym;
        output = out;
    }

    public iStack<iStatement> getExecutionStack() { return executionStack; }
    public iDictionary<String, Value> getSymbolsTable() { return symbolsTable; }
    public iList<String> getOutput() { return output; }

    public void setExecutionStack(iStack st) { executionStack = st; }
    public void setSymbolsTable(iDictionary<String, Value> dict) { symbolsTable = dict; }
    public void setOutput(iList<String> l) { output = l; }

    public void addStatement(iStatement st){
        executionStack.push(st);
    }

    public iStatement deleteStatement() throws Exception{
        iStatement st = executionStack.pop();
        return st;
    }

    @Override
    public String toString(){
        return "Execution Stack:\n" + executionStack.toString()  + "\nSymbols table:\n" + symbolsTable.toString() + "\nOutput:\n" +  output.toString();
    }
}
