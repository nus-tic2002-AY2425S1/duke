package wkduke.command;

import wkduke.common.Messages;
import wkduke.exception.CommandOperationException;
import wkduke.exception.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " {taskNumber}";
    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:";
    public static final String MESSAGE_FAILED = "This task is already marked as done:";
    public static final String TASK_PLACEHOLDER = "  %s";
    protected final int taskNumber;

    /**
     * Constructs a MarkCommand with the specified task number.
     *
     * @param taskNumber The 1-based index of the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the mark command by marking the specified task as done, saving the updated
     * task list to storage, and displaying a success or failure message.
     *
     * @param taskList The task list containing the task to be marked as done.
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
            boolean updated = taskList.markTask(taskIndex);

            if (updated) {
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
                    String.format("Command='mark', TaskNumber='%s'", taskNumber)
            );
        }
    }
}
