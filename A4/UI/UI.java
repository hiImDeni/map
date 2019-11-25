package UI;

import Controller.Controller;
import Model.Statement.IStatement;
import Model.myException;
import Model.programState;

import java.io.*;
import java.util.Scanner;

public class UI {
    Controller controller;
    public UI(Controller ctrl) { controller = ctrl; }

    private void printMenu(){
        System.out.println("Options:");
        System.out.println(" - input <index>");
        System.out.println(" - exit");
    }

    public void run(){
        TextMenu menu = new TextMenu();
        try {
            programState program1 = controller.getProgramAtIndex(0);
            programState program2 = controller.getProgramAtIndex(1);
            programState program3 = controller.getProgramAtIndex(2);
            programState program4 = controller.getProgramAtIndex(3);
            programState program5 = controller.getProgramAtIndex(4);
            programState program6 = controller.getProgramAtIndex(5);
            programState program7 = controller.getProgramAtIndex(6);
            programState program8 = controller.getProgramAtIndex(7);
            programState program9 = controller.getProgramAtIndex(8);
            programState program10 = controller.getProgramAtIndex(9);
            programState program11 = controller.getProgramAtIndex(10);
            programState program12 = controller.getProgramAtIndex(11);
            programState program13 = controller.getProgramAtIndex(12);
            programState program14 = controller.getProgramAtIndex(13);

            menu.addCommand(new exitCommand("0", "exit\n"));
            menu.addCommand(new runCommand("1", program1.getExecutionStack().toString(), controller, program1));
            menu.addCommand(new runCommand("2", program2.getExecutionStack().toString(), controller, program2));
            menu.addCommand(new runCommand("3", program3.getExecutionStack().toString(), controller, program3));
            menu.addCommand(new runCommand("4", program4.getExecutionStack().toString(), controller, program4));
            menu.addCommand(new runCommand("5", program5.getExecutionStack().toString(), controller, program5));
            menu.addCommand(new runCommand("6", program6.getExecutionStack().toString(), controller, program6));
            menu.addCommand(new runCommand("7", program7.getExecutionStack().toString(), controller, program7));
            menu.addCommand(new runCommand("8", program8.getExecutionStack().toString(), controller, program8));
            menu.addCommand(new runCommand("9", program9.getExecutionStack().toString(), controller, program9));
            menu.addCommand(new runCommand("10", program10.getExecutionStack().toString(), controller, program10));
            menu.addCommand(new runCommand("11", program11.getExecutionStack().toString(), controller, program11));
            menu.addCommand(new runCommand("12", program12.getExecutionStack().toString(), controller, program12));
            menu.addCommand(new runCommand("13", program13.getExecutionStack().toString(), controller, program13));
            menu.addCommand(new runCommand("14", program14.getExecutionStack().toString(), controller, program14));

            menu.show();
        }
        catch (myException ex){
            System.out.println(ex.getMessage());
        }
    }
}
