package ruan.task;

/**
 * Represents a task to be done
 * Inherits from Task
 * Coverts input data into string for display or to be store in file
 */

public class Todo extends Task {

    /**
     * Constructs Todo task with the specified description
     * @param description Description of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the task into a specific format for saving purposes
     * @return Formatted string representation of the task suitable for file storage
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the Todo task
     * Includes the task type and the completion status
     * @return Formatted string representing the Todo task
     */
    @Override
    public String toString() {
        //return formatted task string
        return "[T]" + super.toString();
    }
}