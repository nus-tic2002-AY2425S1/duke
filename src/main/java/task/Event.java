package task;

// Events are tasks that start at a specific date/time and ends at a specific date/time
// e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019

public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public Event(String description, String start, String end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    @Override
    public String toString() {
        // If description does not end with a space, add a space behind it
        if (!description.endsWith(" ")) {
            description += " ";
        } 
        return "[" + TaskType.EVENT + "]" + super.toString() + "(from: " + getStart() + " to: " + getEnd() + ")";
    }

    @Override
    public String encodeTask() {
        String separator = " | ";
        return TaskType.EVENT + super.encodeTask() + separator + getStart() + separator + getEnd();
    }

}
