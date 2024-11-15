package alice;

import alice.command.Command;
import alice.exception.NoArgsException;
import alice.parser.Parser;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

import java.io.IOException;

/**
 * <h1>Alice</h1>
 * The Alice class is the main class where
 * the main method is found and will be the
 * entry point of the program.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class Alice {
    private static TaskList tasks;
    private static Storage storage;
    private static Ui ui;

    //Solution below adapted from lectures
    public Alice(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    //Solution below adapted from week 8 tasks
    public static void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (IOException | NoArgsException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Alice("tasks.txt").run();
    }
}
