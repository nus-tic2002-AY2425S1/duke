package KLBot.TaskList;

/**
 * The ToDo class represents a task with a description that needs to be done.
 * It extends the Task class and provides specific functionality for handling ToDo tasks.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string of the ToDo task description.
     *
     * @return A string of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the ToDo task to a format suitable for saving to a file.
     * The format is: "T | completed status | description".
     *
     * @return A string of the ToDo task in the format stated above for file storage..
     */
    @Override
    public String toFileFormat() {
        return "T | " + (completed ? "1" : "0") + " | " + listDescription;
    }
}