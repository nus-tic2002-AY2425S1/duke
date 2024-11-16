package wkduke;

import wkduke.command.Command;
import wkduke.exception.WKDukeException;
import wkduke.exception.storage.FileContentException;
import wkduke.exception.storage.StorageOperationException;
import wkduke.parser.CommandParser;
import wkduke.storage.Storage;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

/**
 * The main class for the WKDuke application.
 * Initializes the necessary components and manages the main application loop.
 */
public class WKDuke {
    private final Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructs a {@code WKDuke} instance.
     * This constructor initializes the user interface (UI), sets up storage, and loads
     * the task list. If an error occurs during storage initialization, it will display an
     * error message and terminate the application.
     */
    public WKDuke() {
        ui = new Ui();
        try {
            storage = ui.getFlexibleDataSource();
            taskList = storage.load();
        } catch (StorageOperationException | FileContentException e) {
            ui.showError(e);
            System.exit(1);
        }
    }

    /**
     * The main entry point for the WKDuke application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new WKDuke().run();
    }

    /**
     * Executes the main application loop.
     * The method displays the welcome message, reads and processes user commands,
     * and catches and handles exceptions related to storage and command execution.
     * If storage operations fail, the application will terminate with an error.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command c = CommandParser.parseCommand(userInput);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (StorageOperationException e) {
                ui.showError(e);
                System.exit(1);
            } catch (WKDukeException e) {
                ui.showError(e);
            }
        }
    }
}
