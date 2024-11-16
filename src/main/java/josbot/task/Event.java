package josbot.task;

import josbot.parser.DateTimeParser;

import java.time.LocalDateTime;

/**
 * Represent Event Task
 */

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Creates an Event task with its description and date & time
     *
     * @param description description of the event task
     * @param from        'from' date&time of the event
     * @param to          'to' date&time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Return LocalDateTime of the Event 'to' value
     *
     * @return LocalDateTime of the Event 'to' value
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Return LocalDateTime of the Event 'from' value
     *
     * @return LocalDateTime of the Event 'from' value
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Return LocalDateTime of the Event 'from' value
     *
     * @return LocalDateTime of the Event 'from' value
     */

    @Override
    public LocalDateTime getDateTime() {
        return from;
    }

    /**
     * Return Event type
     *
     * @return Event type
     */

    @Override
    public String getType() {
        return "E";
    }

    /**
     * Return String of the Event details
     *
     * @return String of the Event details
     */
    @Override
    public String toString() {
        DateTimeParser dtParser = new DateTimeParser();
        return "[E]" + super.toString() + " (From: " + dtParser.convertToString(getFrom(), "view") + " To: " + dtParser.convertToString(getTo(), "view") + ")";
    }
    //have to change this
}
