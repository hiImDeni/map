package Model.Statement;

import Model.Expression.Expression;
import Model.Type.refType;
import Model.Type.type;
import Model.Value.refValue;
import Model.Value.value;
import Model.iDictionary;
import Model.iHeap;
import Model.myException;
import Model.programState;

public class New implements IStatement {
    String variableName;
    Expression expression;
    public New(String name, Expression exp) { variableName = name; expression = exp; }

    @Override
    public programState execute(programState program) throws myException {
        iDictionary<String, value> symTable = program.getSymbolsTable();
        iHeap<value> heapTable = program.getHeapTable();
        if (symTable.exists(variableName)){
            value val = symTable.get(variableName);
            if (val.getType() instanceof refType){
                refValue reference = (refValue)val;
                value expVal = expression.eval(symTable, heapTable);
                refType referenceType = (refType) reference.getType();
                type locationType = referenceType.getInner();
                if (expVal.getType().equals(locationType)){
                    //TODO: generate new free address -> key = heapTable.getNext() ?
                    heapTable.put(expVal);
                    int key = heapTable.getNextFree();
                    symTable.put(variableName, new refValue(locationType, key));
                }
                else throw new myException("Types of " + expression + " and " + variableName + " do not match!");
            }
            else
                throw new myException("Variable " + variableName + " must be a refType");
        }
        else
            throw new myException("The variable " + variableName + " was not declared!");
        return null;
    }

    @Override
    public String toString() { return "new(" + variableName + ", " + expression + ");"; }
}
