package alice.task;

/**
 * <h1>Task</h1>
 * The Task abstract class provides the template
 * for the implementation of the different
 * specific tasks, such as todo, event and deadline.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
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