package denny.main;

import denny.command.Command;
import denny.exception.DennyException;
import denny.parser.Parser;
import denny.storage.Storage;
import denny.ui.Ui;
import denny.task.TaskList;

import java.io.IOException;

/**
 * Main class for the Denny task management application.
 * Handles initialization of components and the main execution loop.
 */
public class Denny {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Initializes the task management application with the specified storage location.
     * @param filePath Path to the file where tasks will be stored
     */
    public Denny(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        ui.showWelcome();
        try {
            tasks = new TaskList(storage.loadTasks());
            ui.showTasksLoaded(tasks.size());
        } catch (IOException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main application loop that processes user commands until exit.
     */
    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DennyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Entry point of the application.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        new Denny("data/tasks.txt").run();
    }
}
