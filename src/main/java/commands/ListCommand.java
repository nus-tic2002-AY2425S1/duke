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

        // Check if the task list is empty
        ui.printEmptyListMessage(taskList, MESSAGE_EMPTY_LIST);

        ArrayList<String> messages = ui.getTaskMessages(MESSAGE_PRE, taskList);

        // Print the complete message list
        ui.printMessage(messages);
    }
}
