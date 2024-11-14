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
     * Constructs a {@code WKDuke} instance, initializing UI, storage, and task list.
     * Displays an initialization error and exits if storage fails to load.
     */
    public WKDuke() {
        ui = new Ui();
        try {
            storage = new Storage();
            taskList = storage.load();
        } catch (StorageOperationException | FileContentException e) {
            ui.showInitError(e);
            System.exit(-1);
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
     * Runs the main application loop, showing the welcome message and processing user commands.
     * Catches and handles exceptions related to storage and command execution.
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
                System.exit(-1);
            } catch (WKDukeException e) {
                ui.showError(e);
            }
        }
    }
}
