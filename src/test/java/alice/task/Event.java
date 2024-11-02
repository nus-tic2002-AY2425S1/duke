package alice.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.isDone = false;
        this.from = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.to = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String print() {
        return "[E]["+ (isDone ? "X" : " " ) +"] description: " + description + " (from: " + from + " to: " + to + ")";
    }
    @Override
    public String toSave() { return "E" + (isDone ? " true " : " false " ) + description + " /from " + from + " /to " + to; }
}
