package Model.Statement;

import Model.Expression.Expression;
import Model.Type.stringType;
import Model.Value.stringValue;
import Model.Value.value;
import Model.iDictionary;
import Model.iStack;
import Model.myException;
import Model.programState;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStatement {
    Expression exp;

    public OpenRFile(Expression e) { exp = e; }

    @Override
    public programState execute(programState program) throws myException {
        iStack<IStatement> executionStack = program.getExecutionStack();
        iDictionary<String, value> symTable = program.getSymbolsTable();
        iDictionary<stringValue, BufferedReader> fileTable = program.getFileTable();
        //iStatement current = executionStack.pop();
        value val = exp.eval(symTable);
        if (val.getType().equals(new stringType())){
            stringValue string = (stringValue)val;
            if (symTable.exists(string.getValue()))
                throw new myException("File already exists!");
            File file = new File(string.getValue());
            if (file.exists()){
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(string.getValue()));
                    fileTable.put(string, reader);
                }
                catch (IOException ex){
                    throw new myException(ex.getMessage());
                }
            }
            else throw new myException("File " +  string.getValue() + " doesn't exist!");
        }
        else{
            throw new myException("Expression must be a string value!");
        }
        return program;
    }

    @Override
    public String toString() { return "openRFile(" + exp + ");"; }
}
