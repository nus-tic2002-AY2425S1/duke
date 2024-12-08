package jibberJabber.tasks.taskType;

import jibberJabber.tasks.Task;
import java.time.LocalDate;
/**
 * The Deadline class represents a deadline task (subclass) that extends the base Task class.
 * Includes a deadline for the task.
 */
public class Deadline extends Task {
    public LocalDate by;
    /**
     * Constructs a Deadline task with the user's input - task name and due date.
     *
     * @param taskName The name of the task.
     * @param by The deadline (date time) for the task.
     */
    public Deadline(String taskName, LocalDate by) {
        super(taskName);
        this.by = by;
    }
    /**
     * Returns a formatted string representing the added deadline task.
     *
     * @return The formatted string with task details including task type, task name, and deadline.
     */
    @Override
    public String printAddedTask(){
        return "\t[D]" + super.printAddedTask() + " (by: " + convertDateInputString(by) + ")";
    }
}