package alice;

import alice.command.Command;
import alice.exception.NoArgsException;
import alice.parser.Parser;
import alice.storage.Storage;
import alice.task.*;
import alice.ui.Ui;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AliceTest {
    private static TaskList tasks;
    private static Storage storage;
    private static Ui ui;


    public AliceTest(String filePath) {
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
    @Test
    void run() {
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
        //assertEquals();
    }

    public static void main(String[] args) {
        new AliceTest("data/tasks.txt").run();
    }
}
