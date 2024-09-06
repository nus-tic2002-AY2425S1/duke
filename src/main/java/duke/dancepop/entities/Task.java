package duke.dancepop.entities;

import duke.dancepop.enums.TaskEnum;

public abstract class Task {
    private final TaskEnum type;
    private boolean done;
    private final String description;

    public Task(String description) {
        this.description = description;
        this.type = getType();
    }

    protected abstract TaskEnum getType();

    public String getDescription() { return description; }

    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDone(String done) {
        this.done = Boolean.parseBoolean(done);
    }

    public String toString() {
        String done = getDone() ? "X" : " ";
        return "[" + type.getValue() + "]" + "[" + done + "] " + description;
    }
}
