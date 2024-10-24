package tasks;

import exception.DukeException;
import lombok.Getter;

import java.time.LocalDateTime;

import static parser.DateTimeParser.parseDateTime;
import static parser.DateTimeParser.formatDateTime;
import static parser.DateTimeParser.formatToFile;
import static parser.TaskParser.parseEvent;

/**
 * Event task with start time and end time
 */
@Getter
public class Event extends Task{
    private  LocalDateTime from;
    private  LocalDateTime to;

    /**
     * Constructor for Event.
     * @param description Description string of Event.
     * @param from The time in LocalDateTime event starts from.
     * @param to The time in LocalDateTime event emds.
     */
    public Event (String description, LocalDateTime from, LocalDateTime to){
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Convert Event object to String
     * @return Event object as a formatted string
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + formatDateTime(from) + " to: " + formatDateTime(to) + ")";
    }

    /**
     * Parse user input and create Event object.
     * @param eventString User input
     * @return Event object
     * @throws DukeException If event user input is improperly formatted
     */
    @Override
    public Task createTask(String eventString) throws DukeException {
        String [] eventParts = parseEvent(eventString);
        return new Event(eventParts[0], parseDateTime(eventParts[1]), parseDateTime(eventParts[2]));
    }

    @Override
    public void update(String eventString) throws DukeException {
        String [] eventParts = parseEvent(eventString);
        this.description = eventParts[0];
        this.from = parseDateTime(eventParts[1]);
        this.to = parseDateTime(eventParts[2]);
    }
    /**
     * Converts the Event task to a format suitable for file saving.
     *
     * @return A string in the format used to save the task to a file.
     */
    @Override
    public String toFileFormat(){
        return "E|" + (isDone ? 1:0) + "|" + description + "|" + formatToFile(from) + "|" + formatToFile(to);
    }
    
}
