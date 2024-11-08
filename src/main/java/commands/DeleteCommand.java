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
 * 
 * <p>
 * The DeleteCommand class is responsible for removing a specified task from the task list based on its task number. 
 * It also interacts with the user interface to provide feedback on the task addition and saves the updated task list to storage.
 * </p>
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE + OPEN_ANGLE_BRACKET + Constants.TASK_NUMBER + CLOSE_ANGLE_BRACKET;
    public static final String MESSAGE_DELETE_SUCCESS_PRE = "Noted. I've removed this task:";
    
    protected int taskNumber;
    
    /**
     * Constructs a DeleteCommand with the specified task number.
     * 
     * @param taskNumber represents the 1-based index of the task to be deleted
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    /**
     * Retrieves the task number associated with this command.
     * 
     * @return the task number
     */
    public int getTaskNumber() {
        return taskNumber;
    }
    
    /**
     * Executes the command to delete the specified task from the task list.
     * 
     * @param taskList represents the list of tasks to delete the task from
     * @param ui represents the user interface to interact with the user
     * @param storage represents the storage to save the updated task list
     * @throws CommandException if the task number is invalid or the task does not exist
     * @throws StorageOperationException if an error occurs while saving the taskList to storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {
        
        int taskNumber = getTaskNumber();
        int taskListSize = taskList.getSize();
        
        String taskWord = taskList.getTaskWord();
        final String MESSAGE_DELETE_SUCCESS_POST = Messages.MESSAGE_NOW_YOU_HAVE + (taskListSize - 1) + taskWord + Messages.MESSAGE_IN_THE_LIST;

        int indexToDelete = taskNumber - 1;
        Task taskToDelete = null;
        try {
            taskToDelete = taskList.getTask(indexToDelete);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(
                Messages.ERROR_TASK_NONEXISTENT,
                    String.format("%s %s %s", Messages.MESSAGE_NONEXISTENT_TASK_PRE, taskNumber, Messages.MESSAGE_NONEXISTENT_TASK_POST),
                    String.format("%s %s.", Messages.MESSAGE_ENTER_VALID_TASK_NUMBER, taskListSize)
            );
        }
        
        String[] messageList = null;

        boolean isDeletedSuccess = taskList.removeTask(taskToDelete);
        if (isDeletedSuccess == true) {
            storage.saveTasks(taskList);
            messageList = new String[]{MESSAGE_DELETE_SUCCESS_PRE, ui.formatSpace(2) + taskToDelete, MESSAGE_DELETE_SUCCESS_POST};
        } 

        ui.printMessage(messageList);
    }
}
