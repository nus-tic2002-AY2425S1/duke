package wkduke.task;

import java.time.LocalDateTime;

/**
 * Represents a general task with a description and completion status.
 * Subclasses must implement methods for encoding and date-based checks.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;
    protected TaskPriority priority;

    /**
     * Constructs a {@code Task} with the specified description, initially marked as not done and
     * with a default priority of {@code TaskPriority.LOW}.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = TaskPriority.LOW;
    }

    /**
     * Constructs a {@code Task} with the specified description, completion status, and priority.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     * @param priority    The priority level of the task.
     */
    public Task(String description, boolean isDone, TaskPriority priority) {
        this.description = description;
        this.isDone = isDone;
        this.priority = priority;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task as a {@code String}.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the priority of the task.
     *
     * @return The priority of the task, or {@code null} if no priority is set.
     */
    public TaskPriority getPriority() {
        return priority;
    }

    /**
     * Sets the priority of the task.
     *
     * @param priority The new priority of the task.
     */
    public void setPriority(TaskPriority priority) {
        this.priority = priority;
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
        if (!priority.equals(task.priority)) {
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
        return "[" + priority + "][" + getStatusIcon() + "] " + description;
    }

}