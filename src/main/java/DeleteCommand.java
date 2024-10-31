public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <task number>";
    public static final String MESSAGE_DELETE_SUCCESS_PRE = "Noted. I've removed this task:";
    public static final String MESSAGE_EMPTY_TASKLIST = "The task list is empty. Please add a task first.";
    
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
        final String MESSAGE_NONEXISTENT_TASK = "Task number " + taskNumber + " not found. Please enter a valid task number from 1 to " + taskListSize + ".";
        
        String taskWord = taskList.getTaskWord(taskListSize);
        final String MESSAGE_DELETE_SUCCESS_POST = "Now you have " + (taskListSize - 1) + taskWord + " in the list.";

        int indexToDelete = taskNumber - 1;
        Task taskToDelete = null;
        try {
            taskToDelete = taskList.getTask(indexToDelete);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(MESSAGE_NONEXISTENT_TASK);
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
