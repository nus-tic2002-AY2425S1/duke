package alice.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest extends Task {
    protected String from;
    protected String to;

    public EventTest(String description, String from, String to) {
        super(description);
        this.isDone = false;
        this.from = from;
        this.to = to;
    }

    public EventTest(String description, boolean isDone, String from, String to) {
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
