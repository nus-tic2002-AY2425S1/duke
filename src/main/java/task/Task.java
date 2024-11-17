package task;

import common.Constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents the base class for all tasks. 
 * Subclasses like Todo, Deadline, Event, and Fixed Duration will extend from this abstract class.
 * The Task class contains a description and completion status, which is common to all tasks.
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    // Aliases from Constants
    protected static final String SPACE = Constants.SPACE;
    protected static final String SEPARATOR = Constants.ENCODE_TASK_SEPARATOR;
    protected static final String COLON = Constants.COLON;
    protected static final String OPEN_SQUARE_BRACKET = Constants.OPEN_SQUARE_BRACKET;
    protected static final String CLOSE_SQUARE_BRACKET = Constants.CLOSE_SQUARE_BRACKET;
    protected static final String OPEN_ROUND_BRACKET = Constants.OPEN_ROUND_BRACKET;
    protected static final String CLOSE_ROUND_BRACKET = Constants.CLOSE_ROUND_BRACKET;

    /**
     * Constructs a Task with the specified description and completion status.
     * 
     * @param description represents the description of the {@code Task}.
     * @param isDone      represents the completion status of the {@code Task}.
     */
    public Task(String description, boolean isDone) {
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description) {
//        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes the task with empty string description and set the task to be not done. Default constructor.
     */
    public Task() {
        description = Constants.EMPTY_STRING;
        this.isDone = false;
    }

    /**
     * Returns the description of the {@code Task}.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the completion status of the {@code Task}.
     * 
     * @return true if the {@code Task} is done; false otherwise.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Returns an integer value that corresponds to the completion status of the {@code Task}.
     * 
     * @return 1 if the {@code Task} is done; 0 otherwise.
     */
    public int getIsDoneValue() {
        int doneValue = Constants.ZERO;
        if (getIsDone()) {
            doneValue = Constants.ONE;
        }
        return doneValue;
    }

    /**
     * Sets the isDone field (completion status of the {@code Task}).
     * 
     * @param isDone represents the new completion status of the {@code Task}.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns a string representation of the completion status (status icon) of the {@code Task}.
     * 
     * @return "X" if the {@code Task} is done; otherwise it returns a space.
     */
    public String getStatusIcon() {
        if (getIsDone()) {
            return Constants.X;
        } else {
            return Constants.SPACE;
        }
    }

    /**
     * Returns a string representation of the {@code Task}.
     * This includes its status icon and the description of the {@code Task}.
     * 
     * @return a string representation of the {@code Task} object.
     */
    // Equivalent to 'decoded' task, i.e. tasks present in TaskList
    @Override
    public String toString() {
        return OPEN_SQUARE_BRACKET + getStatusIcon() + CLOSE_SQUARE_BRACKET + SPACE + getDescription();
    }

    /**
     * Generates a formatted string to encode the task for writing / saving / storing to the tasks file.
     * The encoded string includes the integer representation of 
     * the completion status and the description of the {@code Task}.
     * 
     * @return encoded string representation of the {@code Task}.
     */
    public String encodeTask() {
        String description = getDescription().trim();
        assert description != null && !description.isEmpty() : "Task description should not be null or empty";
        String separator = Constants.ENCODE_TASK_SEPARATOR;
        return separator + getIsDoneValue() + separator + description;
    }

    /**
     * Determines if the task occurs on a specified date.
     * Abstract method to be implemented by the subclasses.
     * 
     * @param date represents the date to check against.
     * @return true if the task occurs on a specified date; false otherwise.
     */
    public abstract boolean isOnDate(LocalDate date);

    public LocalDateTime getTaskDateTime() {
        Task task = this;
        if (task instanceof Deadline) {
            return ((Deadline) task).getDue();
        } else if (task instanceof Event) {
            return ((Event) task).getStartDateTime();
        }
        return null;
    }

    public void assertValidEncodedTask(String encodedTask) {
        assert encodedTask != null && !encodedTask.isEmpty() : "Encoded task should not be null or empty";
    }
}
