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

public class JosBot {

    private UI ui;
    private FileStorage fileStorage;
    private TaskList taskList;
    private static String filepath = "src/data/JosBotList.txt";

    public JosBot(String filePath)
    {
        ui = new UI();
        fileStorage = new FileStorage(filePath);
        try {
            taskList = new TaskList(fileStorage.load());
        } catch (JosBotException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        } catch (FileNotFoundException e) {
            ui.showFileNotFoundError();
        }
          catch (IOException e) {
            ui.showLoadingError();
          }
    }

    public void run(){
        CommandParser parser;
        ui.showLine();
        ReminderCommand command = new ReminderCommand();
        ui.showGreeting("Dash");
        ui.showReminderMessage();
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
            } catch(IndexOutOfBoundsException e) {
                ui.showIndexOutofBoundError();
            }
              catch(DateTimeException e) {
                ui.showInvalidDateTime();
            }
              catch(NumberFormatException e) {
                ui.showNumberFormatError();
              }
             finally {
                ui.showLine();
            }
        }
        ui.showGreeting("End");
    }

    public static void main(String[] args) {
        new JosBot(filepath).run();
    }
}