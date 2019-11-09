import Controller.Controller;
import Model.*;
import Repository.Repo;
import Repository.iRepo;
import UI.UI;

public class toyLanguage {
    public static void main(String a[]){
        iStack<iStatement> executionStack = new myStack<iStatement>();
        iDictionary<String, Value> symTable = new myDictionary<String, Value>();
        iList<String> output = new myList<String>();

        ProgramState state = new ProgramState(executionStack, symTable, output);
        iList<ProgramState> states = new myList<ProgramState>();
        iRepo repository = new Repo(states);
        Controller controller = new Controller(repository);
        UI ui = new UI(controller);
        ui.run();
    }
}
