

/**
 * Create a task class with task name and the status
*/

public class Task {
    protected String taskName;
    protected boolean isDone;


    /**
     * Constructor which help to construct a new task with the given name
     *
     * @param taskName which is the name of the task
     */
    public Task (String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    // New constructor for normal tasks without specific types - NEW
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }


    // Mark done task with X
    public String getStatusIcon(){
        // ✔ for Done, X for have not complete
        return (isDone? "✔" : " ");
    }

    /**
     * To mark the task as done
     */
    public void markAsDone(){
        isDone = true;
    }

    /**
     * To mark the task which had not done
     */
    // Task have not Done set to false
    public void unmarkAsDone(){
        isDone = false;
    }

    /**
     * Task type for subclass to override
     * @return a string that represent the task type. example "T", "D", "E" and "N" for normal task
     */
    protected String getTaskType(){
        return "N";
    }

    /**
     * output task as string
     * the string will include the task type, the status and the task name
     * @return a formatted string representing the task
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + taskName;
    }

    /**
     * String representation of task for saving to file
     * the format include the tasktype, task completion status and the task name
     * @return a string which represent the task formatted for file storage.
     */

    public String toFileString() {
        return getTaskType() + " | " + (isDone ? "1" : "0") + " | " + taskName;
    }
}
