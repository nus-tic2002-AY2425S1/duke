package commands;

import common.Messages;
import exception.CommandException;
import exception.StorageOperationException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 * 
 * <p>
 * The MarkCommand class is responsible for updating the status of a specified task in the task list to indicate that it has been completed. 
 * It provides feedback to the user about the result of the operation.
 * </p>
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <task number>";
    public static final String MESSAGE_MARK_SUCCESS = "Nice! I've marked this task as done:";
    public static final String MESSAGE_EMPTY_TASKLIST = "The task list is empty. Please add a task first.";
    
    protected int taskNumber;
    
    /**
     * Constructor to create a MarkCommand with the specified task number.
     * @param taskNumber represents the 1-based index of the task to be marked as done
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    /**
     * Retrieves the task number specified in the command.
     * @return the task number
     */
    public int getTaskNumber() {
        return taskNumber;
    }
    
    /**
     * Executes the command to mark the specified task as done in the task list. 
     * @param taskList represents the list of tasks to mark the task in
     * @param ui represents the user interface to interact with the user
     * @param storage represents the storage to save the updated task list
     * @throws CommandException if the task number is invalid or the task does not exist
     * @throws StorageOperationException if an error occurs while saving the task list to storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {
        
        int taskNumber = getTaskNumber();
        int taskListSize = taskList.getSize();

        int indexToMark = taskNumber - 1;
        Task taskToMark = null;
        try {
            taskToMark = taskList.getTask(indexToMark);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(
                Messages.ERROR_TASK_NONEXISTENT,
                String.format("%s %s %s", Messages.MESSAGE_NONEXISTENT_TASK_PRE, taskNumber, Messages.MESSAGE_NONEXISTENT_TASK_POST),
                String.format("%s %s.", Messages.MESSAGE_ENTER_VALID_TASK_NUMBER, taskListSize)
            );
        }
        final String MESSAGE_ALREADY_MARKED = "The task `" + taskToMark + "` is already marked as done. No action done.";
        
        String[] messageList = null;

        boolean isMarkedSuccess = taskList.markTask(indexToMark);
        if (isMarkedSuccess == true) {
            storage.saveTasks(taskList);
            messageList = new String[]{MESSAGE_MARK_SUCCESS, ui.formatSpace(2) + taskToMark};
        } else {
            messageList = new String[]{MESSAGE_ALREADY_MARKED};
        }

        ui.printMessage(messageList);
    }
}
