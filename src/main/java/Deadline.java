import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    // Add in Deadline variable (Change to used local date)
    protected LocalDateTime by;

    // Define the date time format as a constant
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    public Deadline(String taskName, LocalDateTime by){
        super(taskName);
        this.by = by;
    }

    @Override
    protected String getTaskType(){
        return "D";
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + taskName + " (by: " + by.format(outputFormatter) + ")";
    }

    @Override
    public String toFileString() {
        return getTaskType() + " | " + (isDone ? "1" : "0") + " | " + taskName + " | " + by.format(FORMATTER);
    }
}
