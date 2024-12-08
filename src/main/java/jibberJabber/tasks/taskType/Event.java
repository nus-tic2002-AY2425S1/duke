package jibberJabber.tasks.taskType;

import jibberJabber.tasks.Task;

import java.time.LocalDate;
/**
 * The Event class represents an Event task (subclass) that extends the base Task class.
 * Includes a date range for the task (from and to)
 */
public class Event extends Task {
    public LocalDate from;
    public LocalDate to;
    /**
     * Constructs an Event task with the user's input - task name, from date and to date
     *
     * @param taskName The name of the task.
     * @param from The event's starting date (date time) of the task.
     * @param to The event's ending date (date time) of the task.
     */
    public Event(String taskName, LocalDate from, LocalDate to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }
    /**
     * Returns a formatted string representing the added event task.
     *
     * @return The formatted string with task details including task type, task name, start date (from), and end date (to).
     */
    @Override
    public String printAddedTask(){
        return "\t[E]" + super.printAddedTask()  + " (from: " + convertDateInputString(from) + " to: " + convertDateInputString(to) + ")";
    }
}
