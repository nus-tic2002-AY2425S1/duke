package javaro;

import commands.Command;
import exception.CommandException;
import exception.FileContentException;
import exception.StorageOperationException;
import exception.TaskListDecoderException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private String commandType;

    private final static Logger LOGGER = Logger.getLogger(Javaro.class.getName());

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
            LOGGER.log(Level.WARNING, e.getMessageList().toString());
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

    public String getCommandType() {
        return commandType;
    }

    public boolean runUserInput(String userInput) {
        assert userInput != null : "User input should not be null";
        try {
            // Parse and execute the command
            Command command = Parser.parse(userInput);
            command.execute(taskList, ui, storage);

            commandType = command.getClass().getName();

            // Check if the command is a "bye" command to exit the loop
            return command.isBye();
        } catch (CommandException e) {
            // Handle command parsing or execution errors
            commandType = "Error";
            ui.showError(e.getMessageList());
            return false;
        } catch (StorageOperationException e) {
            // Handle storage operation errors
            commandType = "Error";
            ui.showError(e.getMessageList());
            return false;
        }
    }
}
