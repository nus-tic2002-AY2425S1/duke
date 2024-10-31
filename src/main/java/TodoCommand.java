public class TodoCommand extends Command {
    
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description>";
    public static final String MESSAGE_ADD_SUCCESS_PRE = "Got it. I've added this task:";
    // public static final String MESSAGE_EMPTY_TASKLIST = "The task list is empty. Please add a task first.";    
    
    protected final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, TaskException, StorageOperationException {
        
        Task task = new Todo(description);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        
        int taskListSize = taskList.getSize();
        String taskWord = taskList.getTaskWord(taskListSize);
        final String MESSAGE_ADD_SUCCESS_POST = "Now you have " + taskListSize + taskWord + " in the list.";
        
        String[] messageList = {MESSAGE_ADD_SUCCESS_PRE, ui.formatSpace(2) + task, MESSAGE_ADD_SUCCESS_POST};
        ui.printMessage(messageList);

        // String[] messageList;
        // if (description.isEmpty()) {
        //     // String emptyDescriptionMessage = MESSAGE_EMPTY_DESCRIPTION_PRE + NEW_LINE + 
        //     //                                  ui.getSpace(false, false) + 
        //     //                                  MESSAGE_EMPTY_DESCRIPTION_POST;
        //     String emptyDescriptionMessage = Messages.MESSAGE_EMPTY_DESCRIPTION_PRE + Messages.NEW_LINE + 
        //                                      ui.getSpace(false, false) + 
        //                                      Messages.MESSAGE_EMPTY_DESCRIPTION_MID + COMMAND_WORD + 
        //                                      Messages.MESSAGE_EMPTY_DESCRIPTION_POST;
        //     throw new TaskException(emptyDescriptionMessage);
        //     // ui.printMessage(emptyDescriptionMessage);
        // }

    }

}
