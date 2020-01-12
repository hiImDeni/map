package Model.Statement;

import Model.*;
import Model.Containers.*;
import Model.Expression.Expression;
import Model.Type.boolType;
import Model.Type.type;
import Model.Value.boolValue;
import Model.Value.value;

import java.util.Map;

public class WhileStatement implements IStatement {
    Expression expression;
    IStatement statement;
    public WhileStatement(Expression exp, IStatement stmt) { expression = exp; statement = stmt; }

    @Override
    public iDictionary<String, type> typecheck(iDictionary<String, type> typeEnvironment) throws myException {
        type expressionType = expression.typecheck(typeEnvironment);
        if (expressionType.equals(new boolType()))
        {
            iDictionary<String, type> cloneEnvironment = new myDictionary<String, type>();
            for (Map.Entry<String, type> var: typeEnvironment.getContent().entrySet())
                cloneEnvironment.put(var.getKey(), var.getValue());
            statement.typecheck(cloneEnvironment);
            return typeEnvironment;
        }
        else throw new myException("The condition int the whileStatement is not bool!");
    }

    @Override
    public programState execute(programState program) throws myException {
        iDictionary<String, value> symTable = program.getSymbolsTable();
        iHeap<value> heapTable = program.getHeapTable();
        iStack<IStatement> exeStack = program.getExecutionStack();

        value expressionValue = expression.eval(symTable, heapTable);
        if (expressionValue.getType().equals(new boolType())){
            boolValue boolVal = (boolValue)expressionValue;
            if (!boolVal.getValue()){
                //exeStack.pop();
                return null;
            }
            else {
                exeStack.push(this);
                exeStack.push(statement);
                return null;
            }
        }
        else throw new myException("Expression " + expression + " must be a boolValue!");
        //return program;
    }

    @Override
    public String toString() { return "while (" + expression + ") do " + statement; }
}
