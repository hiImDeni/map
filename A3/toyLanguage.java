import Controller.Controller;
import Model.*;
import Model.Statement.IStatement;
import Model.Value.stringValue;
import Model.Value.value;
import Repository.Repo;
import Repository.iRepo;
import UI.UI;

import java.io.BufferedReader;
import java.util.Scanner;

public class toyLanguage {
    public static void main(String a[]){

        //try {
            //System.out.println("Enter file path: ");
            //Scanner in = new Scanner(System.in);
            //String filePath = in.nextLine();
            //PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));

            iStack<IStatement> executionStack = new myStack<IStatement>();
            iDictionary<String, value> symTable = new myDictionary<String, value>();
            iList<String> output = new myList<String>();
            iDictionary<stringValue, BufferedReader> fileTable = new myDictionary<stringValue, BufferedReader>();
            programState state = new programState(executionStack, symTable, output, fileTable);
            iList<programState> states = new myList<programState>();

            iRepo repository = new Repo(states, "log.txt");
            Controller controller = new Controller(repository);
            UI ui = new UI(controller);
            ui.run();
            //logFile.close();
        /*} catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
