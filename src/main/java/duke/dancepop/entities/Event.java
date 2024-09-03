package duke.dancepop.entities;

import duke.dancepop.enums.TaskEnum;

public class Event extends Task {
    private String start;
    private String end;

    // TODO: Fix design for inheritance
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    protected TaskEnum getType() {
        return TaskEnum.EVENT;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String toString() {
        return super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
