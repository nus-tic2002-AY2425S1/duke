package commands;

import common.Messages;
import storage.Storage;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * Represents a command to list all tasks in the task list,
 * The ListCommand class is responsible for displaying the tasks currently stored in the task list.
 * It provides feedback to the user whether the list is empty or contains tasks.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_PRE = Messages.HERE_ARE_THE + " tasks" + Messages.MESSAGE_IN_YOUR_LIST_COLON;
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
        assertExecuteParams(taskList, ui, storage);
        ui.printTaskListMessage(taskList, MESSAGE_EMPTY_LIST, MESSAGE_PRE);
    }
}
