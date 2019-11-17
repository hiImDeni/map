package Repository;

import Model.*;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.Statement.*;
import Model.Type.boolType;
import Model.Type.intType;
import Model.Type.stringType;
import Model.Value.boolValue;
import Model.Value.intValue;
import Model.Value.stringValue;
import Model.Value.value;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Repo implements iRepo {
    iList<programState> states;
    String filePath;
    //int index;

    private void initialize(){
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new intType()), new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(2))), new PrintStatement(new VariableExpression("v"))));

        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new intType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new intType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression('+',
                                new ValueExpression(new intValue(2)), new ArithmeticExpression('*',
                                new ValueExpression(new intValue(3)), new ValueExpression(new intValue(5))))),
                                new CompoundStatement(new AssignStatement("b", new ArithmeticExpression('+',
                                        new VariableExpression("a"), new ValueExpression(new intValue(1)))), new PrintStatement(new VariableExpression("b"))))));

        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new boolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new intType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new boolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),
                                        new AssignStatement("v", new ValueExpression(new intValue(2))),
                                                new AssignStatement("v", new ValueExpression(new intValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));

        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("varf", new stringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new stringValue("test.in"))),
                        new CompoundStatement(new OpenRFile(new VariableExpression("varf")), new CompoundStatement(new VariableDeclarationStatement("varc", new intType()),
                                new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                new CloseRFile(new VariableExpression("varf"))))))))));

        iStack<IStatement> exe1 = new myStack<IStatement>();
        iDictionary<String, value> sym1 = new myDictionary<String, value>();
        iList<String> out1 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file1 = new myDictionary<stringValue, BufferedReader>();
        exe1.push(ex1);
        programState program1 = new programState(exe1, sym1, out1, file1);
        addProgram(program1);

        iStack<IStatement> exe2 = new myStack<IStatement>();
        iDictionary<String, value> sym2 = new myDictionary<String, value>();
        iList<String> out2 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file2 = new myDictionary<stringValue, BufferedReader>();
        exe2.push(ex2);
        programState program2 = new programState(exe2, sym2, out2, file2);
        addProgram(program2);

        iStack<IStatement> exe3 = new myStack<IStatement>();
        iDictionary<String, value> sym3 = new myDictionary<String, value>();
        iList<String> out3 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file3 = new myDictionary<stringValue, BufferedReader>();
        exe3.push(ex3);
        programState program3 = new programState(exe3, sym3, out3, file3);
        addProgram(program3);

        iStack<IStatement> exe4 = new myStack<IStatement>();
        iDictionary<String, value> sym4 = new myDictionary<String, value>();
        iList<String> out4 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file4 = new myDictionary<stringValue, BufferedReader>();
        exe4.push(ex4);
        programState program4 = new programState(exe4, sym4, out4, file4);
        addProgram(program4);
    }

    public Repo(iList<programState> sts, String log) { states = sts; initialize(); filePath = log; }

    @Override
    public programState getCurrentProgram() throws myException { return states.get(0); } //first program ?

    @Override
    public programState getProgramAtIndex(int index) throws myException {
        return states.get(index);
    }

    @Override
    public void logProgramState(programState program) throws Exception{
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
        logFile.println(program);
        logFile.close();
    }

    @Override
    public void addProgram(programState program) { states.add(program); }

    public String tos() throws myException {
        String res = "";
        for (int i = 0; i < states.size(); i++)
            res += states.get(i).toString();
        return res;
    }
}
