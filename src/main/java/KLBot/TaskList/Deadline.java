package KLBot.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadlineDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected String deadlineDesc;

    public Deadline(String description, String deadlineDate) {
        super(description);
        this.deadlineDesc=description;
        this.deadlineDate = LocalDate.parse(deadlineDate);
    }

    @Override
    public String getDescription() {
        return deadlineDesc + " (by: " + deadlineDate.format(formatter) + ")";
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadlineDate.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        return "D | " + (completed ? "1" : "0") + " | " + listDescription + " | " + deadlineDate.toString();
    }
}