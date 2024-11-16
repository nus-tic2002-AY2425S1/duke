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

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = Boolean.parseBoolean(isDone);
    }

    /**
     * Returns a string representation of task
     * Example output: [taskType][isDone] description
     *
     * @return A string containing task type, isDone, description
     */
    public String toString() {
        String done = getIsDone() ? "X" : " ";
        return "[" + type.getValue() + "]" + "[" + done + "] " + description;
    }
}
