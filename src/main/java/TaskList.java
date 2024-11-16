public class TaskList {
    protected boolean completed;
    protected String listDescription;

    public TaskList(String listDescription) {
        this.listDescription = listDescription;
        this.completed = false;
    }
    public void markAsCompleted() {
        completed = true;
    }

    public void markAsIncomplete() {
        completed = false;
    }

    @Override
    public String toString() {
        return "[" + (completed ? "X" : " ") + "] " + listDescription;
    }
}