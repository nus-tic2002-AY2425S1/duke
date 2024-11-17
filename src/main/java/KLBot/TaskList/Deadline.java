package KLBot.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a description and a deadline date.
 * This class extends the Task class and provides specific functionality for handling deadline tasks.
 */
public class Deadline extends Task {
    protected LocalDate deadlineDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected String deadlineDesc;

    /**
     * Constructs a Deadline task with a description and a deadline date.
     *
     * @param description  The description of the deadline task.
     * @param deadlineDate The deadline date in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDesc = description;
        this.deadlineDate = LocalDate.parse(deadlineDate);
    }

    /**
     * Returns the description of the task, including the deadline date formatted as "MMM dd yyyy".
     *
     * @return A string with the description of the task, including the formatted deadline date.
     */
    @Override
    public String getDescription() {
        return deadlineDesc + " (by: " + deadlineDate.format(formatter) + ")";
    }

    /**
     * Returns a string with its description and formatted deadline date when showing list.
     *
     * @return A string with its description and formatted deadline date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate.format(formatter) + ")";
    }

    /**
     * Converts the Deadline task to a format suitable for saving to a file.
     * The format is: "D | completed status | description | deadline date".
     *
     * @return A string of the Deadline task in the format stated above for file storage.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (completed ? "1" : "0") + " | " + listDescription + " | " + deadlineDate.toString();
    }
}