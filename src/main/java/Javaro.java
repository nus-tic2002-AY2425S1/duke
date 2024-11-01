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
        } catch (StorageOperationException | FileContentException | TaskListDecoderException e) {
            // storageOperationException.printStackTrace();
            ui.showError(e.getMessageList());
            taskList = new TaskList();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            taskList = new TaskList();
        }

    }

    public void run() {
        ui.showWelcome();
        boolean isBye = false;
        while (!isBye) {
            try {
                String userInput = ui.readInput();
                // ui.showLine();
                Command command = Parser.parse(userInput);
                command.execute(taskList, ui, storage);
                isBye = command.isBye();
            } catch (CommandException e) {
                // System.out.println("CommandException caught: " + e.getMessage());
                ui.showError(e.getMessageList());
            } catch (StorageOperationException e) {
                // System.out.println("StorageOperationException caught: " + e.getMessage());
                ui.showError(e.getMessageList());
            } catch (TaskException e) {
                // System.out.println("TaskException caught: " + e.getMessage());
                ui.showError(e.getMessageList());
            } finally {
                // ui.showLine();
            }
        }
        
    }

    public static void main(String[] args) {
        new Javaro().run();
    }
}
