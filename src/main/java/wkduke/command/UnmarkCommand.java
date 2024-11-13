package wkduke.command;

import wkduke.common.Messages;
import wkduke.exception.CommandOperationException;
import wkduke.exception.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " {taskNumber}";
    public static final String MESSAGE_SUCCESS = "OK, I've marked this task as not done yet:";
    public static final String MESSAGE_FAILED = "This task is not yet marked as done:";
    public static final String TASK_PLACEHOLDER = "  %s";
    protected final int taskNumber;

    /**
     * Constructs an UnmarkCommand with the specified task number.
     *
     * @param taskNumber The 1-based index of the task to be marked as not done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Checks if this UnmarkCommand is equal to another object.
     * An UnmarkCommand is considered equal if it is of the same type and has the same task number.
     *
     * @param obj The object to compare with this UnmarkCommand.
     * @return {@code true} if the specified object is an UnmarkCommand with an equal task number; otherwise, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnmarkCommand command)) {
            return false;
        }
        return taskNumber == command.taskNumber;
    }

    /**
     * Executes the unmark command by marking the specified task as not done, saving the updated
     * task list to storage, and displaying a success or failure message.
     *
     * @param taskList The task list containing the task to be marked as not done.
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
            boolean isUpdated = taskList.unmarkTask(taskIndex);

            if (isUpdated) {
                storage.save(taskList);
                ui.printMessages(
                        MESSAGE_SUCCESS,
                        String.format(TASK_PLACEHOLDER, task.toString())
                );
            } else {
                ui.printMessages(
                        MESSAGE_FAILED,
                        String.format(TASK_PLACEHOLDER, task.toString())
                );
            }
        } catch (IndexOutOfBoundsException e) {
            throw new CommandOperationException(
                    Messages.MESSAGE_INVALID_TASK_NUMBER,
                    String.format("Command='unmark', TaskNumber='%s'", taskNumber)
            );
        }
    }
}
