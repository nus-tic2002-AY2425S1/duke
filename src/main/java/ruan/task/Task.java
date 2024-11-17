package ruan.task;

import ruan.exception.ErrorType;
import ruan.exception.RuanException;

/**
 * Represents an abstract task that has a description and a completion status
 * It serves as a base class for different types of tasks such as Todo, Deadline, and Event
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs Task with the specified description
     * By default, the task is marked as not done
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        //set all item to not done by default
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task
     * Returns "X" if the task is done
     * @return A string representing the status of the task
     */
    public String getStatusIcon() {
        //return task status
        return (isDone ? "X" : " ");
    }

     /**
     * Sets the status of the task
     * @param isDone True if the task is marked as done
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Converts the task into a specific format for saving & reading purposes
     * Each subclass must provide its implementation of how the task should be formatted
     * @return string representing the task in aspecific format
     */
    public abstract String toFileFormat();

    public static Task fromFileFormat(String fileLine) throws RuanException {
        String[] content = fileLine.split(" \\| ");
        if (content.length < 3) {
            throw new RuanException(ErrorType.INVALID_TASK_FORMAT);
        }
    
        String taskType = content[0];
        boolean isDone = content[1].equals("1");
        String description = content[2].trim();
        Task task;
        
        //check and read task from file for different command
        switch (taskType) {
            case "T": 
                task = new Todo(description);
            break;
            case "D":
                String[] deadlineData = description.split(" /by ");
                if (deadlineData.length != 2) {
                    throw new RuanException(ErrorType.INVALID_TASK_FORMAT);
                }
                String deadlineDescription = deadlineData[0].trim();
                String by = deadlineData[1].trim();
                task = new Deadline(deadlineDescription, by);
            break;
            case "E":
                String[] eventData = description.split(" /from | /to ");
                if (eventData.length != 3) {
                    throw new RuanException(ErrorType.INVALID_TASK_FORMAT);
                }
                String eventDescription = eventData[0].trim();
                String from = eventData[1].trim();
                String to = eventData[2].trim();
                task = new Event(eventDescription, from, to);
            break;
            default:
                throw new RuanException(ErrorType.INVALID_TASK_TYPE);
        }
    
        task.setDone(isDone);
        return task;
    }

    /**
     * Returns string representation of the task
     * String includes the status of the task and its description
     * @return A formatted string representing the task
     */
    @Override
    public String toString() {
        //return formatted string
        return "[" + getStatusIcon() + "] " + description; 
    }
}
