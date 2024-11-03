import commands.Command;
import exception.CommandException;
import exception.FileContentException;
import exception.StorageOperationException;
import exception.TaskException;
import exception.TaskListDecoderException;
import exception.DateTimeParserException;
import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;   

import java.io.IOException;

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
        } catch (StorageOperationException | FileContentException | 
                 TaskListDecoderException | DateTimeParserException e) {
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
            } catch (DateTimeParserException e) {
                // System.out.println("StorageOperationException caught: " + e.getMessage());
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
