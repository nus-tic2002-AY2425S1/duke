import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;
    //private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    // Define a common DateTimeFormatter to maintain consistency
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    // Construct an event task with schedule timing
    public Event(String taskName, LocalDateTime from, LocalDateTime to){
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    protected  String getTaskType(){
        return "E";
    }

    // output the event to string with [E] and the schedule timeline for event from till to.
    @Override
    public String toString(){
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + taskName + " (from: " + from.format(OUTPUT_FORMATTER) + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String toFileString() {
        return getTaskType() + " | " + (isDone ? "1" : "0") + " | " + taskName + " | " + from.format(FORMATTER) + " | " + to.format(FORMATTER);
    }

}
