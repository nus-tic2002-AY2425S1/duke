package Chad;

import Chad.Command.*;
import Chad.Exception.*;
import Chad.Parser.*;
import Chad.Storage.*;
import Chad.TaskList.*;
import Chad.Ui.*;

public class Chad {

    private Storage storage;
    private TaskList tasks;
    private TextUi ui;
    private  boolean  isExit;
    static String filePath = "./data/chad.txt";

    public Chad(String filePath) {
        ui = new TextUi(); // UI interface
        tasks = new TaskList(); // Initialize tasks regardless of storage success
        initializeStorage(filePath); // Handle storage initialization
    }

    private void initializeStorage(String filePath) {
        try {
            storage = new Storage(filePath);
        } catch (ChadException e) {
            ui.showError("Failed to initialize storage: " + e.getMessage());
            // Optionally provide fallback behavior
            // Tasks can be managed in memory without loading from file
        }
    }

    public void run() {
        ui.showWelcome();
        isExit = false;
        while (!isExit) {
            executeUserCommand();
        }
    }

    private void executeUserCommand() {
        try {
            String fullCommand = ui.readCommand();
            ui.showLine(); // Show the divider line
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
        } catch (ChadException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }
    }

    public void stop() {
        ui.showBye();
    }

    public static void main(String[] args) {
        new Chad(filePath).run();
    }
}
