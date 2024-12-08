package snitch.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end time.
 * An event task has a description, a start time, and an end time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time in the format "d/M/yyyy HHmm".
     * @param to The end time in the format "d/M/yyyy HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns the start time of the event in "d/M/yyyy HHmm" format.
     *
     * @return The start time as a formatted string.
     */
    public String getFrom() {
        return from.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns the end time of the event in "d/M/yyyy HHmm" format.
     *
     * @return The end time as a formatted string.
     */
    public String getTo() {
        return to.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    /**
     * Returns a string representation of the Event task.
     * The string includes the task type, status, description, start time, and end time.
     *
     * @return A string representing the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}