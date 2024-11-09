package wkduke.task;

import java.time.LocalDateTime;

/**
 * Represents a general task with a description and completion status.
 * Subclasses must implement methods for encoding and date-based checks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a {@code Task} with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Encodes the task into a string format suitable for file storage.
     *
     * @return A {@code String} representing the encoded task.
     */
    public abstract String encode();

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task as a {@code String}.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retrieves the status icon of the task.
     *
     * @return {@code "X"} if the task is done, or a blank space if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return {@code true} if the task is done; {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Checks if the task is scheduled for or due on a specified date.
     *
     * @param targetDateTime The date to check against.
     * @return {@code true} if the task is on the specified date; {@code false} otherwise.
     */
    public abstract boolean isOnDate(LocalDateTime targetDateTime);

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Checks if this task is equal to another object.
     * Two tasks are considered equal if they have the same description and completion status.
     *
     * @param obj The object to compare with this task.
     * @return {@code true} if the specified object is equal to this task, otherwise {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task task)) {
            return false;
        }
        if (!description.equals(task.description)) {
            return false;
        }
        return isDone == task.isDone;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A {@code String} representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}