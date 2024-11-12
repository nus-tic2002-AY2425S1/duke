package commands;

import common.Constants;
import common.Messages;
import exception.CommandException;
import exception.StorageOperationException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to unmark a task in the task list.
 * <p>
 * The UnmarkCommand class is responsible for updating the status of a
 * specified task in the task list to indicate that it has not been completed.
 * It provides feedback to the user about the result of the operation.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE +
        OPEN_ANGLE_BRACKET + Constants.TASK_NUMBER + CLOSE_ANGLE_BRACKET;
    public static final String MESSAGE_UNMARK_SUCCESS = "OK, I've marked this task as not done yet:";

    protected final int taskNumber;

    /**
     * Constructs an UnmarkCommand with the specified 1-based task number.
     *
     * @param taskNumber represents the 1-based index of the task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Retrieves the task number associated with the command.
     *
     * @return the 1-based index of the task.
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Executes the command to unmark the specified task as not done in the task list.
     *
     * @param taskList represents list of tasks to unmark the task in.
     * @param ui       represents the user interface to interact with the user.
     * @param storage  represents the storage to save the updated task list.
     * @throws CommandException          if the task number is invalid or the task does not exist.
     * @throws StorageOperationException if an error occurs while saving the updated task list to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {

        Task taskToUnmark = taskList.getTaskForOperation(getTaskNumber());

        final String MESSAGE_ALREADY_UNMARKED = "The task `" + taskToUnmark +
            "` is already marked as not done. No action done.";

        String[] messages;

        boolean isUnmarkedSuccess = taskList.unmarkTask(taskToUnmark);

        if (isUnmarkedSuccess) {
            storage.saveTasks(taskList);
            messages = new String[]{MESSAGE_UNMARK_SUCCESS, ui.formatSpace(Constants.TWO) + taskToUnmark};
        } else {
            messages = new String[]{MESSAGE_ALREADY_UNMARKED};
        }

        ui.printMessage(messages);
    }
}
