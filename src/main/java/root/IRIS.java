package root;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import commands.Command;
import tasks.Task;

public class IRIS {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public IRIS(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        
        try {
            tasks = loadTasksWithFallback(filePath); 
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList(); 
        }
    }

    private TaskList loadTasksWithFallback(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists() || !file.canRead()) {
            ui.showLoadingError();
            return new TaskList();  
        }

        ArrayList<Task> taskArrayList = storage.loadTasks(); 

        
        if (taskArrayList == null) {
            ui.showLoadingError();
            return new TaskList();  
        }
        return new TaskList(taskArrayList);  
    }

    public void run() {  
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError("An error occurred: " + e.getMessage());
            } finally {
                //ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new IRIS("iris.txt").run(); 
    }
}
