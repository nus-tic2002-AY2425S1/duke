package denny.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a specific deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    /**
     * Creates a new deadline task.
     * @param description The description of the deadline task
     * @param by The deadline date and time in format "d/M/yyyy HHmm"
     * @throws IllegalArgumentException if the date format is invalid
     */
    public Deadline(String description, String by) throws IllegalArgumentException {
        super(description);
        try {
            assert by != null : "Deadline date cannot be null";
            this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
            assert this.by != null : "Failed to parse deadline date";
            assert this.by.isAfter(LocalDateTime.now().minusSeconds(1)) : "Deadline cannot be in the past";
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date should be in format: d/M/yyyy HHmm");
        }
    }

    /**
     * Gets the deadline date and time.
     * @return LocalDateTime representing the deadline
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Gets a formatted string representation of the deadline.
     * @return Formatted deadline string
     */
    public String getByString() {
        return by.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByString() + ")";
    }
}
