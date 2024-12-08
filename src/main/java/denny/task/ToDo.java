package denny.task;

/**
 * Represents a basic todo task without any specific timing requirements.
 */
public class ToDo extends Task {
    /**
     * Creates a new todo task.
     * @param description The description of the todo task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

