package wkduke.task;

import wkduke.parser.TimeParser;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 * Contains a description and a date-time by which the task should be completed.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task with the specified description and deadline date-time.
     *
     * @param description The description of the task.
     * @param by          The date and time by which the task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Encodes the {@code Deadline} task into a string format for file storage.
     *
     * @return A {@code String} representing the encoded task.
     */
    @Override
    public String encode() {
        return String.format("D | %s | %s | %s",
                isDone ? "1" : "0",
                description,
                by.format(TimeParser.ENCODING_FORMATTER)
        );
    }

    /**
     * Checks if the task is due on the specified date.
     *
     * @param targetDateTime The date to check against the task's due date.
     * @return {@code true} if the task is due on the specified date; {@code false} otherwise.
     */
    @Override
    public boolean isOnDate(LocalDateTime targetDateTime) {
        return targetDateTime.isEqual(by);
    }

    /**
     * Returns a string representation of the task, including its type and due date.
     *
     * @return A {@code String} representing the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by.format(TimeParser.CLI_FORMATTER) + ")";
    }

    /**
     * Retrieves the date and time by which the task is due.
     *
     * @return The due date and time as a {@code LocalDateTime}.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Sets the due date and time for the task.
     *
     * @param by The new due date and time.
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }
}
