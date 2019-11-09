package Repository;

import Model.*;

public class Repo implements iRepo {
    iList<ProgramState> states;
    //int index;

    private void initialize(){
        iStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new intType()), new CompoundStatement(new AssignStatement("v", new valueExpression(new intValue(2))), new PrintStatement(new variableExpression("v"))));

        iStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new intType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new intType()),
                        new CompoundStatement(new AssignStatement("a", new arithmeticExpression('+',
                                new valueExpression(new intValue(2)), new arithmeticExpression('*',
                                new valueExpression(new intValue(3)), new valueExpression(new intValue(5))))),
                                new CompoundStatement(new AssignStatement("b", new arithmeticExpression('+',
                                        new variableExpression("a"), new valueExpression(new intValue(1)))), new PrintStatement(new variableExpression("b"))))));

        iStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new boolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new intType()),
                        new CompoundStatement(new AssignStatement("a", new valueExpression(new boolValue(true))),
                                new CompoundStatement(new IfStatement(new variableExpression("a"),
                                        new AssignStatement("v", new valueExpression(new intValue(2))),
                                                new AssignStatement("v", new valueExpression(new intValue(3)))),
                                        new PrintStatement(new variableExpression("v"))))));

        iStack<iStatement> exe1 = new myStack<iStatement>();
        iDictionary<String, Value> sym1 = new myDictionary<String, Value>();
        iList<String> out1 = new myList<String>();
        exe1.push(ex1);
        ProgramState program1 = new ProgramState(exe1, sym1, out1);
        addProgram(program1);

        iStack<iStatement> exe2 = new myStack<iStatement>();
        iDictionary<String, Value> sym2 = new myDictionary<String, Value>();
        iList<String> out2 = new myList<String>();
        exe2.push(ex2);
        ProgramState program2 = new ProgramState(exe2, sym2, out2);
        addProgram(program2);

        iStack<iStatement> exe3 = new myStack<iStatement>();
        iDictionary<String, Value> sym3 = new myDictionary<String, Value>();
        iList<String> out3 = new myList<String>();
        exe3.push(ex3);
        ProgramState program3 = new ProgramState(exe3, sym3, out3);
        addProgram(program3);
    }

    public Repo(iList<ProgramState> sts) { states = sts; initialize(); }

    @Override
    public ProgramState getCurrentProgram() throws MyException { return states.get(0); } //first program ?

    @Override
    public ProgramState getProgramAtIndex(int index) throws MyException {
        return states.get(index);
    }

    @Override
    public void addProgram(ProgramState program) { states.add(program); }

    public String tos() throws MyException {
        String res = "";
        for (int i = 0; i < states.size(); i++)
            res += states.get(i).toString();
        return res;
    }
}
