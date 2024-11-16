package jibberJabber.tasks.taskType;

import jibberJabber.tasks.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * The Event class represents an Event task (subclass) that extends the base Task class.
 * Includes a date range for the task (from and to)
 */
public class Event extends Task {
    public LocalDateTime from;
    public LocalDateTime to;
    /**
     * Constructs an Event task with the user's input - task name, from date and to date
     *
     * @param taskName The name of the task.
     * @param from The event's starting date (date time) of the task.
     * @param to The event's ending date (date time) of the task.
     */
    public Event(String taskName, LocalDateTime from, LocalDateTime to) {
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
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
        return "\t[E]" + super.printAddedTask()  + " (from: " + from.format(dateFormat) + " to: " + to.format(dateFormat) + ")";
    }
}
