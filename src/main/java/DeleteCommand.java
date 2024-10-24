public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " {taskNumber}";
    public static final String MESSAGE_SUCCESS_PRE = "Noted. I've removed this task: ";
    public static final String TASK_PLACEHOLDER = "  %s";
    public static final String MESSAGE_SUCCESS_POST = "Now you have %s tasks in the list.";
    protected final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException, CommandOperationException {
        try {
            int taskIndex = taskNumber - 1;
            Task task = taskList.getTask(taskIndex);
            taskList.deleteTask(taskIndex);
            storage.save(taskList);
            ui.printMessages(
                    MESSAGE_SUCCESS_PRE,
                    String.format(TASK_PLACEHOLDER, task.toString()),
                    String.format(MESSAGE_SUCCESS_POST, taskList.size())
            );
        } catch (IndexOutOfBoundsException e) {
            throw new CommandOperationException(
                    Messages.MESSAGE_INVALID_TASK_NUMBER,
                    String.format("Command='delete', TaskNumber='%s'", taskNumber)
            );
        }
    }
}
