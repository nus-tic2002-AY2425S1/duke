package alice.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone(boolean b) {
        this.isDone = b;
    }
    public String getDescription() {
        return description;
    }

    public abstract String print();
    public abstract String toSave();

}