package KLBot.TaskList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;
    protected String eventDesc;
    DateTimeFormatter formatter;
    public Event(String description, String fromDateTime, String toDateTime) {
        super(description);
        this.eventDesc=description;

        if(fromDateTime.contains("T")||toDateTime.contains("T")){
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        }else{
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        }

        this.fromDateTime = LocalDateTime.parse(fromDateTime, formatter);
        this.toDateTime = LocalDateTime.parse(toDateTime, formatter);
    }

    @Override
    public String getDescription() {
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return eventDesc +  " (from: " + fromDateTime.format(formatter) + " to: " + toDateTime.format(formatter) + ")";
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + fromDateTime.format(formatter) + " to: " + toDateTime.format(formatter) + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (completed ? "1" : "0") + " | " + listDescription + " | " + fromDateTime.toString() + " | " + toDateTime.toString();
    }
}