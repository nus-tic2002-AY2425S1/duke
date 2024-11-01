package commands;
import common.Messages;
import exception.CommandException;
import exception.StorageOperationException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <task number>";
    public static final String MESSAGE_DELETE_SUCCESS_PRE = "Noted. I've removed this task:";
    
    protected int taskNumber;
    
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    public int getTaskNumber() {
        return taskNumber;
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException {
        
        int taskNumber = getTaskNumber();
        int taskListSize = taskList.getSize();
        
        String taskWord = taskList.getTaskWord(taskListSize);
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
