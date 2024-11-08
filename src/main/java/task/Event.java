package task;

import common.Messages;
import parser.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;

// e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019

/**
 * Events are tasks that occur over a period of time, starting at a specific date and time and ending at a specific date and time.
 * The Event class extends from the Task class. It encapsulates the task description, completion status, start date and time, and end date and time.
 */
public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    /**
     * Constructs an {@code Event} task with the specified description, completion status, start date and time, and end date and time
     * The {@code Event} task is initially marked as not done.
     * 
     * @param description represents the description of the {@code Event} task
     * @param isDone represents the completion status of the {@code Event} task
     * @param startDateTime represents the start date and time of the {@code Event} task
     * @param endDateTime represents the end date and time of the {@code Event} task
     */
    public Event(String description, boolean isDone, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Constructs an {@code Event} task with the specified description, start date and time, and end date and time.
     * 
     * @param description represents the description of the {@code Event} task
     * @param startDateTime represents the start date and time of the {@code Event} task
     * @param endDateTime represents the end date and time of the {@code Event} task
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.description = description;
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
     * Gets the formatted start date and time of the {@code Event} task.
     * The format is determined by the {@link DateTimeParser#formatDateTime(LocalDateTime)} method.
     * 
     * @return the formatted start date and time of the {@code Event} task
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
     * Gets the formatted end date and time of the {@code Event} task.
     * The format is determined by the {@link DateTimeParser#formatDateTime(LocalDateTime)} method.
     * 
     * @return the formatted end date and time of the {@code Event} task
     */
    public String getFormattedEndDateTime() {
        return DateTimeParser.formatDateTime(getEndDateTime());
    }

    /**
     * Returns a string representation of the {@code Event} task.
     * This includes the task type (Event), completion status, description, start date and time, and end date and time
     * 
     * @return a string representation of the {@code Event} task
     */
    @Override
    public String toString() {
        final String SPACE = Messages.SPACE;
        // If description does not end with a space, add a space behind it
        if (!description.endsWith(SPACE)) {
            description += SPACE;
        } 
        // String formattedEndDateTime = DateTimeParser.formatDateTime(endDateTime);

        return "[" + TaskType.EVENT + "]" + super.toString() + "(from: " + getFormattedStartDateTime() + " to: " + getFormattedEndDateTime() + ")";
    }

    /**
     * Encodes the {@code Event} task into a string for storage, i.e. writing to the tasks file.
     * 
     * @return encoded string representation of the {@code Event} task
     */
    @Override
    public String encodeTask() {
        String separator = " | ";
        return TaskType.EVENT + super.encodeTask() + separator + getFormattedStartDateTime() + separator + getFormattedEndDateTime();
    }

    /**
     * Checks if the {@code Event} task occurs on a specified date
     * The Event is considered to occur on a specific date if its start date and time or end date and time matches the specified date.
     * 
     * @param date represents the date to check against
     * @return true if the {@code Event} task occurs on a specific date; false otherwise
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        // return getDue().isEqual(due);
        // return startDate.isEqual(getStartDateTime().toLocalDate()) && endDate.isEqual(getEndDateTime().toLocalDate());
        return getStartDateTime().toLocalDate().isEqual(date) || 
               getEndDateTime().toLocalDate().isEqual(date);
    }

}
