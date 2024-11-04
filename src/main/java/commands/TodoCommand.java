package commands;
import exception.StorageOperationException;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Represents a command to add a Todo task to the task list.
 * 
 * <p>
 * The TodoCommand class is responsible for creating a new todo task with the specified description and adding it to the task list. 
 * It provides feedback to the user about the successful addition of the task.
 * </p> 
 */
public class TodoCommand extends Command {
    
    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description>";
    public static final String MESSAGE_ADD_SUCCESS_PRE = "Got it. I've added this task:";

    protected final String description;

    /**
     * Constructs a TodoCommand class with the specified description.
     * @param description represents the description of the Todo task
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Executes the command to add a Todo task to the task list.
     * @param taskList represents the list of tasks to add the new Todo task to
     * @param ui represents the user interface to interact with the user
     * @param storage represents the storage to save the updated task list
     * @throws StorageOperationException if an error occurs while saving the task list to storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        
        // Creates a Todo task with the specified description
        Task task = new Todo(getDescription());

        // Add the newly-created task to the task list
        taskList.addTask(task);

        // Save the updated task list to storage
        storage.saveTasks(taskList);
        
        // Prepare the success message
        int taskListSize = taskList.getSize();
        String taskWord = taskList.getTaskWord(taskListSize);
        final String MESSAGE_ADD_SUCCESS_POST = "Now you have " + taskListSize + taskWord + " in the list.";
        
        String[] messageList = {MESSAGE_ADD_SUCCESS_PRE, ui.formatSpace(2) + task, MESSAGE_ADD_SUCCESS_POST};
        
        // Print the success message to the user
        ui.printMessage(messageList);

    }

}
