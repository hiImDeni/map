package Repository;

import Model.*;
import Model.Expression.*;
import Model.Statement.*;
import Model.Type.boolType;
import Model.Type.intType;
import Model.Type.refType;
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

        IStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("varf", new stringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new stringValue("test.in"))),
                        new CompoundStatement(new OpenRFile(new VariableExpression("varf")), new CompoundStatement(new VariableDeclarationStatement("varc", new intType()),
                                new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                new CompoundStatement(new CloseRFile(new VariableExpression("varf")), new CloseRFile(new VariableExpression("varf")))))))))));

        //int d; d=4; int f; f=5; If f>d THEN print(f) ELSE print("f smaller")
        IStatement ex6=new CompoundStatement(new VariableDeclarationStatement("d", new intType()),new CompoundStatement(new AssignStatement("d",new ValueExpression(new intValue(4))),
                new CompoundStatement(new VariableDeclarationStatement("f",new intType()),new CompoundStatement(new AssignStatement("f", new ValueExpression(new intValue(5))),
                        new IfStatement(new RelationalExpression(new VariableExpression("f"),new VariableExpression("d"),">"),new PrintStatement(new VariableExpression("f")),new PrintStatement(new ValueExpression(new stringValue("f smaller"))))))));

        //IStmt s6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new New("v", new ValueExp(new IntValue(20)), emp),
        //                new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new New("a", new VarExp("v"), emp),
        //                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new refType(new intType())),
                new CompoundStatement(new New("v", new ValueExpression(new intValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new refType(new refType(new intType()))),
                        new CompoundStatement(new New("a", new VariableExpression("v")),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v",new refType(new intType())),
                new CompoundStatement(new New("v", new ValueExpression(new intValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new refType(new refType(new intType()))),
                                new CompoundStatement(new New("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new ReadHeap(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression
                                                        ('+', new ReadHeap(new ReadHeap(new VariableExpression("a"))), new ValueExpression(new intValue(5)))))))));

        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new refType(new intType())),
                new CompoundStatement(new New("v", new ValueExpression(new intValue(20))),
                        new CompoundStatement(new PrintStatement(new ReadHeap(new VariableExpression("v"))),
                                new CompoundStatement(new WriteHeap("v", new ValueExpression(new intValue(30))),
                                        new PrintStatement(new ArithmeticExpression('+', new ReadHeap(new VariableExpression("v")), new ValueExpression(new intValue(5))))))));

        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement ex10 =  new CompoundStatement(new VariableDeclarationStatement("v", new intType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"), new ValueExpression(new intValue(0)), ">"),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignStatement("v",
                                        new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new intValue(1)))))),
                                        new PrintStatement(new VariableExpression("v")))));

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStatement ex11 = new CompoundStatement(new VariableDeclarationStatement("v", new refType(new intType())),
                new CompoundStatement(new New("v", new ValueExpression(new intValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new refType(new refType(new intType()))),
                                new CompoundStatement(new New("a", new VariableExpression("v")),
                                        new CompoundStatement(new New("v", new ValueExpression(new intValue(30))),
                                                new PrintStatement(new ReadHeap(new ReadHeap(new VariableExpression("a")))))))));
        /*IStatement ex11 = new CompoundStatement(new VariableDeclarationStatement("v", new refType(new intType())),
                new CompoundStatement(new New("v", new ValueExpression(new intValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new refType(new refType(new intType()))),
                                new CompoundStatement(new New("v", new ValueExpression(new intValue(30))),
                                        new CompoundStatement(new New("a", new VariableExpression("v")),
                                                new PrintStatement(new ReadHeap(new ReadHeap(new VariableExpression("a")))))))));*/

        IStatement ex12 = new CompoundStatement(new VariableDeclarationStatement("v", new intType()),
                new CompoundStatement(new New("v", new ValueExpression(new intValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new refType(new refType(new intType()))),
                                new CompoundStatement(new New("a", new VariableExpression("v")),
                                        new CompoundStatement(new New("v", new ValueExpression(new intValue(30))),
                                                new PrintStatement(new ReadHeap(new ReadHeap(new VariableExpression("a")))))))));

        IStatement ex13 =  new CompoundStatement(new VariableDeclarationStatement("v", new intType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(4))),
                        new CompoundStatement(new WhileStatement(new VariableExpression("v"), new CompoundStatement
                                (new PrintStatement(new VariableExpression("v")), new AssignStatement("v",
                                        new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new intValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));

        IStatement ex14 = new CompoundStatement(new VariableDeclarationStatement("v", new refType(new intType())),
                new CompoundStatement(new New("v", new ValueExpression(new intValue(20))),
                        new CompoundStatement(new New("v", new ValueExpression(new intValue(30))),
                                new PrintStatement(new ReadHeap(new VariableExpression("v"))))));


        iStack<IStatement> exe1 = new myStack<IStatement>();
        iDictionary<String, value> sym1 = new myDictionary<String, value>();
        iList<String> out1 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file1 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap1 = new myHeap<value>();
        exe1.push(ex1);
        programState program1 = new programState(exe1, sym1, out1, file1, heap1);
        addProgram(program1);

        iStack<IStatement> exe2 = new myStack<IStatement>();
        iDictionary<String, value> sym2 = new myDictionary<String, value>();
        iList<String> out2 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file2 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap2 = new myHeap<value>();
        exe2.push(ex2);
        programState program2 = new programState(exe2, sym2, out2, file2, heap2);
        addProgram(program2);

        iStack<IStatement> exe3 = new myStack<IStatement>();
        iDictionary<String, value> sym3 = new myDictionary<String, value>();
        iList<String> out3 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file3 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap3 = new myHeap<value>();
        exe3.push(ex3);
        programState program3 = new programState(exe3, sym3, out3, file3, heap3);
        addProgram(program3);

        iStack<IStatement> exe4 = new myStack<IStatement>();
        iDictionary<String, value> sym4 = new myDictionary<String, value>();
        iList<String> out4 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file4 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap4 = new myHeap<value>();
        exe4.push(ex4);
        programState program4 = new programState(exe4, sym4, out4, file4, heap4);
        addProgram(program4);

        iStack<IStatement> exe5 = new myStack<IStatement>();
        iDictionary<String, value> sym5 = new myDictionary<String, value>();
        iList<String> out5 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file5 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap5 = new myHeap<value>();
        exe5.push(ex5);
        programState program5 = new programState(exe5, sym5, out5, file5, heap5);
        addProgram(program5);

        iStack<IStatement> exe6 = new myStack<IStatement>();
        iDictionary<String, value> sym6 = new myDictionary<String, value>();
        iList<String> out6 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file6 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap6 = new myHeap<value>();
        exe6.push(ex6);
        programState program6 = new programState(exe6, sym6, out6, file6, heap6);
        addProgram(program6);

        iStack<IStatement> exe7 = new myStack<IStatement>();
        iDictionary<String, value> sym7 = new myDictionary<String, value>();
        iList<String> out7 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file7 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap7 = new myHeap<value>();
        exe7.push(ex7);
        programState program7 = new programState(exe7, sym7, out7, file7, heap7);
        addProgram(program7);

        iStack<IStatement> exe8 = new myStack<IStatement>();
        iDictionary<String, value> sym8 = new myDictionary<String, value>();
        iList<String> out8 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file8 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap8 = new myHeap<value>();
        exe8.push(ex8);
        programState program8 = new programState(exe8, sym8, out8, file8, heap8);
        addProgram(program8);

        iStack<IStatement> exe9 = new myStack<IStatement>();
        iDictionary<String, value> sym9 = new myDictionary<String, value>();
        iList<String> out9 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file9 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap9 = new myHeap<value>();
        exe9.push(ex9);
        programState program9 = new programState(exe9, sym9, out9, file9, heap9);
        addProgram(program9);

        iStack<IStatement> exe10 = new myStack<IStatement>();
        iDictionary<String, value> sym10 = new myDictionary<String, value>();
        iList<String> out10 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file10 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap10 = new myHeap<value>();
        exe10.push(ex10);
        programState program10 = new programState(exe10, sym10, out10, file10, heap10);
        addProgram(program10);

        iStack<IStatement> exe11 = new myStack<IStatement>();
        iDictionary<String, value> sym11 = new myDictionary<String, value>();
        iList<String> out11 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file11 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap11 = new myHeap<value>();
        exe11.push(ex11);
        programState program11 = new programState(exe11, sym11, out11, file11, heap11);
        addProgram(program11);

        iStack<IStatement> exe12 = new myStack<IStatement>();
        iDictionary<String, value> sym12 = new myDictionary<String, value>();
        iList<String> out12 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file12 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap12 = new myHeap<value>();
        exe12.push(ex12);
        programState program12 = new programState(exe12, sym12, out12, file12, heap12);
        addProgram(program12);

        iStack<IStatement> exe13 = new myStack<IStatement>();
        iDictionary<String, value> sym13 = new myDictionary<String, value>();
        iList<String> out13 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file13 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap13 = new myHeap<value>();
        exe13.push(ex13);
        programState program13 = new programState(exe13, sym13, out13, file13, heap13);
        addProgram(program13);

        iStack<IStatement> exe14 = new myStack<IStatement>();
        iDictionary<String, value> sym14 = new myDictionary<String, value>();
        iList<String> out14 = new myList<String>();
        iDictionary<stringValue, BufferedReader> file14 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heap14 = new myHeap<value>();
        exe14.push(ex14);
        programState program14 = new programState(exe14, sym14, out14, file14, heap14);
        addProgram(program14);
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
