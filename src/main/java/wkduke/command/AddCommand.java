package wkduke.command;

import wkduke.exception.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

/**
 * Represents a command to add a task to the task list.
 * This is an abstract class to be extended by specific task-adding commands, such as ToDo, Deadline, and Event.
 */
public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD_TODO = "todo";
    public static final String COMMAND_WORD_DEADLINE = "deadline";
    public static final String COMMAND_WORD_EVENT = "event";
    public static final String MESSAGE_SUCCESS_PRE = "Got it. I've added this task:";
    public static final String TASK_PLACEHOLDER = "  %s";
    public static final String MESSAGE_SUCCESS_POST = "Now you have %s tasks in the list.";
    protected Task task;

    /**
     * Executes the add command by adding a task to the task list, saving it to storage, and displaying a success message.
     *
     * @param taskList The task list to which the task will be added.
     * @param ui       The user interface for displaying messages to the user.
     * @param storage  The storage where the updated task list will be saved.
     * @throws StorageOperationException if there is an error with saving the task list to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        taskList.addTask(task);
        storage.save(taskList);
        ui.printMessages(
                MESSAGE_SUCCESS_PRE,
                String.format(TASK_PLACEHOLDER, task.toString()),
                String.format(MESSAGE_SUCCESS_POST, taskList.size())
        );
    }
}
