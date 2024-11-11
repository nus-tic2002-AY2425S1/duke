package task;

import common.Constants;

import java.time.LocalDate;

/**
 * Represents a task with a fixed duration.
 * Extends from the {@link Task} class and includes a duration attribute
 * that specifies how long the task is expected to take.
 */
public class FixedDuration extends Task {

    private final double duration;
    private static final String SPACE = Constants.SPACE;

    /**
     * Constructs a FixedDuration task with the specified description, completion status, and duration.
     *
     * @param description represents a brief description of the task.
     * @param isDone      indicates whether the task is completed.
     * @param duration    represents the amount of time that the task requires, in hours.
     */
    public FixedDuration(String description, boolean isDone, double duration) {
        super(description, isDone);
        this.duration = duration;
    }

    /**
     * Constructs a FixedDuration task with the specified description and duration.
     * The task is initialized as not done.
     *
     * @param description represents a brief description of the task.
     * @param duration    represents the amount of time that the task requires, in hours.
     */
    public FixedDuration(String description, double duration) {
        super(description);
        this.duration = duration;
    }

    /**
     * Returns the duration of the task in hours.
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Returns a string representation of the FixedDuration task.
     * The string includes the task type (FD), description, completion status, and duration.
     */
    @Override
    public String toString() {
        return Constants.OPEN_SQUARE_BRACKET + TaskType.FIXED_DURATION + Constants.CLOSE_SQUARE_BRACKET +
            super.toString() + SPACE + Constants.OPEN_ROUND_BRACKET + Constants.DURATION + Constants.COLON +
            SPACE + getDuration() + SPACE + Constants.HOURS + Constants.CLOSE_ROUND_BRACKET;
    }

    /**
     * Encodes the FixedDuration task into a string format suitable for storage.
     * The encoded string includes the task type, description, completion status, and duration.
     *
     * @return a string encoding of the FixedDuration task
     */
    @Override
    public String encodeTask() {
        return TaskType.FIXED_DURATION + super.encodeTask() + Constants.ENCODE_TASK_SEPARATOR +
            getDuration() + SPACE + Constants.HOURS;
    }

    /**
     * Determines whether the task is scheduled for a specific date.
     * For FixedDuration tasks, this method always returns false as they do not have a specific date.
     *
     * @param date represents the date to check against.
     * @return false, indicating that the task is not scheduled for any specific date.
     */
    @Override
    public boolean isOnDate(LocalDate date) {
        return false;
    }

}