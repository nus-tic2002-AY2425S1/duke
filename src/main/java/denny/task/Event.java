package denny.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

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

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public String getFromString() {
        return from.format(OUTPUT_FORMATTER);
    }

    public String getToString() {
        return to.format(OUTPUT_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFromString() + " to: " + getToString() + ")";
    }
}