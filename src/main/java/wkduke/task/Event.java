package wkduke.task;

import wkduke.parser.TimeParser;

import java.time.LocalDateTime;

/**
 * Represents an event task with a start and end date-time.
 * Contains a description and a time range during which the event occurs.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an {@code Event} task with the specified description, start, and end date-time.
     *
     * @param description The description of the event.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an {@code Event} task with the specified description, start date-time, end date-time, and completion status.
     * The task has a default priority of {@code TaskPriority.LOW}.
     *
     * @param description The description of the event.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     * @param isDone      The completion status of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an {@code Event} task with the specified description, start date-time, end date-time, completion status, and priority.
     *
     * @param description The description of the event.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     * @param isDone      The completion status of the event.
     * @param priority    The priority level of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone, TaskPriority priority) {
        super(description, isDone, priority);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the start date and time of the event.
     *
     * @return The start date and time as a {@code LocalDateTime}.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Sets the start date and time of the event.
     *
     * @param from The new start date and time.
     */
    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    /**
     * Retrieves the end date and time of the event.
     *
     * @return The end date and time as a {@code LocalDateTime}.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Sets the end date and time of the event.
     *
     * @param to The new end date and time.
     */
    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    /**
     * Encodes the {@code Event} task into a string format for file storage.
     *
     * @return A {@code String} representing the encoded event task.
     */
    @Override
    public String encode() {
        return String.format("E | %s | %s | %s | %s | %s",
                priority,
                isDone ? "1" : "0",
                description,
                from.format(TimeParser.ENCODING_FORMATTER),
                to.format(TimeParser.ENCODING_FORMATTER)
        );
    }

    /**
     * Checks if the event is occurring on the specified date.
     *
     * @param targetDateTime The date to check against the event's time range.
     * @return {@code true} if the specified date falls within the event's time range; {@code false} otherwise.
     */
    @Override
    public boolean isOnDate(LocalDateTime targetDateTime) {
        return targetDateTime.isAfter(from) && targetDateTime.isBefore(to);
    }

    /**
     * Checks if this Event task is equal to another object.
     * An Event task is considered equal if it is of the same type, has the same description,
     * completion status, start date, and end date as the specified task.
     *
     * @param obj The object to compare with this Event task.
     * @return {@code true} if the specified object is an Event task and is equal to this task; otherwise, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event task)) {
            return false;
        }
        if (!from.equals(task.from)) {
            return false;
        }
        if (!to.equals(task.to)) {
            return false;
        }
        return super.equals(obj);
    }

    /**
     * Returns a string representation of the event, including its type, time range, and description.
     *
     * @return A {@code String} representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.format(TimeParser.CLI_FORMATTER) + " to:" + to.format(TimeParser.CLI_FORMATTER) + ")";
    }
}
