public class Task {
    protected String taskName;
    protected boolean isDone;

    // Construct a new task
    public Task (String taskName){
        this.taskName = taskName;
        this.isDone = false;
    }

    // Mark done task with X
    public String getStatusIcon(){
        // ✔ for Done, X for have not complete
        return (isDone? "✔" : "X");
    }

    // Task is Done Set to true.
    public void markAsDone(){
        isDone = true;
    }

    // Task have not Done set to false
    public void unmarkAsDone(){
        isDone = false;
    }

    // Output task as string
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskName;
    }
}
