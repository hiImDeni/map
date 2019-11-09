package UI;

import Controller.Controller;
import Model.MyException;
import Model.ProgramState;

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
        while(true){
            try
            {
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
                    ProgramState programState = controller.getProgramAtIndex(index-1);
                    System.out.println(programState);
                    System.out.println("  - one step");
                    System.out.println("  - complete");

                    in = new Scanner(System.in);
                    option = in.nextLine();
                    if (option.equals("one step"))
                        controller.oneStep(programState);
                    else if (option.equals("complete"))
                        controller.allSteps(programState);
                    else
                        System.out.println("Incorrect option");
                }
                /*else if (tokens[0].equals("one") && tokens[1].equals("step") && tokens.length == 3){
                    int index = Integer.parseInt(tokens[2]);
                    ProgramState programState = controller.getProgramAtIndex(index);
                    controller.oneStep(programState);
                }
                else if (tokens[0].equals("complete") && tokens.length == 1)
                    controller.allSteps();*/
                else
                    System.out.println("Incorrect option");
            }
            catch (MyException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
