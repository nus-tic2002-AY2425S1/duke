package task;

import common.Constants;
import parser.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a task with a specific due date and time.
 * Deadlines are tasks that need to be done before a specific date/time, e.g., submit report by 11/10/2019 5pm.
 * It extends from the {@code Task} class and provides additional functionality for managing deadlines.
 * It encapsulates the task description, completion status, and due date.
 */
public class Deadline extends Task {

    protected final LocalDateTime due;

    /**
     * Constructs a {@code Deadline} task with the specified description, completion status, and due date / time.
     *
     * @param description represents the description of the {@code Deadline} task.
     * @param isDone      represents the completion status of the {@code Deadline} task.
     * @param due         represents the due date / time of the {@code Deadline} task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime due) {
        super(description, isDone);
        this.due = due;
    }

    /**
     * Constructs a {@code Deadline} task with the specified description and due date / time.
     *
     * @param description represents the description of the {@code Deadline} task.
     * @param due         represents the due date / time of the {@code Deadline} task.
     */
    public Deadline(String description, LocalDateTime due) {
        super(description);
        this.due = due;
    }

    /**
     * Returns the due date / time of the {@code Deadline} task as a LocalDateTime object.
     */
    public LocalDateTime getDue() {
        return due;
    }

    /**
     * Returns the formatted due date / time as a String
     */
    public String getFormattedDue() {
        return DateTimeParser.formatDateTime(due);
    }

    /**
     * Returns a String representation of the {@code Deadline} task object.
     * The format includes the task type (Deadline), description, completion status, and due date / time.
     */
    @Override
    public String toString() {
        final String SPACE = Constants.SPACE;

        // If description does not end with a space, add a space behind it
        if (!description.endsWith(SPACE)) {
            description += SPACE;
        }

        // String formattedDue = DateTimeParser.formatDateTime(due);
        return Constants.OPEN_SQUARE_BRACKET + TaskType.DEADLINE + Constants.CLOSE_SQUARE_BRACKET +
            super.toString() +
            Constants.OPEN_ROUND_BRACKET + Constants.BY + Constants.COLON +
            Constants.SPACE + getFormattedDue() + Constants.CLOSE_ROUND_BRACKET;
    }

    /**
     * Encodes the {@code Deadline} task into a String format that is suitable for storage.
     * The format includes the task type (Deadline), completion status, description, and due date / time.
     *
     * @return a String representation of the encoded {@code Deadline} task.
     */
    @Override
    public String encodeTask() {
        String separator = Constants.ENCODE_TASK_SEPARATOR;
        // Construct the final encoded task without leading or trailing spaces
        return TaskType.DEADLINE + super.encodeTask() + separator + getFormattedDue();
    }

    /**
     * Checks if the task is due on a specified date.
     *
     * @param date the date to check against.
     * @return true if the task is due on the specified date, false otherwise.
     */
    @Override
    // https://www.geeksforgeeks.org/compare-dates-in-java/
    public boolean isOnDate(LocalDate date) {
        return getDue().toLocalDate().isEqual(date);
    }

}
