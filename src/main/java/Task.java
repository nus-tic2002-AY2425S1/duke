public class Task {
    protected String taskName;
    protected boolean isDone;


    // Construct a new task

    public Task (String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        //this.taskType = taskType;
    }

    // Mark done task with X
    public String getStatusIcon(){
        // ✔ for Done, X for have not complete
        return (isDone? "✔" : " ");
    }

    // Task is Done Set to true.
    public void markAsDone(){
        isDone = true;
    }

    // Task have not Done set to false
    public void unmarkAsDone(){
        isDone = false;
    }

    // Task type for subclass to override
    protected String getTaskType(){
        return " ";
    }

    // Output task as string
    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + taskName;
    }

    // String representation of task for saving to file
    public String toFileString() {
        return getTaskType() + " | " + (isDone ? "1" : "0") + " | " + taskName;
    }
}
