package ruan.task;

import ruan.datetime.DateTime;

/**
 * Represents a task with a duration
 * Inherits from Task and includes a from and to datetime
 * Coverts input data into string for display or to be store in file
 */

public class Event extends Task {
    private DateTime from;
    private DateTime to;

    /**
     * Constructs a Event task with a given description and from & to datetime.
     * @param description description/command of the task.
     * @param from from day/datetime of the task as a LocalDateTime object (if applicable).
     * @param to to day/datetime of the task as a LocalDateTime object (if applicable).
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = new DateTime(from);
        this.to = new DateTime(to);
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " /from " + from.DateTimeToString() + " /to " + to.DateTimeToString();
    }

    @Override
    public String toString() {
        //return formatted task string
        return "[E]" + super.toString() + " (from: " + from.DateTimeToString() + " to: " + to.DateTimeToString() + ")";
    }

}
