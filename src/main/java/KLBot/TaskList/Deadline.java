package KLBot.TaskList;

public class Deadline extends Task {
    protected String deadlineDate;

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    public String getBy() {
        return deadlineDate;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (completed ? "1" : "0") + " | " + listDescription + " | " + deadlineDate;
    }
}