package denny.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    public Deadline(String description, String by) throws IllegalArgumentException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Date should be in format: d/M/yyyy HHmm");
        }
    }

    public LocalDateTime getBy() {
        return by;
    }

    public String getByString() {
        return by.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByString() + ")";
    }
}
