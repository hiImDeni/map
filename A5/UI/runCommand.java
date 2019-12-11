package UI;

import Controller.Controller;
import Model.myException;
import Model.programState;

public class runCommand extends Command {
    private Controller controller;
    private programState program;
    public runCommand(String key, String description, Controller ctrl, programState prg) {
        super(key, description);
        controller = ctrl;
        program = prg;
    }

    public void execute() {
        try {
            controller.allSteps(program);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
