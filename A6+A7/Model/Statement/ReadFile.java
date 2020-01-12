package Model.Statement;

import Model.Expression.Expression;
import Model.Type.stringType;
import Model.Type.type;
import Model.Value.intValue;
import Model.Value.stringValue;
import Model.Value.value;
import Model.Containers.iDictionary;
import Model.Containers.iHeap;
import Model.Containers.myException;
import Model.programState;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private Expression expression;
    private String variableName;

    public ReadFile(Expression expr, String name) {
        expression = expr;
        variableName = name;
    }

    @Override
    public iDictionary<String, type> typecheck(iDictionary<String, type> typeEnvironment) throws myException {
        type expressionType = expression.typecheck(typeEnvironment);
        type variableType = typeEnvironment.get(variableName);
        if (variableType == null)
            throw new myException("ReadFile: variable was not declared!");
        if (expressionType.equals(new stringType()))
        {
            return typeEnvironment;
        }
        else throw new myException("ReadFile: expression is not a stringType!");
    }

    @Override
    public programState execute(programState program) throws myException {
        iDictionary<String, value> symTable = program.getSymbolsTable();
        iDictionary<stringValue, BufferedReader> fileTable = program.getFileTable();
        iHeap<value> heapTable = program.getHeapTable();
        value val = symTable.get(variableName);

        if (val == null)
            throw new myException("Variable " + variableName + " was not declared!");
        value exprValue = expression.eval(symTable, heapTable);

        if (exprValue.getType().equals(new stringType())){
            stringValue file = (stringValue)exprValue;
            if (fileTable.exists(file)){
                BufferedReader reader = fileTable.get(file);
                try {
                    String line = reader.readLine();
                    intValue variableValue;
                    if (line == null){
                        variableValue = new intValue(0);
                    }
                    else
                        variableValue = new intValue(Integer.parseInt(line));
                    symTable.put(variableName, variableValue);
                }
                catch (IOException ex) {
                    throw new myException(ex.getMessage());
                }
            }
            else throw new myException("File does not exist!");
        }
        else throw new myException("Expresion must be a stringValue");
        return null;
    }

    @Override
    public String toString() { return "readFile(file: " + expression + ", " + variableName + ");"; }
}
