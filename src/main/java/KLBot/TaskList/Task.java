package KLBot.TaskList;

/**
 * The Task class represents a task with a description and a completion status.
 * It provides methods to mark the task as completed or incomplete, retrieve its description,
 * and convert the task to a string format suitable for display or saving to a file.
 */
public class Task {
    protected boolean completed;
    protected String listDescription;

    /**
     * Constructs a new Task with description.
     *
     * @param listDescription The description of the task.
     */
    public Task(String listDescription) {
        this.listDescription = listDescription;
        this.completed = false;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {
        completed = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        completed = false;
    }

    /**
     * Returns the status icon of the task.
     * The status icon is "X" if the task is completed, and a space (" ") if the task is incomplete.
     *
     * @return A string of the status icon of the task
     */
    public String getStatusIcon() {
        return (completed ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return listDescription;
    }

    /**
     * Returns a string representation of the task in the format:
     * "[X] description" if the task is completed, or "[ ] description" if incomplete.
     *
     * @return A string description of the task with its completion status.
     */
    @Override
    public String toString() {
        return "[" + (completed ? "X" : " ") + "] " + listDescription;
    }

    /**
     * Converts the task to a format suitable for saving to a file.
     * The format is: "type | completed status | description".
     *
     * @return A string with the task in the format stated above for file storage.
     */
    public String toFileFormat() {
        return " | " + (completed ? "1" : "0") + " | " + listDescription;
    }

    /**
     * Creates a task object (ToDo, Deadline, or Event).
     * The format of the input string must be: "type | completion status | description | additional info".
     *
     * @param taskData The string representing the task data in the format "type | completion status | description | additional info".
     * @return A Task object or null if the input is invalid.
     */
    public static Task fromFileFormat(String taskData) {
        //Solution below adapted from https://stackoverflow.com/questions/10796160/splitting-a-java-string-by-the-pipe-symbol-using-split
        String[] parts = taskData.split(" \\| ");

        if (parts.length >= 3) {
            if (parts[0].equals("T")) {
                ToDo task = new ToDo(parts[2].trim());
                if (parts[1].equals("1")) {
                    task.markAsCompleted();
                }
                return task;
            } else if (parts[0].equals("D")) {
                if (parts.length == 4) {
                    Deadline task = new Deadline(parts[2].trim(), parts[3].trim());
                    if (parts[1].equals("1")) {
                        task.markAsCompleted();
                    }
                    return task;
                }
            } else if (parts[0].equals("E")) {
                if (parts.length == 5) {
                    Event task = new Event(parts[2].trim(), parts[3].trim(), parts[4].trim());
                    if (parts[1].equals("1")) {
                        task.markAsCompleted();
                    }
                    return task;
                }
            }
        }

        return null;
    }
}
