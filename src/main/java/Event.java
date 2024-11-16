public class Event extends TaskList {

    protected String fromDateTime;
    protected String toDateTime;

    public Event(String description, String fromDateTime, String toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    public String getFrom() {
        return fromDateTime;
    }

    public String getTo() {
        return toDateTime;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDateTime + " to: " + toDateTime + ")";
    }

    @Override
    public String toFileFormat() {
        return "E | " + (completed ? "1" : "0") + " | " + listDescription + " | " + fromDateTime + " | " + toDateTime;
    }
}