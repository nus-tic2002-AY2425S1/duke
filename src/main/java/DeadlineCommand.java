public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description> /by <due date>";
    public static final String MESSAGE_ADD_SUCCESS_PRE = "Got it. I've added this task:";
    
    protected final String description;
    protected final String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, TaskException, StorageOperationException {
        
        Task task = new Deadline(description, by);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        
        int taskListSize = taskList.getSize();
        String taskWord = taskList.getTaskWord(taskListSize);
        final String MESSAGE_ADD_SUCCESS_POST = "Now you have " + taskListSize + taskWord + " in the list.";
        
        String[] messageList = {MESSAGE_ADD_SUCCESS_PRE, ui.formatSpace(2) + task, MESSAGE_ADD_SUCCESS_POST};
        ui.printMessage(messageList);

    }

}
