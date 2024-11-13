package josbot;

import java.io.FileNotFoundException;
import josbot.commands.Command;
import josbot.commands.ReminderCommand;
import josbot.parser.Parser;
import josbot.storage.FileStorage;
import josbot.task.TaskList;
import josbot.ui.UI;

public class JosBot {

    private UI ui;
    private FileStorage fileStorage;
    private TaskList taskList;

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
    }

    public void run(){
        Parser parser;
        ReminderCommand command = new ReminderCommand();
        ui.showGreeting("Dash");
        ui.showReminderMessage();
        ui.showTaskLists(command.sortTaskDate(taskList), false);
        ui.showGreeting("Start");
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showGreeting("Dash");
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, fileStorage);
                isExit = c.isExit();
            } catch (JosBotException e) {
                ui.showError(e.getMessage());
            } catch(IndexOutOfBoundsException e) {
                ui.showIndexOutofBoundError();
            }
             finally {
                ui.showGreeting("Dash");
            }
        }
        ui.showGreeting("End");
    }

    public static void main(String[] args) {
        String filepath = "src/data/JosBotList.txt";
        new JosBot(filepath).run();
    }
}
