public class Deadline extends Task{
    private String doneBy;

    public Deadline(String task, String doneBy) {
        super(task);
        this.doneBy = doneBy;
    }

    @Override
    public String printTask() {
        return "[D] " + super.printTask() + " (by: " + doneBy + ")";
    }
}
