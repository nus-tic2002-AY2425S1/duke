package commands;
import exception.CommandException;
import exception.StorageOperationException;
import exception.TaskException;
import storage.Storage;
import task.Event;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.time.LocalDateTime;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " <description> /from <start date/time> /to <end date/time>";
    public static final String MESSAGE_ADD_SUCCESS_PRE = "Got it. I've added this task:";
    
    protected final String description;
    protected final LocalDateTime startDateTime;
    protected final LocalDateTime endDateTime;

    public EventCommand(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, TaskException, StorageOperationException {
        
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
