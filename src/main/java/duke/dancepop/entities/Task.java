package duke.dancepop.entities;

import duke.dancepop.enums.TaskEnum;

public abstract class Task {
    private final TaskEnum type;
    private boolean done;
    private final String description;

    public Task(String description) {
        this.description = description;
        this.type = getType();  // Template method to be defined by subclasses
    }

    protected abstract TaskEnum getType();

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String toString() {
        String done = getDone() ? "X" : " ";
        return "[" + type.getValue() + "]" + "[" + done + "] " + description;
    }
}
