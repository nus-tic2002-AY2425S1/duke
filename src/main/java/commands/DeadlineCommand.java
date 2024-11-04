package commands;
import exception.StorageOperationException;
import storage.Storage;
import task.Deadline;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a command to create a Deadline task.
 * 
 * <p>
 * The DeadlineCommand class is responsible for adding a new deadline task to the task list with a specified description and due date. 
 * It also interacts with the user interface to provide feedback on the task addition and saves the updated task list to storage.
 * </p>
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description> /by <due date>";
    public static final String MESSAGE_ADD_SUCCESS_PRE = "Got it. I've added this task:";
    
    protected final String description;
    protected final LocalDateTime by;

    /**
     * Constructs a DeadlineCommand with the specified description and due date.
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task. The time is optional.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add a deadline task to the task list.
     * 
     * @param taskList represents the task list to add the deadline task to.
     * @param ui represents the user interface to interact with the user.
     * @param storage represents the storage to save the updated task list.
     * @throws StorageException if an error occurs while saving the task list to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException {
        
        Task task = new Deadline(description, by);
        taskList.addTask(task);
        storage.saveTasks(taskList);
        
        int taskListSize = taskList.getSize();
        String taskWord = taskList.getTaskWord(taskListSize);
        final String MESSAGE_ADD_SUCCESS_POST = "Now you have " + taskListSize + taskWord + " in the list.";
        
        String[] messageList = {MESSAGE_ADD_SUCCESS_PRE, 
                                ui.formatSpace(2) + task, 
                                MESSAGE_ADD_SUCCESS_POST};
                                
        ui.printMessage(messageList);

    }

}
