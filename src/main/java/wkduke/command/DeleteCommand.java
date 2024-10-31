package wkduke.command;

import wkduke.common.Messages;
import wkduke.exception.CommandOperationException;
import wkduke.exception.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " {taskNumber}";
    public static final String MESSAGE_SUCCESS_PRE = "Noted. I've removed this task:";
    public static final String TASK_PLACEHOLDER = "  %s";
    public static final String MESSAGE_SUCCESS_POST = "Now you have %s tasks in the list.";
    protected final int taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber The 1-based index of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the delete command by removing the specified task from the task list,
     * saving the updated list to storage, and displaying a success message.
     *
     * @param taskList The task list from which the task will be deleted.
     * @param ui       The user interface for displaying messages to the user.
     * @param storage  The storage where the updated task list will be saved.
     * @throws StorageOperationException if there is an error with saving the task list to storage.
     * @throws CommandOperationException if the specified task number is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException, CommandOperationException {
        try {
            int taskIndex = taskNumber - 1;
            Task task = taskList.getTask(taskIndex);
            taskList.deleteTask(taskIndex);
            storage.save(taskList);
            ui.printMessages(
                    MESSAGE_SUCCESS_PRE,
                    String.format(TASK_PLACEHOLDER, task.toString()),
                    String.format(MESSAGE_SUCCESS_POST, taskList.size())
            );
        } catch (IndexOutOfBoundsException e) {
            throw new CommandOperationException(
                    Messages.MESSAGE_INVALID_TASK_NUMBER,
                    String.format("Command='delete', TaskNumber='%s'", taskNumber)
            );
        }
    }
}
