package starkchatbot.taskmanager;

/**
 * Represents a task with a description and status.
 */
public class Task {
    private final String description;
    private String status = " ";

    /**
     * Constructs a Task with the specified description.
     * The task is initially unmarked.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the status of the task to either marked or unmarked.
     *
     * @param status is a string indicating the desired status need to ("mark" or "unmark").
     */
    public void setStatus(String status) {
        if (status.equalsIgnoreCase("mark")) {
            this.status = "X";
        } else if (status.equalsIgnoreCase("unmark")) {
            this.status = " ";
        }
    }

    /**
     * Returns the status of the task.
     *
     * @return The task status, either "X" for marked or " " for unmarked.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A formatted string representing the task in the format "[status] description".
     */
    public String printTask() {
        return "[" + status + "] " + description;
    }
}
