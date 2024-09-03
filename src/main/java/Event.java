public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;

    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getTaskIcon() {
        return "E";
    }

    public String getListView() {
        return "[" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }
}
