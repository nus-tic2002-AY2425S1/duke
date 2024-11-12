package commands.add;

import common.Constants;
import task.Event;
import task.Task;

import java.time.LocalDateTime;

/**
 * Extends from {@code AddTaskCommand} and is a subclass of {@code AddTaskCommand}.
 * {@code AddTaskCommand} provides the general structure for adding a task to the {@code TaskList}.
 * It represents a command that is used to add an {@code Event} task to the {@code TaskList}.
 * The command takes a description, start date/time, and end date/time, creates an {@code Event} task,
 * adds it to the {@code TaskList} and saves the updated {@code TaskList} to {@code Storage}.
 */
public class EventCommand extends AddTaskCommand {
    public static final String COMMAND_WORD = "event";

    // event <description> / from <start date/ time> / to <end date/ time>
    public static final String MESSAGE_USAGE = COMMAND_WORD + SPACE +
        DESCRIPTION_IN_ANGLE_BRACKETS + SPACE + Constants.SLASH_FROM + SPACE +
        Constants.START_DATE_TIME_IN_ANGLE_BRACKETS + SPACE + Constants.SLASH_TO + SPACE +
        Constants.END_DATE_TIME_IN_ANGLE_BRACKETS;

    protected final LocalDateTime startDateTime;
    protected final LocalDateTime endDateTime;

    /**
     * Constructs an {@code EventCommand} with the specified task description,
     * start date and time, and end date and time.
     *
     * @param description   represents the description of the {@code Event} task.
     * @param startDateTime represents the start date and time of the {@code Event} task.
     * @param endDateTime   represents the end date and time of the {@code Event} task.
     */
    public EventCommand(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the start date and time of the {@code Event} task.
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Returns the end date and time of the {@code Event} task.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Returns a new {@code Event} task with the specified description, start date and time, and end date and time.
     */
    @Override
    protected Task createTask() {
        String eventDescription = getDescription();
        LocalDateTime eventStartDateTime = getStartDateTime();
        LocalDateTime eventEndDateTime = getEndDateTime();
        return new Event(eventDescription, eventStartDateTime, eventEndDateTime);
    }

}
