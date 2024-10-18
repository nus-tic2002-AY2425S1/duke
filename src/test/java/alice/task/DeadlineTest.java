package alice.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest extends Task {
    private String by;


    public DeadlineTest(String description, LocalDate by) {
        super(description);
        this.isDone = false;
        this.by = by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public DeadlineTest(String description, boolean isDone, LocalDate by) {
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
