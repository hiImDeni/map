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
        //System.out.println(" - one step <index>");
        //System.out.println(" - complete");
        System.out.println(" - exit");
    }

    public void run(){
        TextMenu menu = new TextMenu();
        try {
            programState program1 = controller.getProgramAtIndex(0);
            programState program2 = controller.getProgramAtIndex(1);
            programState program3 = controller.getProgramAtIndex(2);
            programState program4 = controller.getProgramAtIndex(3);

            menu.addCommand(new exitCommand("0", "exit"));
            menu.addCommand(new runCommand("1", "example1", controller, program1));
            menu.addCommand(new runCommand("2", "example2", controller, program2));
            menu.addCommand(new runCommand("3", "example3", controller, program3));
            menu.addCommand(new runCommand("4", "example4", controller, program4));

            menu.show();
        }
        catch (myException ex){
            System.out.println(ex.getMessage());
        }
        /*while (true) {
            try {
                printMenu();
                Scanner in = new Scanner(System.in);
                String option = in.nextLine();
                String[] tokens = option.split(" ");
                if (tokens[0].equals("exit") && tokens.length == 1)
                    break;
                else if (tokens[0].equals("input") && tokens.length == 2) {
                    //System.out.println(controller.tos());
                    int index = Integer.parseInt(tokens[1]);
                    //System.out.println(index);
                    programState programState = controller.getProgramAtIndex(index - 1);
                    System.out.println(programState);
                    System.out.println("  - one step");
                    System.out.println("  - complete");

                    in = new Scanner(System.in);
                    option = in.nextLine();
                    switch (option) {
                        case "one step":
                            controller.oneStep(programState);
                            break;
                        case "complete":
                            controller.allSteps(programState);
                            break;
                        case "exit":
                            return;
                        default:
                            System.out.println("Incorrect option");
                            break;
                    }
                }
                else
                    System.out.println("Incorrect option");
            } catch (myException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}
