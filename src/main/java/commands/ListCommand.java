package commands;
import java.util.ArrayList;
import java.util.Arrays;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_PRE = "Here are the tasks in your list:";
    public static final String MESSAGE_EMPTY_LIST = "Good job! You're all caught up!";

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // https://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
        ArrayList<String> messageList = new ArrayList<>(Arrays.asList(MESSAGE_PRE));
        
        int taskListSize = taskList.getSize();
        
        if (taskList.isEmpty()) {
            String[] message = {MESSAGE_EMPTY_LIST};
            ui.printMessage(message);
            return;
        }
        
        for (int i = 0; i < taskListSize; i++) {
            Task current = taskList.getTask(i);         // taskList.get(i) contains the checkbox
            String index = Integer.toString(i + 1);
            String line = index + ". " + current;
            messageList.add(line);
        }
        
        ui.printMessage(messageList);
    }
}
