package alice.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>Deadline</h1>
 * The Deadline class details the implementation
 * of deadline task object which consists of
 * description and the /by date.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class Deadline extends Task {
    private String by;


    public Deadline(String description, LocalDate by) {
        super(description);
        this.isDone = false;
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public void setBy(String by) {
        this.by = by;
    }
    public String getBy() {
        return by;
    }

    @Override
    public String print() {
        return "[D]["+ (isDone ? "X" : " " ) +"] description: " + description + " (by: " + by + ")";
    }

    @Override
    public String toSave() { return "D" + (isDone ? " true " : " false " ) + description + " /by " + by; }
}
