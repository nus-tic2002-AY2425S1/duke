package ruan.task;

import ruan.datetime.DateTime;
import ruan.constant.Constants;

/**
 * Represents a task with a deadline
 * Inherits from Task and includes a due date and time
 * Coverts input data into string for display or to be store in file
 */

public class Deadline extends Task {
    private DateTime by;
    
    /**
     * Constructs a Deadline task with a given description and due datetime.
     * @param description description/command of the task.
     * @param by due day/datetime of the task as a LocalDateTime object (if applicable).
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = new DateTime(by);
    }

    @Override
    public String toFileFormat() {
        String completionTime = (completionDateTime != null) ? completionDateTime.toString() : "null";
        return "D | " + (isDone ? "1" : "0") + " | " + description + Constants.SPLIT_BY_COMMAND + by.dateTimeToString() + " | " + completionTime;
    }

    @Override
    public String toString() {
        //return formatted task string
        return "[D]" + super.toString() + " (by: " + by.dateTimeToString() + ")";
    }

}
