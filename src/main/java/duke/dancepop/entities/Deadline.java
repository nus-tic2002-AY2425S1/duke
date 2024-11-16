package duke.dancepop.entities;

import duke.dancepop.enums.TaskEnum;
import duke.dancepop.utils.DateTimeUtil;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public TaskEnum getType() {
        return TaskEnum.DEADLINE;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Returns a string representation of deadline task
     * Example output: [taskType][isDone] description (by: deadline)
     *
     * @return A string containing task type, isDone, description, deadline
     */
    public String toString() {
        return super.toString() + " (by: " + DateTimeUtil.toString(deadline) + ")";
    }
}
