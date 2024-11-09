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
     * Checks if this ToDo task is equal to another object.
     * A ToDo task is considered equal if it is of the same type and has the same
     * description and completion status as the specified task.
     *
     * @param obj The object to compare with this ToDo task.
     * @return {@code true} if the specified object is a ToDo task and is equal to this task; otherwise, {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ToDo)) {
            return false;
        }
        return super.equals(obj);
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
