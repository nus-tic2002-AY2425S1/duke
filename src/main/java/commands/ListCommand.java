package commands;

import common.Constants;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a command to list all tasks in the task list,
 * <p>
 * The ListCommand class is responsible for displaying the tasks currently stored in the task list.
 * It provides feedback to the user whether the list is empty or contains tasks.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_PRE = "Here are the tasks in your list:";
    public static final String MESSAGE_EMPTY_LIST = "Good job! You're all caught up!";

    /**
     * Executes the command to display the tasks in the task list.
     *
     * @param taskList represents the list of tasks to display.
     * @param ui       represents the user interface to interact with the user.
     * @param storage  represents the storage (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // Initialize a list to hold the messages for display to the user
        // Initialization of ArrayList referenced from https://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
        ArrayList<String> messages = new ArrayList<>(List.of(MESSAGE_PRE));

        int taskListSize = taskList.getSize();

        // Check if the task list is empty
        if (taskList.isEmpty()) {
            String[] message = {MESSAGE_EMPTY_LIST};
            ui.printMessage(message);
            return;
        }

        // Iterate through the task list and format each task for display
        for (int i = 0; i < taskListSize; i++) {
            Task current = taskList.getTask(i);
            String index = Integer.toString(i + 1);
            String line = index + Constants.DOT_SPACE + current;
            messages.add(line);
        }

        // Print the complete message list
        ui.printMessage(messages);
    }
}
