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

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

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

        // Assertions to validate constructor parameters
        assert startDateTime != null : "Event start date and time cannot be null";
        assert endDateTime != null : "Event end date and time cannot be null";
        assert startDateTime.isBefore(endDateTime) : "Event start date and time must be before end date and time";

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
        boolean isDescriptionEmpty = !(eventDescription.trim().isEmpty());
        assert eventDescription != null && isDescriptionEmpty : "Task description cannot be null or empty";

        LocalDateTime eventStartDateTime = getStartDateTime();
        assert eventStartDateTime != null : "Event start date and time must not be null";

        LocalDateTime eventEndDateTime = getEndDateTime();
        assert eventEndDateTime != null : "Event end date and time must not be null";

        assert eventStartDateTime.isBefore(eventEndDateTime) : "Event start date and time must be before or equal to end date and time";

        return new Event(eventDescription, eventStartDateTime, eventEndDateTime);
    }

}
