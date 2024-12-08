package Chad.TaskList;

public class Event extends Task {

    protected String startsAt;
    protected String endsAt;

    public Event(String description, String startsAt, String endsAt) {
        super(description);
        this.startsAt = startsAt;
        this.endsAt = endsAt;
    }

    public String getEnddate() {
        return endsAt;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " from: " + startsAt + " to: " + endsAt;
    }
}