package task;

import common.Constants;
import parser.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Extends from the Task class. It encapsulates the task description,
 * completion status, start date and time, and end date and time.
 * Events are tasks that occur over a period of time,
 * starting at a specific date and time and ending at a specific date and time.
 * e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019
 */
public class Event extends Task {

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Constructs an {@code Event} task with the specified description,
     * completion status, start date and time, and end date and time
     * The {@code Event} task is initially marked as not done.
     *
     * @param description   represents the description of the {@code Event} task.
     * @param isDone        represents the completion status of the {@code Event} task.
     * @param startDateTime represents the start date and time of the {@code Event} task.
     * @param endDateTime   represents the end date and time of the {@code Event} task.
     */
    public Event(String description, boolean isDone, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, isDone);
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        assert startDateTime != null : "Start date and time should not be null";
        assert endDateTime != null : "End date and time should not be null";
        assert startDateTime.isBefore(endDateTime) : "Start date and time must be before or equal to end date and time";

        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Constructs an {@code Event} task with the specified description, start date and time, and end date and time.
     *
     * @param description   represents the description of the {@code Event} task.
     * @param startDateTime represents the start date and time of the {@code Event} task.
     * @param endDateTime   represents the end date and time of the {@code Event} task.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        assert startDateTime != null : "Start date and time should not be null";
        assert endDateTime != null : "End date and time should not be null";
        assert startDateTime.isBefore(endDateTime) : "Start date and time must be before or equal to end date and time";
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
     * Returns the formatted start date and time of the {@code Event} task.
     * The format is determined by the {@link DateTimeParser#formatDateTime(LocalDateTime)} method.
     */
    public String getFormattedStartDateTime() {
        return DateTimeParser.formatDateTime(getStartDateTime());
    }

    /**
     * Retrieves the end date and time of the {@code Event} task.
     *
     * @return the end date and time as a {@code LocalDateTime} object.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Returns the formatted end date and time of the {@code Event} task.
     * The format is determined by the {@link DateTimeParser#formatDateTime(LocalDateTime)} method.
     */
    public String getFormattedEndDateTime() {
        return DateTimeParser.formatDateTime(getEndDateTime());
    }

    /**
     * Returns a string representation of the {@code Event} task.
     * This includes the task type (Event), completion status, description, start date and time, and end date and time.
     */
    @Override
    public String toString() {
        return OPEN_SQUARE_BRACKET + TaskType.EVENT + CLOSE_SQUARE_BRACKET + super.toString() + SPACE +
            OPEN_ROUND_BRACKET + Constants.FROM + COLON + SPACE + getFormattedStartDateTime() + SPACE +
            Constants.TO + COLON + SPACE + getFormattedEndDateTime() + CLOSE_ROUND_BRACKET;

    }

    /**
     * Encodes the {@code Event} task into a string for storage, i.e. writing to the tasks file.
     *
     * @return encoded string representation of the {@code Event} task.
     */
    @Override
    public String encodeTask() {
        String encodedTask = TaskType.EVENT + super.encodeTask() + SEPARATOR +
            getFormattedStartDateTime() + SEPARATOR + getFormattedEndDateTime();
        assertValidEncodedTask(encodedTask);
        return encodedTask;
    }

    /**
     * Checks if the {@code Event} task occurs on a specified date
     * The Event is considered to occur on a specific date if its
     * start date and time or end date and time matches the specified date.
     *
     * @param date represents the date to check against.
     * @return true if the {@code Event} task occurs on a specific date; false otherwise.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        boolean isStartDateOnDate = getStartDateTime().toLocalDate().isEqual(date);
        boolean isEndDateOnDate = getEndDateTime().toLocalDate().isEqual(date);
        return isStartDateOnDate || isEndDateOnDate;
    }

}
