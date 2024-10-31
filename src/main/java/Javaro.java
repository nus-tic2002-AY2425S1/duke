import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Javaro {
    
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Javaro() {
        
        // Initialize Ui
        ui = new Ui();
        
        // Initialize Storage
        try {
            storage = new Storage();
            taskList = storage.loadTasks();
        } catch (StorageOperationException storageOperationException) {
            // TODO Auto-generated catch block
            storageOperationException.printStackTrace();
            // ui.showError(storageOperationException);
        } catch (IOException | FileContentException | TaskListDecoderException loadTaskException) {
            // ui.showLoadingError();
            // System.out.println(loadTaskException.getMessage());
            taskList = new TaskList();
        }

        System.out.println("taskList: " + taskList);
    }

    public void run() {
        ui.showWelcome();
        boolean isBye = false;
        while (!isBye) {
            try {
                String userInput = ui.readInput();
                // ui.showLine();
                Command command = Parser.parse(userInput);
                // System.out.println("taskList: " + taskList);
                command.execute(taskList, ui, storage);
                // command.execute(taskList, ui, storage);
                isBye = command.isBye();
            } catch (CommandException e) {
                // ui.showError(e.getMessage());
                System.out.println("A command exception occurred");
            } finally {
                // ui.showLine();
            }
        }
        
    }

    public static void main(String[] args) {
        new Javaro().run();
    }
}
