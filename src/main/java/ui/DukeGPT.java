package ui;

import java.util.*;

import command.CommandHandler;
import command.ListCommandHandler;
import command.MarkCommandHandler;
import command.UnmarkCommandHandler;
import command.DeleteCommandHandler;
import command.FindCommandHandler;
import command.UpdateCommandHandler;
import command.CommandFactory;

import exception.DukeException;
import storage.FileProcessor;
import tasks.Task;

import static output.OutputHandler.printError;
import static output.OutputHandler.printGreetings;
import static output.OutputHandler.printGoodbye;
import static output.OutputHandler.printAddedItems;

/**
 * Main class for DukeGPT Application
 */
public class DukeGPT {
    private static String chatbotName = "DukeGPT";
    private static String fileLocation = "./data/dukegpt.txt";
    private static List<Task> tasks = new ArrayList<>();
    private static Map<String, CommandHandler> commandHandlerMapping = new HashMap<>();

    /**
     * Initialise the command handlers
     */
    private static void initCommandHandlers() {
        commandHandlerMapping.put("list", new ListCommandHandler());
        commandHandlerMapping.put("mark", new MarkCommandHandler());
        commandHandlerMapping.put("unmark", new UnmarkCommandHandler());
        commandHandlerMapping.put("delete", new DeleteCommandHandler());
        commandHandlerMapping.put("find", new FindCommandHandler());
        commandHandlerMapping.put("update", new UpdateCommandHandler());
    }

    /**
     * Main method for DukeGPT Application
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initCommandHandlers();
        printGreetings(chatbotName);
        FileProcessor fileProcessor = new FileProcessor(fileLocation);
        try{
            tasks = fileProcessor.load();
        } catch (DukeException e){
            printError("OOPS!! Error initialising save file!" + e.getMessage());
        }

        while (true){
            String userInput = scanner.nextLine().trim();
            try{
                if(userInput.equalsIgnoreCase("bye")){
                    printGoodbye();
                    break;
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
                fileProcessor.save(tasks);
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
