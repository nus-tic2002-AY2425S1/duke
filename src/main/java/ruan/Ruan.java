package ruan;

import ruan.ui.Ui;
import ruan.storage.*;
import ruan.task.*;
import ruan.exception.*;
import ruan.command.*;
import ruan.parser.*;

/**
 * Main class for the Ruan application, where the program is initialized and run
 * Handles loading tasks, managing user interactions, and executing commands
 */

public class Ruan {    

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    //default path for tasks text to be stored
    private static final String FILE_PATH = "./src/main/java/ruan/data/ruan.txt"; 

    /**
     * Initializes the Ruan application by setting up the UI, storage, and task list
     * Attempts to load the task list from the given file path
     * @param filePath File path where the tasks are stored
     * @throws RuanException If there are issues loading the task list
     */
    public Ruan(String filePath) throws RuanException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (RuanException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Ruan application, providing a loop to accept and execute user commands
     * Prints a welcome message, reads user input, and executes commands until the user exits
     */
    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (RuanException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * The main method to start the Ruan application
     * @param args Command line arguments
     * @throws RuanException If there are issues initializing the application
     */
    public static void main(String[] args) throws RuanException {
        new Ruan(FILE_PATH).run();
    }
    
}
