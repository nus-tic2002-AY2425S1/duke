package denny.task;

import java.time.LocalDateTime;

/**
 * Base class for all task types in the application.
 * Provides common functionality for task management.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected final LocalDateTime createdAt;

    /**
     * Creates a new task with the given description.
     * @param description The description of the task
     */
    public Task(String description) {
        assert description != null && !description.trim().isEmpty() : "Task description cannot be null or empty";
        this.description = description;
        this.isDone = false;
        this.createdAt = LocalDateTime.now();
        assert this.createdAt != null : "Creation timestamp must be set";
    }

    /**
     * Gets the status icon for the task (X for done, space for not done).
     * @return Status icon character
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        assert !isDone : "Task is already marked as done";
        this.isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        assert isDone : "Task is already marked as not done";
        this.isDone = false;
    }

    /**
     * Gets the task description.
     * @return Task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is completed.
     * @return true if task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the creation timestamp of the task.
     * @return LocalDateTime when the task was created
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}