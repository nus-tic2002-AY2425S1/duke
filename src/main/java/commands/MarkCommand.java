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
 * Represents a command to mark a task as done in the task list.
 * The MarkCommand class is responsible for updating the status of a
 * specified task in the task list to indicate that it has been completed.
 * It provides feedback to the user about the result of the operation.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE +
        OPEN_ANGLE_BRACKET + Constants.TASK_NUMBER + CLOSE_ANGLE_BRACKET;
    protected static final String MESSAGE_MARK_SUCCESS = "Nice! I've marked this task as done:";

    protected final int taskNumber;

    /**
     * Creates a MarkCommand with the specified task number.
     *
     * @param taskNumber represents the 1-based index of the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
//        assert taskNumber > 0 : "Task number must be greater than 0";
        this.taskNumber = taskNumber;
    }

    /**
     * Returns the task number specified in the command.
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Executes the command to mark the specified task as done in the task list.
     *
     * @param taskList represents the list of tasks to mark the task in.
     * @param ui       represents the user interface to interact with the user.
     * @param storage  represents the storage to save the updated task list.
     * @throws CommandException          if the task number is invalid or the task does not exist.
     * @throws StorageOperationException if an error occurs while saving the task list to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {
        assertExecuteParams(taskList, ui, storage);

        Task taskToMark = taskList.getTaskForOperation(getTaskNumber());
        assert taskToMark != null : "Task to mark should not be null";

        final String MESSAGE_ALREADY_MARKED = Messages.THE_TASK + SPACE + Constants.BACKTICK + taskToMark +
            Constants.BACKTICK + SPACE + Messages.ALREADY_MARKED_AS + SPACE + Messages.DONE +
            Constants.DOT + SPACE + Messages.NO_ACTION_DONE;

        String[] messages;

        boolean isMarkedSuccess = taskList.markTask(taskToMark);
        if (isMarkedSuccess) {
            storage.saveTasks(taskList);
            messages = new String[]{MESSAGE_MARK_SUCCESS, ui.getSpace(false, true) + taskToMark};
        } else {
            throw new CommandException(MESSAGE_ALREADY_MARKED);
        }

        ui.printMessage(messages);
    }
}
