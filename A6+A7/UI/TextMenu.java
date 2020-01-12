package UI;

import Model.Containers.iDictionary;
import Model.Containers.myDictionary;

import java.util.Scanner;

public class TextMenu {
    private iDictionary<String, Command> commands;
    public TextMenu() { commands = new myDictionary<String, Command>(); }

    public void addCommand(Command command) { commands.put(command.getKey(), command); }

    private void printMenu() {
        System.out.println("Options are: ");
        for (Command command : commands.values()){
            String line = String.format("%4s: %s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu();
            System.out.printf("Input the option: ");
            String key = scanner.nextLine();
            Command command = commands.get(key);
            if (command == null){
                System.out.println("Invalid option");
                continue;
            }
            command.execute();
        }
    }
}
