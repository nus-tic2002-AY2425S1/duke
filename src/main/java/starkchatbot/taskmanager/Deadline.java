package starkchatbot.taskmanager;

public class Deadline extends Task{

    private String doneBy;

    public Deadline(String task, String doneBy) {
        super(task+ " (by: " + doneBy + ")");
        this.doneBy = doneBy;
    }

    @Override
    public String printTask() {
        return "[D] " + super.printTask() ;
    }

    public String getDoneBy() {
        return doneBy;
    }

}
