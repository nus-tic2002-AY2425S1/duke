package commands.add;

import commands.Command;
import common.Messages;
import exception.StorageOperationException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * AddTaskCommand is an abstract class that extends from the Command class.
 * It serves as the base class for TodoCommand, DeadlineCommand, and EventCommand. 
 * It is meant to be extended by concrete classes that define specific types of tasks.
 * Its purpose is to handle the core functionality of adding a task to the {@code TaskList}, saving the {@code TaskList} to {@code Storage}, i.e. the tasks file, and printing a message
 * The task creation process is abstracted so that subclasses can define their own implementation of creating the specfic type of task.
 */
public abstract class AddTaskCommand extends Command {
    
    public static final String MESSAGE_ADD_SUCCESS_PRE = "Got it. I've added this task:";

    // All tasks have a description
    protected final String description;

    /**
     * Constructs an {@code AddTaskCommand} with the specified task description.
     * 
     * @param description represents the description of the {@code Task} to be added
     */
    public AddTaskCommand(String description) {
        this.description = description;
    }

    /**
     * Retrieve the description of the {@code Task}.
     * 
     * @return the description of the {@code Task} object
     */
    public String getDescription() {
        return description;
    }

    /**
     * Creates the specific {@code Task} to be added to the {@code TaskList} based on the command.
     * This abstract method will be implemented by the subclasses, i.e. TodoCommand, DeadlineCommand, and EventCommand.
     * The respective classes will create the appropriate type of task.
     * 
     * @return the newly-created {@code Task} object to be added to the {@code TaskList}
     */
    protected abstract Task createTask();

    /**
     * Prepares the post-success message after a task has been added to the {@code TaskList}. 
     * It indicates to the user, the number of tasks in the list after the addition of the new task.
     * 
     * @param taskList represents the current state of the task list
     * @return a post-success message
     */
    protected static String preparePostSuccessMessage(TaskList taskList) {
        // Prepare the success message
        int taskListSize = taskList.getSize();
        String taskWord = taskList.getTaskWord();
        return Messages.MESSAGE_NOW_YOU_HAVE + taskListSize + taskWord + Messages.MESSAGE_IN_THE_LIST;
    }

    /**
     * Executes the command by adding a {@code Task} to the {@code TaskList}.
     * Creates the {@code Task}, adds it to the {@code TaskList}, and saves the updated {@code TaskList} to storage. 
     * Upon successful completion, prints a success message.
     * 
     * @param taskList represents the list of tasks to which the new task will be added
     * @param ui represents the user interface instance that will be used to handle interactions with the user
     * @param storage represents the storage instance that will be used to save the updated task list
     * @throws StorageOperationException if an error occurs while saving the task list to storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        Task task = createTask();

        // Add the newly-created task to the task list
        taskList.addTask(task);

        // Save the updated task list to storage
        storage.saveTasks(taskList);
        
        final String MESSAGE_ADD_SUCCESS_POST = preparePostSuccessMessage(taskList);
        
        String[] messageList = {MESSAGE_ADD_SUCCESS_PRE, ui.formatSpace(2) + task, MESSAGE_ADD_SUCCESS_POST};
        
        // Print the success message to the user
        ui.printMessage(messageList);
    }
}
