package commands.add;

import java.time.LocalDateTime;

import common.Constants;
import task.Task;
import task.Event;

/**
 * {@code EventCommand} is extended from {@code AddTaskCommand} and is a subclass of {@code AddTaskCommand}.
 * {@code AddTaskCommand} provides the general structure for adding a task to the {@code TaskList}.
 * It represents a command that is used to add an {@code Event} task to the {@code TaskList}.
 * The command takes a description, start date/time, and end date/time, creates an {@code Event} task, 
 * adds it to the {@code TaskList} and saves the updated {@code TaskList} to {@code Storage}.
 */
public class EventCommand extends AddTaskCommand {
    public static final String COMMAND_WORD = "event";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE + 
                                               OPEN_ANGLE_BRACKET + Constants.DESCRIPTION + CLOSE_ANGLE_BRACKET + SPACE +
                                               Constants.SLASH_FROM + SPACE + OPEN_ANGLE_BRACKET + Constants.START_DATE_TIME + CLOSE_ANGLE_BRACKET + SPACE +
                                               Constants.SLASH_TO + SPACE + OPEN_ANGLE_BRACKET + Constants.END_DATE_TIME + CLOSE_ANGLE_BRACKET;
    
    protected final LocalDateTime startDateTime;
    protected final LocalDateTime endDateTime;

    /**
     * Constructs an {@code EventCommand} with the specified task description, start date and time, and end date and time.
     * 
     * @param description represents the description of the {@code Event} task
     * @param startDateTime represents the start date and time of the {@code Event} task
     * @param endDateTime represents the end date and time of the {@code Event} task
     */
    public EventCommand(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Retrieves the start date and time of the {@code Event} task.
     * 
     * @return the start date and time of the {@code Event} task
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Retrieves the end date and time of the {@code Event} task.
     * 
     * @return the end date and time of the {@code Event} task
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Creates a new {@code Event} task with the specified description, start date and time, and end date and time.
     * 
     * @return the newly-created {@code Event} task
     */
    @Override
    protected Task createTask() {
        String description = getDescription();
        LocalDateTime startDateTime = getStartDateTime();
        LocalDateTime endDateTime = getEndDateTime();
        Event task = new Event(description, startDateTime, endDateTime);
        return task;
    }

}
