import ruan.ui.Ui;
import ruan.storage.*;
import ruan.task.*;
import ruan.exception.*;
import ruan.command.*;
import ruan.parser.*;

public class Ruan {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    //default path for tasks text to be stored
    private static final String FILE_PATH = "./src/main/java/ruan/data/ruan.txt"; 

    //method to initial and load text file
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

    //method to initiate program
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

    public static void main(String[] args) throws RuanException {
        new Ruan(FILE_PATH).run();
    }
    
}
