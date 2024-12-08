package ruan.task;

import ruan.exception.*;
import ruan.constant.Constants;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an abstract task that has a description and a completion status
 * It serves as a base class for different types of tasks such as Todo, Deadline, and Event
 */

public abstract class Task {
    protected String description;
    protected boolean isDone;
    // Stores the time when the task is marked as done
    protected LocalDateTime completionDateTime; 
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    /**
     * Constructs Task with the specified description
     * By default, the task is marked as not done
     * @param description The description of the task
     */
    public Task(String description) {
        assert description != null && !description.isBlank() : ErrorType.EMPTY_DESCRIPTION;
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
     * Gets the task status
     * @return Task status
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the description of the task
     * @return String representing the description of the task
     */
    public String getDescription() {
        //return task description
        return description;
    }

     /**
     * Sets the status of the task
     * @param isDone True if the task is marked as done
     * @param completionDateTime completion datetime to be set for task, if null, take current datetime
     */
    public void setDone(boolean isDone, String completionDateTime) {
        this.isDone = isDone;
        if (isDone) {
            try {
                // Ensure that completionDateTime is a String before parsing
                String completionDateTimeStr = completionDateTime != null ? completionDateTime.toString() : LocalDateTime.now().toString();
                this.completionDateTime = LocalDateTime.parse(completionDateTimeStr, DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                this.completionDateTime = LocalDateTime.now();
            }
        } else {
            this.completionDateTime = null;
        }
    }

    /**
     * Gets the datetime when the task was completed
     * 
     * @return LocalDateTime representing the completion datetime
     */
    public LocalDateTime getCompletionDateTime() {
        return completionDateTime;
    }

    /**
     * Converts the task into a specific format for saving & reading purposes
     * Each subclass must provide its implementation of how the task should be formatted
     * @return string representing the task in aspecific format
     */
    public abstract String toFileFormat();

    public static Task fromFileFormat(String fileLine) throws RuanException {
        String[] content = fileLine.split(" \\| ");
        if (content.length != 4) {
            throw new RuanException(ErrorType.INVALID_TASK_FORMAT);
        }
    
        String taskType = content[0];
        boolean isDone = content[1].equals("1");
        String description = content[2].trim();
        String completedDateTime = content[3].trim();
        Task task;
        
        //check and read task from file for different command
        switch (taskType) {
            case "T": 
                task = new Todo(description);
            break;
            case "D":
                String[] deadlineData = description.split(Constants.SPLIT_BY_COMMAND);
                if (deadlineData.length != 3) {
                    throw new RuanException(ErrorType.INVALID_TASK_FORMAT);
                }
                String deadlineDescription = deadlineData[0].trim();
                String deadlineBy = deadlineData[1].trim();
                task = new Deadline(deadlineDescription, deadlineBy);
            break;
            case "E":
                String[] eventData = description.split(Constants.SPLIT_FROM_TO_COMMAND);
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
    
        task.setDone(isDone, completedDateTime);
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


    /**
     * Checks if this task is equal to another object
     * @param obj The object to compare
     * @return true if the tasks have the same description
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task otherTask = (Task) obj;
        return this.description.equalsIgnoreCase(otherTask.description);
    }
}
