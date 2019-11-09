package ui;

import controller.Controller;
import model.*;

import java.util.Scanner;

public class UI {
    Controller controller = new Controller();
    void initiate() throws Exception
    {
        controller.add("fish", "Greg", 3);
        controller.add("tortoise", "Bob", 2);
        controller.add("tortoise", "Suzy", 1);
        controller.add("fish", "Nemo", 1);
    }

    static void printMenu()
    {
        System.out.println("Options are:\n" +
                " - add <tortoise/fish> <name> <age>\n" +
                " - remove <name>\n" +
                " - filter <age>\n" +
                " - list\n" +
                " - exit\n"
        );
    }

    public void run()
    {
        try {
            initiate();
        }
        catch(Exception ex) {}
        label:
        while(true) {
            try {
                printMenu();
                //System.out.println("\nEnter option: ");

                Scanner in = new Scanner(System.in);
                String option = in.nextLine();

                String[] tokens = option.split(" ");

                switch (tokens[0]) {
                    case "exit":
                        break label;
                    case "filter":
                        animal[] filterArray = controller.filterByAge(Float.parseFloat(tokens[1]));

                        for (int i = 0; i < controller.getFilterSize(); i++)
                            System.out.println(filterArray[i]);
                        break;
                    case "add": {
                        //Scanner input = new Scanner(System.in);
                        String type = tokens[1];
                        String name = tokens[2];
                        float age = Float.parseFloat(tokens[3]);

                        controller.add(type, name, age);

                        //TO DO: addTortoise, addFish!!!
                        break;
                    }
                    case "remove": {
                        String name = tokens[1];
                        controller.remove(name);
                        break;
                    }
                    case "list":
                        for (animal i : controller.getAnimals())
                            System.out.println(i);
                        break;
                    default:
                        System.out.println("Incorrect option!");
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
}
