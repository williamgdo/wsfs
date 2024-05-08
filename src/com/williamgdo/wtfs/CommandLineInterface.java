package com.williamgdo.wtfs;

//import java.util.Arrays;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CommandLineInterface {
    Scanner scanner = new Scanner(System.in);
    boolean loopOptions = true;

    /* private boolean inputEqualsListOfString(String input, String[] commandList) {
        boolean isEquals = false;
        for (String command : commandList) {
            if (input.equalsIgnoreCase(command)) {
                isEquals = true;
                break;
            }
        }
        return isEquals;
    } */

    /*private void loopBetweenOptions(boolean continueLoop, Function askForCommands) {
        boolean loop = true;
        String nextCommand = "";

        do {
            askForCommands();
        } while (loop);
    }*/

    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    public void printHelp() {
        System.out.println("╒═════════════════════════════════════════════════════════════╕");
        System.out.println("|        Welcome to William's Simple File System (WSFS).      |");
        System.out.println("|         Type the number inside the brackets or the          |");
        System.out.println("|     command name (case independent) in order to choose.     |");
        System.out.println("|               Type 'exit' to close the CLI.                 |");
        System.out.println("╘═════════════════════════════════════════════════════════════╛");
    }

    private String defaultOptionsCli(String userInput) {
        return switch (userInput.toLowerCase()) {
            case "exit" ->
//                exitCli();
                    "exit";
            case "help" -> {
                printHelp();
                yield "";
            }
            default -> {
                System.out.println("Command not found. Type 'help' to see the instructions.");
                yield "";
            }
        };
    }

    public String createOrMountFsMenu() {
        String nextFunction;
        do {
            System.out.println("Do you want to [1] Mount or [2] Create a filesystem?");

            String userInput = getUserInput();
            nextFunction = processCreateOrMountFsCommand(userInput);
        } while (nextFunction.equalsIgnoreCase(""));
        return nextFunction;
    }
    private String processCreateOrMountFsCommand(String userInput) {
        return switch (userInput.toLowerCase().trim()) {
            case "1", "mount" ->
//                mountFs();
                    "mount";
            case "2", "create" ->
//                createFs();
                    "create";
            default -> defaultOptionsCli(userInput);
        };
    }

    public void mountFsMenu() {
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        System.out.println("Your current path is: " + path);
        System.out.println("Input the path for the filesystem: ");


        // TODO check if absolute path is correct way

        String userInput = getUserInput();
        // TODO: mount
        File file = new File(userInput);

        System.out.println("File exists = " + file.exists());
        System.out.println("Is folder = " + file.isDirectory());
    }

    public void createFsMenu() {
        String nextFunction;
        do {
            System.out.println("Type [3] Info to see the default settings of a superblock.");
            System.out.println("Do you want to create a filesystem with [1] Default settings " +
                    "for a superblock or [2] Customize yourself?");

            String userInput = getUserInput();
            nextFunction = processCreateFsMenuCommand(userInput);
        } while (nextFunction.equalsIgnoreCase(""));

        if (nextFunction.equalsIgnoreCase("exit"))
            exitCli();

        if (nextFunction.equalsIgnoreCase("default"))
//            createFs();
            System.out.println("createFs()");

        if (nextFunction.equalsIgnoreCase("custom")) {
            // TODO : get user input
            // TODO : getCustomFsFromUserInput()
        }

        // TODO : Mount system (?)
        // ask if user wants to mount?
    }

    public String processCreateFsMenuCommand(String userInput) {
        return switch (userInput.toLowerCase().trim()) {
            case "1", "default" ->
                    "default";
            case "2", "custom" ->
                    "custom";
            default -> defaultOptionsCli(userInput);
        };
    }

//    public Superblock getCustomFsFromUserInput() {
//
//    }

    public void exitCli() {
        // debug purposes
        new Exception().printStackTrace(System.out);
        loopOptions = false;

        System.out.println("Exiting WSFS CLI...");
    }
}

