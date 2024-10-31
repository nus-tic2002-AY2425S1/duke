public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description> /from <start date/time> /to <end date/time>";
    public static final String MESSAGE_ADD_SUCCESS_PRE = "Got it. I've added this task:";
    
    protected final String description;
    protected final String startTime;
    protected final String endTime;

    public EventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, TaskException, StorageOperationException {
        
        Task task = new Event(description, startTime, endTime);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        
        int taskListSize = taskList.getSize();
        String taskWord = taskList.getTaskWord(taskListSize);
        final String MESSAGE_ADD_SUCCESS_POST = "Now you have " + taskListSize + taskWord + " in the list.";
        
        String[] messageList = {MESSAGE_ADD_SUCCESS_PRE, ui.formatSpace(2) + task, MESSAGE_ADD_SUCCESS_POST};
        ui.printMessage(messageList);

    }
}
