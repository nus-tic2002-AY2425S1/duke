public class Task {
    protected String description;
    protected boolean isDone;

    //initial Task
    public Task(String description) {
        this.description = description;
        this.isDone = false;//default all item set to not done
    }

    // get item status
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    //mark item as done
    public void markDone() {
        isDone = true; 
    }

    //unmark item, set not done
    public void unmarkDone() {
        isDone = false; 
    }

    @Override
    //convert into string msg
    public String toString() {
        return "[" + getStatusIcon() + "] " + description; 
    }
}
