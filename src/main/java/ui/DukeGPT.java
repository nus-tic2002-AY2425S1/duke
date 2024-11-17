package ui;


import command.CommandHandler;
import command.ListCommandHandler;
import command.MarkCommandHandler;
import command.UnmarkCommandHandler;
import command.DeleteCommandHandler;
import command.FindCommandHandler;
import command.UpdateCommandHandler;
import command.CommandType;
import command.CommandFactory;

import exception.DukeException;
import storage.FileProcessor;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

import static output.OutputHandler.printError;
import static output.OutputHandler.printGreetings;
import static output.OutputHandler.printGoodbye;
import static output.OutputHandler.printAddedItems;

/**
 * Represent the main class for DukeGPT Application
 */
public class DukeGPT {
    private static final String chatbotName = "DukeGPT";
    private static final String fileLocation = "./data/dukegpt.txt";
    private static List<Task> tasks = new ArrayList<>();
    private static Map<String, CommandHandler> commandHandlerMapping = new HashMap<>();

    /**
     * Initialises the command handlers by mapping commands to their respective handlers
     */
    //Solution below adapted from https://stackoverflow.com/questions/37721799/factory-implementation-with-enum
    private static void initCommandHandlers() {
        commandHandlerMapping.put(CommandType.LIST.name().toLowerCase(), new ListCommandHandler());
        commandHandlerMapping.put(CommandType.MARK.name().toLowerCase(), new MarkCommandHandler());
        commandHandlerMapping.put(CommandType.UNMARK.name().toLowerCase(), new UnmarkCommandHandler());
        commandHandlerMapping.put(CommandType.DELETE.name().toLowerCase(), new DeleteCommandHandler());
        commandHandlerMapping.put(CommandType.FIND.name().toLowerCase(), new FindCommandHandler());
        commandHandlerMapping.put(CommandType.UPDATE.name().toLowerCase(), new UpdateCommandHandler());
    }

    private static List<Task> loadTasksFromFile() {
        FileProcessor fileProcessor = new FileProcessor(fileLocation);
        try {
            return fileProcessor.load();
        } catch (DukeException e) {
            printError("OOPS!! Error initialising save file!" + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void saveTasksToFile() {
        FileProcessor fileProcessor = new FileProcessor(fileLocation);
        try {
            fileProcessor.save(tasks);
        } catch (DukeException e) {
            printError("OOPS!! Error saving to file! " + e.getMessage());
        }
    }

    public static void handleUserInput(String userInput) throws DukeException{
        if(userInput.equalsIgnoreCase("bye")){
            printGoodbye();
            System.exit(0);
        }
        else if(userInput.trim().isEmpty()){
            throw new DukeException("OOPS!!! Input cannot be empty!");
        }
        String[] inputParts = userInput.split(" ", 2);
        CommandHandler commandHandler = commandHandlerMapping.get(inputParts[0].toLowerCase());

        if(commandHandler != null){
            commandHandler.handle(userInput, tasks);
        }else {
            Task newTask  = CommandFactory.generateTask(userInput);
            tasks.add(newTask);
            printAddedItems(newTask, tasks);
        }
    }

    /**
     * The entry point of DukeGPT application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initCommandHandlers();
        assert !commandHandlerMapping.isEmpty() : "Command handlers should be initialized";
        printGreetings(chatbotName);
        tasks = loadTasksFromFile();

        while (true){
            String userInput = scanner.nextLine().trim();
            try{
                handleUserInput(userInput);
                saveTasksToFile();
            } catch (DukeException e){
                printError(e.getMessage());
            } catch (NumberFormatException e){
                printError("OOPS!!! Please provide a valid task number!");
            } catch (Exception e){
                printError("OOPS!!! Something went wrong, please try again");
            }
        }

    }
}
