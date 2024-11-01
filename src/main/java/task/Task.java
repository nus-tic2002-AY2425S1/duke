package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    // public abstract TaskType getTaskType();

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    // Initialize task with empty string description and set the task to be undone
    public Task() {
        description = "";
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public int getIsDoneValue() {
        int doneValue = 0;
        if (getIsDone()) {
            doneValue = 1;
        }
        return doneValue;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "X";
        } else {
            return " ";
        }
    }

    // Equivalent to 'decoded' task, i.e. tasks present in TaskList
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    // Generate string to write to file
    public String encodeTask() {
        String separator = " | ";
        return separator + getIsDoneValue() + separator + getDescription().trim();
    }

}
