package javaro;

import commands.Command;
import common.Messages;
import exception.CommandException;
import exception.FileContentException;
import exception.StorageOperationException;
import exception.TaskListDecoderException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;

/**
 * Represents the main application class / entry point for the Javaro task management system.
 * The class initializes the user interface, storage, and task list, and runs the main application logic.
 * It processes the commands issued by the user.
 * It reads commands from the user, parses and executes them, and displays appropriate feedback to the user.
 * The application handles errors related to task storage, command parsing, and general exceptions gracefully.
 */
public class Javaro {

    private final Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a Javaro instance.
     * Setting up the Javaro application initializing the user interface, storage, and task list.
     * It handles any initialization errors that occur.
     * If it fails to load tasks list from storage (tasks file),
     * then it initializes an empty task list and displays an error message.
     */
    public Javaro() {

        // Initialize Ui
        ui = new Ui();

        // Initialize Storage and load tasks to TaskList
        try {
            storage = new Storage();
            taskList = storage.loadTasks();     // Attempt to load task from storage (tasks file)
        } catch (StorageOperationException | FileContentException |
                 TaskListDecoderException | CommandException e) {
            // Handle common exceptions related to task loading
            // storageOperationException.printStackTrace();
            ui.showError(e.getMessageList());       // Display the error message to the user
            taskList = new TaskList();      // Initialize an empty task list
        } catch (IOException ioe) {
            // Handle IOExceptions (e.g., file not found)
            String[] messages = {ioe.getMessage()};
            ui.printMessage(messages);
            taskList = new TaskList();      // Initialize an empty task list
        }

    }

    public Ui getUi() {
        return ui;
    }

    public Storage getStorage() {
        return storage;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Runs the main application logic.
     * Repeatedly reads input from the user, parses the input received from the user and
     * executes it until the user issues a command to exit, which stops the program.
     * Any exceptions that occur will be caught and an error message will be displayed to the user.
     */
//    public void run() {
//        // Display welcome message
//        ui.showWelcome();
//        boolean isBye = false;
//
//        // Main loop for reading user input and executing commands
//        while (!isBye) {
//            try {
//                // Read input from the user
//                String userInput = ui.readInput();
//                // ui.showLine();
//
//                // Parse and execute the command
//                Command command = Parser.parse(userInput);
//                command.execute(taskList, ui, storage);
//
//                // Check if the command is a "bye" command to exit the loop
//                isBye = command.isBye();
//            } catch (CommandException e) {
//                // Handle command parsing or execution errors
//                // System.out.println("CommandException caught: " + e.getMessage());
//                ui.showError(e.getMessageList());
//            } catch (StorageOperationException e) {
//                // Handle storage operation errors
//                // System.out.println("StorageOperationException caught: " + e.getMessage());
//                ui.showError(e.getMessageList());
//            }
//        }
//
//    }

    public boolean runUserInput(String userInput) {
        try {
            // Parse and execute the command
            Command command = Parser.parse(userInput);
            command.execute(taskList, ui, storage);

            // Check if the command is a "bye" command to exit the loop
            return command.isBye();
        } catch (CommandException e) {
            // Handle command parsing or execution errors
            // System.out.println("CommandException caught: " + e.getMessage());
            ui.showError(e.getMessageList());
            return false;
        } catch (StorageOperationException e) {
            // Handle storage operation errors
            // System.out.println("StorageOperationException caught: " + e.getMessage());
            ui.showError(e.getMessageList());
            return false;
        }
    }

    /**
     * Initializes the Javaro program and starts the main loop.
     * The main entry point of the Javaro application.
     * This method is called when the program is executed from the command-line.
     *
     * @param args command line arguments (not used here).
     */
//    public static void main(String[] args) {
//        new Javaro().run();
//    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) {
        return "Duke heard: " + userInput;
    }
}
