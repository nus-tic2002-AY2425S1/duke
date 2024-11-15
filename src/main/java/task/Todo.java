package task;

import common.Constants;

import java.time.LocalDate;

/**
 * Represents a {@code Todo} task, which is a subclass of {@link Task}, i.e. a specific type of {@link Task}.
 * Todos are tasks without any date/time attached to it, e.g., visit new theme park.
 * A {@code Todo} task has a description and completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a {@code Todo} task with the specified description.
     * The task is initialized as not done.
     *
     * @param description represents the description of the {@code Todo} task.
     */
    public Todo(String description) {
        super(description);
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
    }

    /**
     * Constructs a Todo task with the specified description and completion status.
     *
     * @param description represents the description of the {@code Todo} task.
     * @param isDone      represents the completion status of the {@code Todo} task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
    }

    /**
     * Returns a string representation of the {@code Todo} task.
     * This includes its task type (Todo), which is represented by "T", and the description of the {@code Todo} task.
     *
     * @return a string representation of the {@code Todo} task.
     */
    @Override
    public String toString() {
        return Constants.OPEN_SQUARE_BRACKET + TaskType.TODO + Constants.CLOSE_SQUARE_BRACKET + super.toString();
    }

    /**
     * Generates a formatted string to encode the {@code Todo} task suitable for {@link storage.Storage}.
     * The encoded string includes the task type, completion status, and description of the {@code Todo} task.
     *
     * @return the encoded string representation of the {@code Todo} task,
     */
    @Override
    public String encodeTask() {
        return TaskType.TODO + super.encodeTask();
    }

    /**
     * Checks if the {@code Todo} task occurs on a specified date.
     * For a {@code Todo} task, this method must always return false because it does not have any date attached to it.
     *
     * @param date represents the date to check against.
     * @return false as {@code Todo} tasks do not have a specific date.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }

}
