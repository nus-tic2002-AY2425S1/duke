package starkchatbot.taskmanager;

public class Event extends Task {

    private String startAt;
    private String endBy;
    private String readableStartAt;
    private String readableEndAt;

    public Event(String task, String startAt, String endBy) {
        super(task );
        this.startAt = startAt;
        this.endBy = endBy;
        this.readableStartAt = DateTimeParser.parseDateTime(startAt);
        this.readableEndAt = DateTimeParser.parseDateTime(endBy);
    }

    @Override
    public String printTask() {
        return "[E] " + super.printTask() + " (from: " + readableStartAt + " to: " + readableEndAt + ")";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + startAt + " to: " + endBy + ")";
    }

    public String getStarkAt() {
        return startAt;
    }
    public String getEndBy() {
        return endBy;
    }
}
