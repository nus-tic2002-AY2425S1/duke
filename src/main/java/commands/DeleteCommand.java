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
 * Represents a command to delete a task from the task list.
 * The DeleteCommand class is responsible for removing a specified task from the task list based on its task number.
 * It also interacts with the user interface to provide feedback on the task addition and
 * saves the updated task list to storage.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE + Constants.TASK_NUMBER_IN_ANGLE_BRACKETS;
    public static final String MESSAGE_DELETE_SUCCESS_PRE = "Noted. I've removed this task:";

    protected final int taskNumber;

    /**
     * Constructs a DeleteCommand with the specified task number.
     *
     * @param taskNumber represents the 1-based index of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Returns the task number associated with this command.
     */
    public int getTaskNumber() {
        return taskNumber;
    }

    /**
     * Executes the command to delete the specified task from the task list.
     *
     * @param taskList represents the list of tasks to delete the task from.
     * @param ui       represents the user interface to interact with the user.
     * @param storage  represents the storage to save the updated task list.
     * @throws CommandException          if the task number is invalid or the task does not exist.
     * @throws StorageOperationException if an error occurs while saving the taskList to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {

        Task taskToDelete = taskList.getTaskForOperation(getTaskNumber());

        boolean isDeletedSuccess = taskList.removeTask(taskToDelete);

        int taskListSize = taskList.getSize();
        String taskWord = taskList.getTaskWord();
        final String MESSAGE_DELETE_SUCCESS_POST = Messages.MESSAGE_NOW_YOU_HAVE +
            taskListSize + taskWord + Messages.MESSAGE_IN_THE_LIST;

        String[] messages = null;
        if (isDeletedSuccess) {
            storage.saveTasks(taskList);
            messages = new String[]{MESSAGE_DELETE_SUCCESS_PRE,
                ui.getSpace(false, true) + taskToDelete, MESSAGE_DELETE_SUCCESS_POST};
        }

        ui.printMessage(messages);
    }
}
