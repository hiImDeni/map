package sample;

import Model.*;
import Model.Containers.*;
import Model.Expression.*;
import Model.Statement.*;
import Model.Type.*;
import Model.Value.boolValue;
import Model.Value.intValue;
import Model.Value.stringValue;
import Model.Value.value;
import Repository.Repo;
import Repository.iRepo;
import UI.TextMenu;
import UI.exitCommand;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    private static iRepo currentRepo;
    private List<iRepo> repos;
    @FXML
    private ListView<iRepo> programsList;
    @FXML
    private Button startButton;

    public static iRepo getCurrentRepo() { return currentRepo; }

    @FXML
    public void changeScene(MouseEvent mouseEvent) throws Exception{
        //Stage stage;
        Parent root = FXMLLoader.load(getClass().getResource("startWindow.fxml"));
        startButton.getScene();
        startButton.getScene().setRoot(root);
        currentRepo = programsList.getSelectionModel().getSelectedItem();


    }

    @FXML
    public void initialize() {
        initializeRepo();
        populateRepos();
    }

    private void populateRepos() {
        programsList.setItems(getRepos());

        // To set selection model
        programsList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // select item at index 0
        programsList.getSelectionModel().selectIndices(0);

        currentRepo = programsList.getSelectionModel().getSelectedItem();
    }

    private ObservableList<iRepo> getRepos() {
        //List<programState> programsList = repository.getProgramsList().getContent();
        ObservableList<iRepo> observablePrograms = FXCollections.observableArrayList(repos);
        return observablePrograms;
    }

    private void initializeRepo() {
        repos = new ArrayList<iRepo>();
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new stringType()), new CompoundStatement(new AssignStatement("v", new ValueExpression(new intValue(2))), new PrintStatement(new VariableExpression("v"))));

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

        TextMenu menu = new TextMenu();
        menu.addCommand(new exitCommand("0", "exit\n"));

        Alert error;

        try {
            iDictionary<String, type> typeEnvironment = new myDictionary<String, type>();
            ex1.typecheck(typeEnvironment);
            iStack<IStatement> executionStack1 = new myStack<IStatement>();
            iDictionary<String, value> symTable1 = new myDictionary<String, value>();
            iList<String> output1 = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable1 = new myDictionary<stringValue, BufferedReader>();
            iHeap<value> heapTable1 = new myHeap<value>();
            executionStack1.push(ex1);
            programState program1 = new programState(executionStack1, symTable1, output1, fileTable1, heapTable1);
            //program1.getOutput().add("wtf is wrong with you");
            iRepo repo1 = new Repo(program1, "log1.txt");
            repos.add(repo1);
        }
        catch (myException ex){
            error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            error.setTitle("Program 1");
            error.show();
            //System.out.println("Program 1: " + ex.getMessage());
        }

        try {
            iDictionary<String, type> typeEnvironment = new myDictionary<String, type>();
            ex2.typecheck(typeEnvironment);
            iStack<IStatement> executionStack2 = new myStack<IStatement>();
            iDictionary<String, value> symTable2 = new myDictionary<String, value>();
            iList<String> output2 = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable2 = new myDictionary<stringValue, BufferedReader>();
            iHeap<value> heapTable2 = new myHeap<value>();
            executionStack2.push(ex2);
            programState program2 = new programState(executionStack2, symTable2, output2, fileTable2, heapTable2);
            iRepo repo2 = new Repo(program2, "log2.txt");
            repos.add(repo2);
        }
        catch (myException ex) {
            error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            error.setTitle("Program 2");
            error.show();
        }

        try {
            iDictionary<String, type> typeEnvironment = new myDictionary<String, type>();
            ex3.typecheck(typeEnvironment);
            iStack<IStatement> executionStack3 = new myStack<IStatement>();
            iDictionary<String, value> symTable3 = new myDictionary<String, value>();
            iList<String> output3 = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable3 = new myDictionary<stringValue, BufferedReader>();
            iHeap<value> heapTable3 = new myHeap<value>();
            executionStack3.push(ex3);
            programState program3= new programState(executionStack3, symTable3, output3, fileTable3, heapTable3);
            iRepo repo3 = new Repo(program3, "log3.txt");
            repos.add(repo3);
        } catch (myException ex) {
            error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            error.setTitle("Program 3");
            error.show();
        }

        try {
            iDictionary<String, type> typeEnvironment = new myDictionary<String, type>();
            ex4.typecheck(typeEnvironment);
            iStack<IStatement> executionStack4 = new myStack<IStatement>();
            iDictionary<String, value> symTable4 = new myDictionary<String, value>();
            iList<String> output4 = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable4 = new myDictionary<stringValue, BufferedReader>();
            iHeap<value> heapTable4 = new myHeap<value>();
            executionStack4.push(ex4);
            programState program4 = new programState(executionStack4, symTable4, output4, fileTable4, heapTable4);
            iRepo repo4 = new Repo(program4, "log4.txt");
            repos.add(repo4);
        } catch (myException ex) {
            error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            error.setTitle("Program 4");
            error.show();
        }

        try {
            iDictionary<String, type> typeEnvironment = new myDictionary<String, type>();
            ex5.typecheck(typeEnvironment);
            iStack<IStatement> executionStack5 = new myStack<IStatement>();
            iDictionary<String, value> symTable5 = new myDictionary<String, value>();
            iList<String> output5 = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable5 = new myDictionary<stringValue, BufferedReader>();
            iHeap<value> heapTable5 = new myHeap<value>();
            executionStack5.push(ex5);
            programState program5 = new programState(executionStack5, symTable5, output5, fileTable5, heapTable5);
            iRepo repo5 = new Repo(program5, "log5.txt");
            repos.add(repo5);
        } catch (myException ex) {
            error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            error.setTitle("Program 5");
            error.show(); }

        try {
            iDictionary<String, type> typeEnvironment = new myDictionary<String, type>();
            ex5.typecheck(typeEnvironment);
            iStack<IStatement> executionStack6 = new myStack<IStatement>();
            iDictionary<String, value> symTable6 = new myDictionary<String, value>();
            iList<String> output6 = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable6 = new myDictionary<stringValue, BufferedReader>();
            iHeap<value> heapTable6 = new myHeap<value>();
            executionStack6.push(ex6);
            programState program6 = new programState(executionStack6, symTable6, output6, fileTable6, heapTable6);
            iRepo repo6 = new Repo(program6, "log6.txt");
            repos.add(repo6);
        } catch (myException ex) {
            error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            error.setTitle("Program 6");
            error.show();
        }

        try {
            iDictionary<String, type> typeEnvironment = new myDictionary<String, type>();
            ex7.typecheck(typeEnvironment);
            iStack<IStatement> executionStack7 = new myStack<IStatement>();
            iDictionary<String, value> symTable7 = new myDictionary<String, value>();
            iList<String> output7 = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable7 = new myDictionary<stringValue, BufferedReader>();
            iHeap<value> heapTable7 = new myHeap<value>();
            executionStack7.push(ex7);
            programState program7 = new programState(executionStack7, symTable7, output7, fileTable7, heapTable7);
            iRepo repo7 = new Repo(program7, "log7.txt");
            repos.add(repo7);
        } catch (myException ex) {
            error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            error.setTitle("Program 7");
            error.show();
        }

        try {
            iDictionary<String, type> typeEnvironment = new myDictionary<String, type>();
            ex8.typecheck(typeEnvironment);
            iStack<IStatement> executionStack8 = new myStack<IStatement>();
            iDictionary<String, value> symTable8 = new myDictionary<String, value>();
            iList<String> output8 = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable8 = new myDictionary<stringValue, BufferedReader>();
            iHeap<value> heapTable8 = new myHeap<value>();
            executionStack8.push(ex8);
            programState program8 = new programState(executionStack8, symTable8, output8, fileTable8, heapTable8);
            iRepo repo8 = new Repo(program8, "log8.txt");
            repos.add(repo8);
        } catch (myException ex) {
            error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            error.setTitle("Program 8");
            error.show();
        }

        try {
            iDictionary<String, type> typeEnvironment = new myDictionary<String, type>();
            ex9.typecheck(typeEnvironment);
            iStack<IStatement> executionStack9 = new myStack<IStatement>();
            iDictionary<String, value> symTable9 = new myDictionary<String, value>();
            iList<String> output9 = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable9 = new myDictionary<stringValue, BufferedReader>();
            iHeap<value> heapTable9 = new myHeap<value>();
            executionStack9.push(ex9);
            programState program9 = new programState(executionStack9, symTable9, output9, fileTable9, heapTable9);
            iRepo repo9 = new Repo(program9, "log9.txt");
            repos.add(repo9);
        } catch (myException ex) {
            error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            error.setTitle("Program 9");
            error.show();
        }

        try {
            iDictionary<String, type> typeEnvironment = new myDictionary<String, type>();
            ex10.typecheck(typeEnvironment);
            iStack<IStatement> executionStack10 = new myStack<IStatement>();
            iDictionary<String, value> symTable10 = new myDictionary<String, value>();
            iList<String> output10 = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable10 = new myDictionary<stringValue, BufferedReader>();
            iHeap<value> heapTable10 = new myHeap<value>();
            executionStack10.push(ex10);
            programState program10 = new programState(executionStack10, symTable10, output10, fileTable10, heapTable10);
            iRepo repo10 = new Repo(program10, "log10.txt");
            repos.add(repo10);
        } catch (myException ex) {
            error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            error.setTitle("Program 10");
            error.show();
        }

        try {
            iDictionary<String, type> typeEnvironment = new myDictionary<String, type>();
            ex11.typecheck(typeEnvironment);
            iStack<IStatement> executionStack11 = new myStack<IStatement>();
            iDictionary<String, value> symTable11 = new myDictionary<String, value>();
            iList<String> output11 = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable11 = new myDictionary<stringValue, BufferedReader>();
            iHeap<value> heapTable11 = new myHeap<value>();
            executionStack11.push(ex11);
            programState program11 = new programState(executionStack11, symTable11, output11, fileTable11, heapTable11);
            iRepo repo11 = new Repo(program11, "log11.txt");
            repos.add(repo11);
        } catch (myException ex) {
            error = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            error.setTitle("Program 11");
            error.show();
        }

    }

    public void onSelect(MouseEvent mouseEvent) {
        if (currentRepo != programsList.getSelectionModel().getSelectedItem()){
            currentRepo = programsList.getSelectionModel().getSelectedItem();
        }
    }
}
