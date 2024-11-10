package snitch.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task with a deadline.
 */
public class Deadline extends Task {
    protected String by;
    private LocalDateTime dateTime;

    /**
     * Constructs a Deadline task.
     *
     * @param description The description of the task.
     * @param by The deadline, which can be a date/time or plain text.
     * @throws IllegalArgumentException if the date format is invalid and not plain text.
     */
    public Deadline(String description, String by) {
        super(description);

        if (looksLikeDate(by)) {
            if (isValidDate(by)) {
                this.dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                this.by = dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"));
            } else {
                throw new IllegalArgumentException("Invalid date format or value for deadline. Use d/M/yyyy HHmm.");
            }
        } else {
            this.by = by; // Treat as plain text
            this.dateTime = null;
        }
    }

    /**
     * Checks if the input is a valid date format.
     *
     * @param input The input string.
     * @return True if the input is a valid date.
     */
    private boolean isValidDate(String input) {
        try {
            LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if the input looks like a date (matches the expected pattern).
     *
     * @param input The input string.
     * @return True if the input looks like a date.
     */
    private boolean looksLikeDate(String input) {
        return input.matches("\\d{1,2}/\\d{1,2}/\\d{4} \\d{4}");
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}