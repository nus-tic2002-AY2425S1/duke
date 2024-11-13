package duke.dancepop.entities;

import duke.dancepop.enums.TaskEnum;
import duke.dancepop.utils.DateTimeUtil;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    // TODO: Fix design for inheritance
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public TaskEnum getType() {
        return TaskEnum.EVENT;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String toString() {
        return super.toString() + " (from: " + DateTimeUtil.toString(start) + " to: " + DateTimeUtil.toString(end) + ")";
    }
}
