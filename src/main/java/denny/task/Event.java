package denny.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs during a specific time period.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    /**
     * Creates a new event task.
     * @param description The description of the event
     * @param from Start date and time in format "d/M/yyyy HHmm"
     * @param to End date and time in format "d/M/yyyy HHmm"
     * @throws IllegalArgumentException if the date format is invalid or end time is before start time
     */
    public Event(String description, String from, String to) throws IllegalArgumentException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
            this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
            if (this.to.isBefore(this.from)) {
                throw new IllegalArgumentException("End time cannot be before start time");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date should be in format: d/M/yyyy HHmm");
        }
    }

    /**
     * Gets the event start date and time.
     * @return LocalDateTime representing the start time
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the event end date and time.
     * @return LocalDateTime representing the end time
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Gets a formatted string representation of the start time.
     * @return Formatted start time string
     */
    public String getFromString() {
        return from.format(OUTPUT_FORMATTER);
    }

    /**
     * Gets a formatted string representation of the end time.
     * @return Formatted end time string
     */
    public String getToString() {
        return to.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFromString() + " to: " + getToString() + ")";
    }
}