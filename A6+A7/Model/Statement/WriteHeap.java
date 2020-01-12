package Model.Statement;

import Model.Expression.Expression;
import Model.Type.refType;
import Model.Type.type;
import Model.Value.refValue;
import Model.Value.value;
import Model.Containers.iDictionary;
import Model.Containers.iHeap;
import Model.Containers.myException;
import Model.programState;

public class WriteHeap implements IStatement {
    String variableName;
    Expression expression;
    public WriteHeap(String name, Expression exp) { variableName = name; expression = exp; }

    @Override
    public iDictionary<String, type> typecheck(iDictionary<String, type> typeEnvironment) throws myException {
        type expressionType = expression.typecheck(typeEnvironment);
        type variableType = typeEnvironment.get(variableName);
        if (variableType.equals(new refType(expressionType)))
            return typeEnvironment;
        else
            throw new myException("WriteHeap: right hand side and left hand side have different types!");
    }

    @Override
    public programState execute(programState program) throws myException {
        iDictionary<String, value> symTable = program.getSymbolsTable();
        iHeap<value> heapTable = program.getHeapTable();

        if (symTable.exists(variableName)){
            value variableValue = symTable.get(variableName);
            if (variableValue.getType() instanceof refType){
                refValue variableReference = (refValue)variableValue;
                int address = variableReference.getAddress();
                if (heapTable.exists(address)){
                    value expressionValue = expression.eval(symTable, heapTable);
                    refType locationType = (refType)variableReference.getType();
                    //t.getInner().equals(val.getType()
                    if (locationType.getInner().equals(expressionValue.getType())){
                        heapTable.update(address, expressionValue);
                    }
                    else throw new myException(expressionValue + " and " + locationType + " do not match!");
                }
                else throw new myException("Address " + address + " does not exist!");
            }
            else throw new myException("Variable " + variableName + " is not a refType!");
        }
        else throw new myException("Variable " + variableName + " was not declared!");
        return null;
    }

    @Override
    public String toString() { return "WriteHeap(" + variableName + ", " + expression + ");"; }
}
