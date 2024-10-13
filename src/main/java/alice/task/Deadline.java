package alice.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.isDone = false;
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
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
