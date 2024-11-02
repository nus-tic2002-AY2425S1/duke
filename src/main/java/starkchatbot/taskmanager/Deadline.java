package starkchatbot.taskmanager;

public class Deadline extends Task{

    private String doneBy;
    private String readableDateTime;


    public Deadline(String task, String doneBy) {
        super(task);
        this.doneBy = doneBy;
        readableDateTime= DateTimeParser.parseDateTime(doneBy);
    }

    @Override
    public String printTask() {
        return "[D] " + super.printTask() + " (by: " + readableDateTime + ")";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + doneBy + ")";
    }

    public String getDoneBy() {
        return doneBy;
    }

}
