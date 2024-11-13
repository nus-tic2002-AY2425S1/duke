package duke.dancepop.entities;

import duke.dancepop.enums.TaskEnum;

public abstract class Task {
    private final TaskEnum type;
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.type = getType();
    }

    public abstract TaskEnum getType();

    public String getDescription() {
        return description;
    }

    public boolean getDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setDone(String isDone) {
        this.isDone = Boolean.parseBoolean(isDone);
    }

    public String toString() {
        String done = getDone() ? "X" : " ";
        return "[" + type.getValue() + "]" + "[" + done + "] " + description;
    }
}
