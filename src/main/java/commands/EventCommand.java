package commands;
import exception.StorageOperationException;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a Command to add an Event task.
 * 
 * <p>
 * The EventCommand class is responsible for adding a new event task to the task list with a specified description, start date/time, and end date/time. 
 * It interacts with the user interface to provide feedback on the task addition and saves the updated task list to storage.
 * </p>
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description> /from <start date/time> /to <end date/time>";
    public static final String MESSAGE_ADD_SUCCESS_PRE = "Got it. I've added this task:";
    
    protected final String description;
    protected final LocalDateTime startDateTime;
    protected final LocalDateTime endDateTime;

    /**
     * Constructs an EventCommand with the specified description, start date/time, and end date/time.
     * @param description represents the description of the Event task
     * @param startDateTime represents the start date/time of the Event task
     * @param endDateTime represents the end date/time of the Event task
     */
    public EventCommand(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Executes the command to add an Event task to the task list.
     * 
     * @param taskList represents the list of tasks to add the Event task to
     * @param ui represents the user interface to interact with the user
     * @param storage represents the storage to save the updated task list to
     * @throws StorageOperationException if an error occurs while saving the updated task list to storage
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        
        Task task = new Event(description, startDateTime, endDateTime);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        
        int taskListSize = taskList.getSize();
        String taskWord = taskList.getTaskWord(taskListSize);
        final String MESSAGE_ADD_SUCCESS_POST = "Now you have " + taskListSize + taskWord + " in the list.";
        
        String[] messageList = {MESSAGE_ADD_SUCCESS_PRE, ui.formatSpace(2) + task, MESSAGE_ADD_SUCCESS_POST};
        ui.printMessage(messageList);

    }
}
