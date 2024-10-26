package denny.task;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    protected final LocalDateTime createdAt;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.createdAt = LocalDateTime.now();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}