package Model.Statement;

import Model.Expression.Expression;
import Model.Type.stringType;
import Model.Value.stringValue;
import Model.Value.value;
import Model.iDictionary;
import Model.myException;
import Model.programState;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement {
    Expression expression;
    public CloseRFile(Expression expr) { expression = expr; }

    @Override
    public programState execute(programState program) throws myException {
        iDictionary<String, value> symTable = program.getSymbolsTable();
        iDictionary<stringValue, BufferedReader> fileTable = program.getFileTable();

        value val = expression.eval(symTable);
        if (val.getType().equals(new stringType())){
            stringValue file = (stringValue)val;
            if (fileTable.exists(file)){
                BufferedReader reader = fileTable.get(file);
                try {
                    reader.close();
                }
                catch (IOException ex){
                    throw new myException(ex.getMessage());
                }
                fileTable.remove(file);
            }
            else throw new myException("File " + file + " does not exist!");
        }
        else throw new myException("Expression must be a string!");

        return program;
    }

    @Override
    public String toString() { return "closeRFile(" + expression + ");"; }
}
