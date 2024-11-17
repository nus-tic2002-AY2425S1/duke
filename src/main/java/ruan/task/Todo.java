package ruan.task;

/**
 * Represents a task to be done
 * Inherits from Task
 * Coverts input data into string for display or to be store in file
 */

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        //return formatted task string
        return "[T]" + super.toString();
    }
}