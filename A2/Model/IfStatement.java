package Model;

public class IfStatement implements iStatement {
    Expression exp;
    iStatement thenStatement, elseStatement;

    public IfStatement(Expression ex, iStatement i, iStatement e){
        exp = ex;
        thenStatement = i;
        elseStatement = e;
    }

    @Override
    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenStatement.toString() + ") ELSE(" + elseStatement.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException{
        iDictionary<String , Value> sym = state.getSymbolsTable();
        iStack<iStatement> exe = state.getExecutionStack();
        Value res = exp.eval(sym);
        if (res.getType().equals(new boolType())){
            boolValue bres = (boolValue) res;
            boolean bool = bres.getValue();
            if (bool == true)
                exe.push(thenStatement);
            else
                exe.push(elseStatement);
        }
        else
            throw new MyException("If statement must contain a boolValue!");
        return state;
    }
}
