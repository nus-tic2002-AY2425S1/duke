import java.io.IOException;

public class Denny {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

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

    public static void main(String[] args) {
        new Denny("data/tasks.txt").run();
    }
}
