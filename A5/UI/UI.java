package UI;

import Controller.Controller;
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
import Repository.Repo;
import Repository.iRepo;
import com.sun.jdi.Value;

import javax.lang.model.type.ReferenceType;
import java.io.*;
import java.util.Scanner;

public class UI {
    Controller controller;
    public UI(Controller ctrl) { controller = ctrl; }
    public UI() {}

    private void printMenu(){
        System.out.println("Options:");
        System.out.println(" - input <index>");
        System.out.println(" - exit");
    }

    void addCommands() throws myException{
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

        /*IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("varf", new stringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new stringValue("test.in"))),
                        new CompoundStatement(new OpenRFile(new VariableExpression("varf")), new CompoundStatement(new VariableDeclarationStatement("varc", new intType()),
                                new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                new CloseRFile(new VariableExpression("varf"))))))))));*/

        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("varf", new stringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new stringValue("test.in"))),
                        new CompoundStatement(new OpenRFile(new VariableExpression("varf")), new CompoundStatement(new VariableDeclarationStatement("varc", new intType()),
                                new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                new CompoundStatement(new ReadFile(new VariableExpression("varf"), "varc"),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                new CompoundStatement(new CloseRFile(new VariableExpression("varf")),
                                                                        new CloseRFile(new VariableExpression("varf")))))))))));

        //int d; d=4; int f; f=5; If f>d THEN print(f) ELSE print("f smaller")
        IStatement ex5=new CompoundStatement(new VariableDeclarationStatement("d", new intType()),new CompoundStatement(new AssignStatement("d",new ValueExpression(new intValue(4))),
                new CompoundStatement(new VariableDeclarationStatement("f",new intType()),new CompoundStatement(new AssignStatement("f", new ValueExpression(new intValue(5))),
                        new IfStatement(new RelationalExpression(new VariableExpression("f"),new VariableExpression("d"),">"),new PrintStatement(new VariableExpression("f")),new PrintStatement(new ValueExpression(new stringValue("f smaller"))))))));

        IStatement ex6 = new CompoundStatement(new VariableDeclarationStatement("v", new refType(new intType())),
                new CompoundStatement(new New("v", new ValueExpression(new intValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new refType(new refType(new intType()))),
                                new CompoundStatement(new New("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));

        //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v",new refType(new intType())),
                new CompoundStatement(new New("v", new ValueExpression(new intValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new refType(new refType(new intType()))),
                                new CompoundStatement(new New("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new ReadHeap(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression
                                                        ('+', new ReadHeap(new ReadHeap(new VariableExpression("a"))), new ValueExpression(new intValue(5)))))))));

        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new refType(new intType())),
                new CompoundStatement(new New("v", new ValueExpression(new intValue(20))),
                        new CompoundStatement(new PrintStatement(new ReadHeap(new VariableExpression("v"))),
                                new CompoundStatement(new WriteHeap("v", new ValueExpression(new intValue(30))),
                                        new PrintStatement(new ArithmeticExpression('+', new ReadHeap(new VariableExpression("v")), new ValueExpression(new intValue(5))))))));

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new refType(new intType())),
                new CompoundStatement(new New("v", new ValueExpression(new intValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new refType(new refType(new intType()))),
                                new CompoundStatement(new New("a", new VariableExpression("v")),
                                        new CompoundStatement(new New("v", new ValueExpression(new intValue(30))),
                                                new PrintStatement(new ReadHeap(new ReadHeap(new VariableExpression("a")))))))));

        //int v; Ref int a; v=10; new(a,22); fork(wH(a,30); v=32; print(v); print(rH(a)));   print(v); print(rH(a))
        IStatement ex10 = new CompoundStatement(new VariableDeclarationStatement("v", new intType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new refType(new intType())),
                        new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(10))),
                                new CompoundStatement(new New("a", new ValueExpression(new intValue(22))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeap("a", new ValueExpression(new intValue(30))),
                                                new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(32))),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap(new VariableExpression("a"))))))),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap(new VariableExpression("a")))))))));

        //int v; v = 2; ref int a; new(a,4);
        // fork(
        //   v=3; wh(a,6); fork(v=4;wh(a,8);print(v);print(rh(a))
        //   print(v))
        // fork (v=5; wh(a,10);print(v);print(rh(a))
        //print(v); print(rh(a));
        /*IStatement ex11 = new CompoundStatement(new VariableDeclarationStatement("v", new intType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(2))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new refType(new intType())),
                                new CompoundStatement(new New("a", new ValueExpression(new intValue(4))),
                                        new CompoundStatement(new ForkStatement(new CompoundStatement(
                                                new AssignStatement("v", new ValueExpression(new intValue(3))),
                                                new CompoundStatement(new WriteHeap("a", new ValueExpression(new intValue(6))),
                                                        new CompoundStatement(new ForkStatement(new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(6))),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap(new VariableExpression("a")))))),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap(new VariableExpression("a")))))),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeap(new VariableExpression("a"))))),
                                                new
                                        )))))))*/

        //int v; v = 2;
        // fork(
        //   v=3; fork(v=4;print(v);)
        //   print(v))
        // fork (v=5; print(v);)
        //print(v);
        IStatement ex11 = new CompoundStatement(new VariableDeclarationStatement("v", new intType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(2))),
                        new CompoundStatement(new ForkStatement(new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(3))),
                                new CompoundStatement(new ForkStatement(new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(4))), new PrintStatement(new VariableExpression("v")))),
                                        new PrintStatement(new VariableExpression("v"))))),
                                new CompoundStatement(new ForkStatement(new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(5))), new PrintStatement(new VariableExpression("v")))),
                                        new PrintStatement(new VariableExpression("v"))))));

        iStack<IStatement> executionStack1 = new myStack<IStatement>();
        iDictionary<String, value> symTable1 = new myDictionary<String, value>();
        iList<String> output1 = new myList<String>();
        iDictionary<stringValue, BufferedReader> fileTable1 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heapTable1 = new myHeap<value>();
        executionStack1.push(ex1);
        programState program1 = new programState(executionStack1, symTable1, output1, fileTable1, heapTable1);
        iRepo repo1 = new Repo(program1, "log1.txt");
        Controller controller1 = new Controller(repo1);

        iStack<IStatement> executionStack2 = new myStack<IStatement>();
        iDictionary<String, value> symTable2 = new myDictionary<String, value>();
        iList<String> output2 = new myList<String>();
        iDictionary<stringValue, BufferedReader> fileTable2 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heapTable2 = new myHeap<value>();
        executionStack2.push(ex2);
        programState program2 = new programState(executionStack2, symTable2, output2, fileTable2, heapTable2);
        iRepo repo2 = new Repo(program2, "log2.txt");
        Controller controller2 = new Controller(repo2);

        iStack<IStatement> executionStack3 = new myStack<IStatement>();
        iDictionary<String, value> symTable3 = new myDictionary<String, value>();
        iList<String> output3 = new myList<String>();
        iDictionary<stringValue, BufferedReader> fileTable3 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heapTable3 = new myHeap<value>();
        executionStack3.push(ex3);
        programState program3= new programState(executionStack3, symTable3, output3, fileTable3, heapTable3);
        iRepo repo3 = new Repo(program3, "log3.txt");
        Controller controller3 = new Controller(repo3);

        iStack<IStatement> executionStack4 = new myStack<IStatement>();
        iDictionary<String, value> symTable4 = new myDictionary<String, value>();
        iList<String> output4 = new myList<String>();
        iDictionary<stringValue, BufferedReader> fileTable4 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heapTable4 = new myHeap<value>();
        executionStack4.push(ex4);
        programState program4 = new programState(executionStack4, symTable4, output4, fileTable4, heapTable4);
        iRepo repo4 = new Repo(program4, "log4.txt");
        Controller controller4 = new Controller(repo4);

        iStack<IStatement> executionStack5 = new myStack<IStatement>();
        iDictionary<String, value> symTable5 = new myDictionary<String, value>();
        iList<String> output5 = new myList<String>();
        iDictionary<stringValue, BufferedReader> fileTable5 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heapTable5 = new myHeap<value>();
        executionStack5.push(ex5);
        programState program5 = new programState(executionStack5, symTable5, output5, fileTable5, heapTable5);
        iRepo repo5 = new Repo(program5, "log5.txt");
        Controller controller5 = new Controller(repo5);

        iStack<IStatement> executionStack6 = new myStack<IStatement>();
        iDictionary<String, value> symTable6 = new myDictionary<String, value>();
        iList<String> output6 = new myList<String>();
        iDictionary<stringValue, BufferedReader> fileTable6 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heapTable6 = new myHeap<value>();
        executionStack6.push(ex6);
        programState program6 = new programState(executionStack6, symTable6, output6, fileTable6, heapTable6);
        iRepo repo6 = new Repo(program6, "log6.txt");
        Controller controller6 = new Controller(repo6);

        iStack<IStatement> executionStack7 = new myStack<IStatement>();
        iDictionary<String, value> symTable7 = new myDictionary<String, value>();
        iList<String> output7 = new myList<String>();
        iDictionary<stringValue, BufferedReader> fileTable7 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heapTable7 = new myHeap<value>();
        executionStack7.push(ex7);
        programState program7 = new programState(executionStack7, symTable7, output7, fileTable7, heapTable7);
        iRepo repo7 = new Repo(program7, "log7.txt");
        Controller controller7 = new Controller(repo7);

        iStack<IStatement> executionStack8 = new myStack<IStatement>();
        iDictionary<String, value> symTable8 = new myDictionary<String, value>();
        iList<String> output8 = new myList<String>();
        iDictionary<stringValue, BufferedReader> fileTable8 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heapTable8 = new myHeap<value>();
        executionStack8.push(ex8);
        programState program8 = new programState(executionStack8, symTable8, output8, fileTable8, heapTable8);
        iRepo repo8 = new Repo(program8, "log8.txt");
        Controller controller8 = new Controller(repo8);

        iStack<IStatement> executionStack9 = new myStack<IStatement>();
        iDictionary<String, value> symTable9 = new myDictionary<String, value>();
        iList<String> output9 = new myList<String>();
        iDictionary<stringValue, BufferedReader> fileTable9 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heapTable9 = new myHeap<value>();
        executionStack9.push(ex9);
        programState program9 = new programState(executionStack9, symTable9, output9, fileTable9, heapTable9);
        iRepo repo9 = new Repo(program9, "log9.txt");
        Controller controller9 = new Controller(repo9);

        iStack<IStatement> executionStack10 = new myStack<IStatement>();
        iDictionary<String, value> symTable10 = new myDictionary<String, value>();
        iList<String> output10 = new myList<String>();
        iDictionary<stringValue, BufferedReader> fileTable10 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heapTable10 = new myHeap<value>();
        executionStack10.push(ex10);
        programState program10 = new programState(executionStack10, symTable10, output10, fileTable10, heapTable10);
        iRepo repo10 = new Repo(program10, "log10.txt");
        Controller controller10 = new Controller(repo10);

        iStack<IStatement> executionStack11 = new myStack<IStatement>();
        iDictionary<String, value> symTable11 = new myDictionary<String, value>();
        iList<String> output11 = new myList<String>();
        iDictionary<stringValue, BufferedReader> fileTable11 = new myDictionary<stringValue, BufferedReader>();
        iHeap<value> heapTable11 = new myHeap<value>();
        executionStack11.push(ex11);
        programState program11 = new programState(executionStack11, symTable11, output11, fileTable11, heapTable11);
        iRepo repo11 = new Repo(program11, "log11.txt");
        Controller controller11 = new Controller(repo11);

        TextMenu menu = new TextMenu();

        menu.addCommand(new exitCommand("0", "exit\n"));
        menu.addCommand(new runCommand("1", program1.getExecutionStack().toString(), controller1, program1));
        menu.addCommand(new runCommand("2", program2.getExecutionStack().toString(), controller2, program2));
        menu.addCommand(new runCommand("3", program3.getExecutionStack().toString(), controller3, program3));
        menu.addCommand(new runCommand("4", program4.getExecutionStack().toString(), controller4, program4));
        menu.addCommand(new runCommand("5", program5.getExecutionStack().toString(), controller5, program5));
        menu.addCommand(new runCommand("6", program6.getExecutionStack().toString(), controller6, program6));
        menu.addCommand(new runCommand("7", program7.getExecutionStack().toString(), controller7, program7));
        menu.addCommand(new runCommand("8", program8.getExecutionStack().toString(), controller8, program8));
        menu.addCommand(new runCommand("9", program9.getExecutionStack().toString(), controller9, program9));
        menu.addCommand(new runCommand("10", program10.getExecutionStack().toString(), controller10, program10));
        menu.addCommand(new runCommand("11", program11.getExecutionStack().toString(), controller11, program11));

        menu.show();
    }

    public void run(){
        try {
            addCommands();
        }
        catch (myException ex){
            System.out.println(ex.getMessage());
        }
    }
}
