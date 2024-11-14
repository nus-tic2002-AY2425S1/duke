package Chad.TaskList;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;
    protected LocalDateTime completeTime;

    public Task(String description) {
        // Assert that the description is not null or empty
        assert description != null && !description.isEmpty() : "Task description must not be null or empty.";

        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void setTask() {
        this.isDone = true;
        this.completeTime = LocalDateTime.now();
        
        // Assertion to confirm the task is marked as done, condition false assert will come out
        assert isDone : "Task should be marked as done after setTask() is called.";
        assert completeTime != null : "Complete time must not be null when the task is done.";
    }

    public void setCompleteTime(LocalDateTime completeTime) {
        // Assert that completeTime is not null
        assert completeTime != null : "Complete time must not be null.";

        this.completeTime = completeTime;
    }

    public LocalDateTime getCompleteTime() {
        return this.completeTime;
    }

    public void unSetTask() {
        this.isDone = false;
        this.completeTime = null;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    //...

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.description;
    }
}