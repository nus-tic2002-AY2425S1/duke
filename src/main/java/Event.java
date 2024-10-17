public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;

    public Event(String description, String startDateTime, String endDateTime) {
        super(description);
        taskType = TaskType.EVENT;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getListView() {
        return "[" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description + " (from: " + startDateTime + " to: " + endDateTime + ")";
    }

    public String getFileView() {
        return this.getTaskIcon() + " | " +
                this.status.toString() + " | " +
                this.getDescription() + " /from " +
                this.startDateTime + " /to " +
                this.endDateTime;
    }
}
