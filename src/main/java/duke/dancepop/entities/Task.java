package duke.dancepop.entities;

import duke.dancepop.enums.TaskEnum;

public abstract class Task {
    protected TaskEnum type;
    protected boolean done;
    protected String description;


    public boolean getDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String toString() {
        String done = getDone() ? "X" : "";
        // return "[" + type.getValue() + "]" + "[" + done + "]";
        return "[" + done + "] " + description;
    }
}
