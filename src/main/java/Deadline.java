import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{


    // Add in Deadline variable (Change to used local date)
    protected LocalDate by;

    public Deadline(String taskName, String by){
        super(taskName);
        this.by = LocalDate.parse(by);
    }

    @Override
    protected String getTaskType(){
        return "D";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + taskName + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return getTaskType() + " | " + (isDone ? "1" : "0") + " | " + taskName + " | " + by;
    }
}
