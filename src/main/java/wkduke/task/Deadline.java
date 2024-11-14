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
     * Constructs a {@code Deadline} task with the specified description, deadline date-time, completion status, and priority.
     *
     * @param description The description of the task.
     * @param by          The date and time by which the task is due.
     * @param isDone      The completion status of the task.
     * @param priority    The priority level of the task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone, TaskPriority priority) {
        super(description, isDone, priority);
        this.by = by;
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
     * Checks if this Deadline task is equal to another object.
     * A Deadline task is considered equal if it is of the same type, has the same description,
     * completion status, and due date as the specified task.
     *
     * @param obj The object to compare with this Deadline task.
     * @return {@code true} if the specified object is a Deadline task and is equal to this task; otherwise, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Deadline task)) {
            return false;
        }
        if (!by.equals(task.by)) {
            return false;
        }
        return super.equals(obj);
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
}
