package Model.Statement;

import Model.*;
import Model.Expression.Expression;
import Model.Type.boolType;
import Model.Value.boolValue;
import Model.Value.value;

public class WhileStatement implements IStatement {
    Expression expression;
    IStatement statement;
    public WhileStatement(Expression exp, IStatement stmt) { expression = exp; statement = stmt; }

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
                return program;
            }
            else {
                exeStack.push(this);
                exeStack.push(statement);
                return program;
            }
        }
        else throw new myException("Expression " + expression + " must be a boolValue!");
        //return program;
    }

    @Override
    public String toString() { return "while (" + expression + ") do " + statement; }
}
