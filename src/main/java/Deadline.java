// Deadlines are tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm

public class Deadline extends Task {

    protected String due;

    public Deadline(String description, boolean isDone, String due) {
        super(description, isDone);
        this.due = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + due + ")";
    }

}
