package josbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.DateTimeException;

import josbot.commands.Command;
import josbot.commands.ReminderCommand;
import josbot.parser.CommandParser;
import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

/**
 * Main Program of JosBot
 */

public class JosBot {

    private final UI ui;
    private final FileStorage fileStorage;
    private TaskList taskList;
    private static final String filepath = "./src/data/JosBotList.txt";

    public JosBot(String filePath) {
        ui = new UI();
        fileStorage = new FileStorage(filePath);
        try {
            taskList = new TaskList(FileStorage.load());
        } catch (JosBotException e) {
            ui.showError("loading_error");
            taskList = new TaskList();
        } catch (FileNotFoundException e) {
            ui.showError("file_not_found_error");
        } catch (IOException e) {
            ui.showError("loading_error");
        }
    }

    public void run() {
        ui.showLine();
        ui.showReminderMessage();
        ReminderCommand command = new ReminderCommand();
        ui.showTaskLists(command.sortTaskDate(taskList), false);
        ui.showGreeting("Start");
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = CommandParser.parse(fullCommand);
                c.execute(taskList, ui, fileStorage);
                isExit = c.isExit();
            } catch (JosBotException e) {
                ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showIndexOutofBoundError();
            } catch (DateTimeException e) {
                ui.showInvalidDateTime();
            } catch (NumberFormatException e) {
                ui.showNumberFormatError();
            } finally {
                ui.showLine();
            }
        }
        ui.showGreeting("End");
    }

    public static void main(String[] args) {
        new JosBot(filepath).run();
    }
}
