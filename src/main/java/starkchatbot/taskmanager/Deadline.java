package starkchatbot.taskmanager;


/**
 * Represents a deadline task with a specific due date/time.
 * This class extends Task and includes additional attributes
 */
public class Deadline extends Task {

    private final String doneBy;
    private final String readableDateTime;


    /**
     * Constructs a Deadline task with the specified description and due date/time.
     * The due date/time is converted to human-readable format using DateTimeParser.
     *
     * @param task   The description of the deadline task.
     * @param doneBy The due date/time for the task in raw format.
     */
    public Deadline(String task, String doneBy) {
        super(task);
        this.doneBy = doneBy;
        readableDateTime = DateTimeParser.parseDateTime(doneBy);
    }


    /**
     * Returns a formatted string representing the deadline task, prefixed with "[D]" and
     * including its status, description, and due date/time.
     *
     * @return A string in the format "[D][status] description (by: Due data/time)".
     */
    @Override
    public String printTask() {
        return "[D] " + super.printTask() + " (by: " + readableDateTime + ")";
    }


    /**
     * @return A string in the format "description (by: due Date/time)".
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + doneBy + ")";
    }



}
