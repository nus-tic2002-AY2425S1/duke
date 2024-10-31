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
     * Encodes the {@code Event} task into a string format for file storage.
     *
     * @return A {@code String} representing the encoded event task.
     */
    @Override
    public String encode() {
        return String.format("E | %s | %s | %s | %s",
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
     * Returns a string representation of the event, including its type, time range, and description.
     *
     * @return A {@code String} representing the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.format(TimeParser.CLI_FORMATTER) + " to:" + to.format(TimeParser.CLI_FORMATTER) + ")";
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
}
