package wkduke.command.read;

import wkduke.command.Command;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;
import wkduke.ui.UiTaskGroup;

import java.util.List;

import static wkduke.common.Messages.MESSAGE_TASK_LIST_TIPS;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String MESSAGE_SUCCESS = "Here are the tasks in your list:";
    private static final String MESSAGE_FAILED = "Your task list is currently empty.";

    /**
     * Checks if this ListCommand is equal to another object.
     * A ListCommand is considered equal to another object if the specified object is also a ListCommand.
     *
     * @param obj The object to compare with this ListCommand.
     * @return {@code true} if the specified object is a ListCommand; otherwise, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }

    /**
     * Executes the list command by retrieving all tasks from the task list
     * and displaying them to the user. If the task list is empty, a message
     * indicating this is displayed.
     *
     * @param taskList The task list containing all tasks.
     * @param ui       The user interface for displaying messages to the user.
     * @param storage  The storage being used (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "Precondition failed: 'taskList' cannot be null";
        assert ui != null : "Precondition failed: 'ui' cannot be null";
        List<Task> tasks = taskList.getAllTask();
        if (tasks.isEmpty()) {
            ui.printMessages(MESSAGE_FAILED);
            return;
        }
        assert !tasks.isEmpty() : "Postcondition failed: 'tasks' cannot be empty";
        ui.printUiTaskGroup(taskList, new UiTaskGroup(String.format(MESSAGE_SUCCESS), MESSAGE_TASK_LIST_TIPS, tasks));
    }
}
