package starkchatbot.taskmanager;

public class Deadline extends Task{

    public Deadline(String task, String doneBy) {
        super(task+ " (by: " + doneBy + ")");

    }

    @Override
    public String printTask() {
        return "[D] " + super.printTask() ;
    }
}
