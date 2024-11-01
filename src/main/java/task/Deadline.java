package task;
// Deadlines are tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm

public class Deadline extends Task {

    protected String due;

    public Deadline(String description, boolean isDone, String due) {
        super(description, isDone);
        this.due = due;
    }

    public Deadline(String description, String due) {
        this.description = description;
        this.due = due;
    }

    public String getDue() {
        return due;
    }

    @Override
    public String toString() {
        // If description does not end with a space, add a space behind it
        if (!description.endsWith(" ")) {
            description += " ";
        } 
        return "[" + TaskType.DEADLINE + "]" + super.toString() + "(by: " + getDue() + ")";
        // return "[D]" + super.toString() + "(by: " + getDue() + ")";
    }

    @Override
    public String encodeTask() {
        String separator = " | ";
        // Construct the final encoded task without leading or trailing spaces
        return TaskType.DEADLINE + super.encodeTask() + separator + getDue().trim();
    }

}
