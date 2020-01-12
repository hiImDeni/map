package Model.Statement;

import Model.Expression.Expression;
import Model.Type.stringType;
import Model.Type.type;
import Model.Value.stringValue;
import Model.Value.value;
import Model.Containers.iDictionary;
import Model.Containers.iHeap;
import Model.Containers.myException;
import Model.programState;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement {
    Expression expression;
    public CloseRFile(Expression expr) { expression = expr; }

    @Override
    public iDictionary<String, type> typecheck(iDictionary<String, type> typeEnvironment) throws myException {
        type expressionType = expression.typecheck(typeEnvironment);
        if (expressionType.equals(new stringType()))
            return typeEnvironment;
        throw new myException("CloseRFile: expression is not a stringType!");
    }

    @Override
    public programState execute(programState program) throws myException {
        iDictionary<String, value> symTable = program.getSymbolsTable();
        iDictionary<stringValue, BufferedReader> fileTable = program.getFileTable();
        iHeap<value> heap = program.getHeapTable();
        value val = expression.eval(symTable, heap);
        if (val.getType().equals(new stringType())){
            stringValue file = (stringValue)val;
            if (fileTable.exists(file)){
                BufferedReader reader = fileTable.get(file);
                try {
                    reader.close();
                    fileTable.remove(file);
                }
                catch (IOException ex){
                    throw new myException(ex.getMessage());
                }
            }
            else throw new myException("File " + file + " does not exist!");
        }
        else throw new myException("Expression must be a string!");

        return null;
    }

    @Override
    public String toString() { return "closeRFile(" + expression + ");"; }
}
