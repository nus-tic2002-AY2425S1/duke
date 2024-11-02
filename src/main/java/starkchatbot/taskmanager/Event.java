package starkchatbot.taskmanager;

public class Event extends Task {

    private String starkAt;
    private String endBy;

    public Event(String task, String startAt, String endBy) {
        super(task + " (from: " + startAt + " to: " + endBy + ")");
        this.starkAt = startAt;
        this.endBy = endBy;
    }

    @Override
    public String printTask() {
        return "[E] " + super.printTask();
    }

    public String getStarkAt() {
        return starkAt;
    }
    public String getEndBy() {
        return endBy;
    }
}
