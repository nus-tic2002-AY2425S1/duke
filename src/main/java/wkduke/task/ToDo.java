package wkduke.task;

import java.time.LocalDateTime;

/**
 * Represents a ToDo task with a description.
 * A ToDo task has no associated date or deadline.
 */
public class ToDo extends Task {
    /**
     * Constructs a {@code ToDo} task with the specified description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Encodes the {@code ToDo} task into a string format for file storage.
     *
     * @return A {@code String} representing the encoded task.
     */
    @Override
    public String encode() {
        return String.format("T | %s | %s",
                isDone ? "1" : "0",
                description
        );
    }

    /**
     * Checks if the task is scheduled for or due on a specified date.
     * Since a ToDo task has no associated date, this always returns {@code false}.
     *
     * @param targetDateTime The date to check against.
     * @return {@code false}, as ToDo tasks have no associated date.
     */
    @Override
    public boolean isOnDate(LocalDateTime targetDateTime) {
        return false;
    }

    /**
     * Returns a string representation of the ToDo task, including its type and description.
     *
     * @return A {@code String} representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
