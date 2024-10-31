public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <task number>";
    public static final String MESSAGE_UNMARK_SUCCESS = "Nice! I've marked this task as done:";
    public static final String MESSAGE_EMPTY_TASKLIST = "The task list is empty. Please add a task first.";
    
    protected int taskNumber;
    
    public UnmarkCommand(int taskNumber) {
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

        int indexToUnmark = taskNumber - 1;
        Task taskToUnmark = null;
        try {
            taskToUnmark = taskList.getTask(indexToUnmark);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new CommandException(MESSAGE_NONEXISTENT_TASK);
        }
        final String MESSAGE_ALREADY_UNMARKED = "The task `" + taskToUnmark + "` is already marked as not done. No action done.";
        
        String[] messageList = null;

        boolean isUnmarkedSuccess = taskList.unmarkTask(indexToUnmark);
        if (isUnmarkedSuccess == true) {
            storage.saveTasks(taskList);
            messageList = new String[]{MESSAGE_UNMARK_SUCCESS, ui.formatSpace(2) + taskToUnmark};
        } else {
            messageList = new String[]{MESSAGE_ALREADY_UNMARKED};
        }

        ui.printMessage(messageList);
    }
}
