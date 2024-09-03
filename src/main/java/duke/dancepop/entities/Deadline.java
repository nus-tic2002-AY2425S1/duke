package duke.dancepop.entities;

import duke.dancepop.enums.TaskEnum;

public class Deadline extends Task {
    private String deadline;

    // TODO: Fix design for inheritance
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    protected TaskEnum getType() {
        return TaskEnum.DEADLINE;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}
