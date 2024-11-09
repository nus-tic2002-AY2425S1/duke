package tasks;

import exception.DukeException;
import lombok.Getter;

/**
 * An abstract task class with description and completion status
 */
@Getter
public abstract class Task {
    protected boolean isDone;
    protected String description;

    /**
     * Constructs a Task with description
     * @param description The description of the task
     */
    public Task (String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone(){
        isDone = true;
    }

    /**
     * Un-marks the task as done.
     */
    public void unmarkDone(){
        isDone = false;
    }

    /**
     * Convert Task object into string
     * @return A string representation of task
     */
    @Override
    public String toString(){
        return "[" + (isDone? "X" : " ") + "] " + description;
    }

    /**
     * Creates a task from description
     * @param description The description of the task
     * @return The created task object
     * @throws DukeException If an error occur while creating task
     */
    public abstract Task createTask(String description) throws DukeException;

    public abstract void update (String updateInput) throws DukeException;

    /**
     * Convert task to a format for file saving format for file storage
     * @return String in the format to save task into a file
     * @throws DukeException If an error occur during the conversion
     */
    public abstract String toFileFormat() throws DukeException;
}
