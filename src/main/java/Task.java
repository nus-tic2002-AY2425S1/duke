public class Task {
    protected String description;
    protected boolean isDone;

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

    public boolean getDone() {
        return isDone;
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

    @Override
    public String toString() {
        // System.out.println("description is " + getDescription() + " isDone is " + getDone());
        return "[" + getStatusIcon() + "] " + getDescription();
    }

}
