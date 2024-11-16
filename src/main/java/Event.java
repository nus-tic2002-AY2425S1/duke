public class Event extends TaskList {

    protected String fromDateTime;
    protected String toDateTime;

    public Event(String description, String fromDateTime, String toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDateTime + " to: " + toDateTime + ")";
    }
}