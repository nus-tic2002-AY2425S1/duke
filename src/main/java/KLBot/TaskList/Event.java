package KLBot.TaskList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents an event task with a description, start date-time, and end date-time.
 * It extends the Task class and provides specific functionality for handling event-related tasks.
 */
public class Event extends Task {

    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;
    protected String eventDesc;
    DateTimeFormatter formatter;

    /**
     * Constructs an Event task with a description, start date-time, and end date-time.
     * The input date-time strings are parsed into LocalDateTime objects using a DateTimeFormatter.
     *
     * @param description The description of the event task.
     * @param fromDateTime The start date-time of the event in string format.
     * @param toDateTime The end date-time of the event in string format.
     */
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

    /**
     * Returns the description of the event, including the formatted start and end datetime.
     * The datetime are formatted using the pattern "MMM dd yyyy HH:mm".
     *
     * @return A string of the event's description.
     */
    @Override
    public String getDescription() {
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return eventDesc +  " (from: " + fromDateTime.format(formatter) + " to: " + toDateTime.format(formatter) + ")";
    }

    /**
     * Returns a string of the event task with its description and the formatted start and end datetime when showing list.
     * The datetime are formatted using the pattern "MMM dd yyyy HH:mm".
     *
     * @return A string of the event task.
     */
    @Override
    public String toString() {
        formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + fromDateTime.format(formatter) + " to: " + toDateTime.format(formatter) + ")";
    }

    /**
     * Converts the Event task to a format suitable for saving to a file.
     * The format is: "E | completed status | description | start date-time | end date-time".
     *
     * @return A string of the event task in the format stated above for file storage.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (completed ? "1" : "0") + " | " + listDescription + " | " + fromDateTime.toString() + " | " + toDateTime.toString();
    }
}