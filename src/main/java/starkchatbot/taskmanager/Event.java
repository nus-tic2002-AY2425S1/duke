package starkchatbot.taskmanager;

/**
 * Represents an event task with a start and end date/time.
 * This class extends task class and includes additional attributes
 */
public class Event extends Task {

    private final String startAt;
    private final String endBy;
    private final String readableStartAt;
    private final String readableEndAt;


    /**
     * Constructs an Event task with the specified description, start time, and end time.
     * The start and end times are converted to human-readable format using {@link DateTimeParser}.
     *
     * @param task    The description of the event task.
     * @param startAt The start time of the event in raw format.
     * @param endBy   The end time of the event in raw format.
     */
    public Event(String task, String startAt, String endBy) {
        super(task);
        this.startAt = startAt;
        this.endBy = endBy;
        this.readableStartAt = DateTimeParser.parseDateTime(startAt);
        this.readableEndAt = DateTimeParser.parseDateTime(endBy);
    }

    /**
     * @return A string in the format "[E][status] description (from: Start date/time to: End date/time)".
     */
    @Override
    public String printTask() {
        return "[E] " + super.printTask() + " (from: " + readableStartAt + " to: " + readableEndAt + ")";
    }


    /**
     * @return A string in the format "description (from:  Start date/time to: End date/time)".
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + startAt + " to: " + endBy + ")";
    }


}
