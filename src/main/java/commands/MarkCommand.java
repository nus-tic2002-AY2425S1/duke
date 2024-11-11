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
 * <p>
 * The MarkCommand class is responsible for updating the status of a
 * specified task in the task list to indicate that it has been completed.
 * It provides feedback to the user about the result of the operation.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE +
            OPEN_ANGLE_BRACKET + Constants.TASK_NUMBER + CLOSE_ANGLE_BRACKET;
    public static final String MESSAGE_MARK_SUCCESS = "Nice! I've marked this task as done:";

    protected final int taskNumber;

    /**
     * Creates a MarkCommand with the specified task number.
     *
     * @param taskNumber represents the 1-based index of the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
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

        int inputTaskNumber = getTaskNumber();
        int taskListSize = taskList.getSize();
        boolean isTaskListEmpty = taskList.isEmpty();

        if (isTaskListEmpty) {
            throw new CommandException(Messages.MESSAGE_EMPTY_TASKLIST);
        }

        int indexToMark = inputTaskNumber - 1;
        Task taskToMark;
        try {
            taskToMark = taskList.getTask(indexToMark);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(
                    Messages.ERROR_TASK_NONEXISTENT,
                    String.format("%s %s %s", Messages.MESSAGE_NONEXISTENT_TASK_PRE,
                            inputTaskNumber, Messages.MESSAGE_NONEXISTENT_TASK_POST),
                    String.format("%s %s.", Messages.MESSAGE_ENTER_VALID_TASK_NUMBER, taskListSize)
            );
        }

        final String MESSAGE_ALREADY_MARKED = "The task `" + taskToMark +
                "` is already marked as done. No action done.";

        String[] messages;

        boolean isMarkedSuccess = taskList.markTask(indexToMark);
        if (isMarkedSuccess) {
            storage.saveTasks(taskList);
            messages = new String[]{MESSAGE_MARK_SUCCESS, ui.formatSpace(2) + taskToMark};
        } else {
            messages = new String[]{MESSAGE_ALREADY_MARKED};
        }

        ui.printMessage(messages);
    }
}
