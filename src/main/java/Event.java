import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate from;
    protected LocalDate to;
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    // Construct an event task with schedule timing
    public Event(String taskName, String from, String to){
        super(taskName);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    protected  String getTaskType(){
        return "E";
    }

    // output the event to string with [E] and the schedule timeline for event from till to.
    @Override
    public String toString(){
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + taskName + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String toFileString() {
        return getTaskType() + " | " + (isDone ? "1" : "0") + " | " + taskName + " | " + from + " | " + to;
    }

}
