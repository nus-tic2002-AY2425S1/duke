public class Deadline extends Task {
    protected String by;
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }
    // (Wk 5) Level-4 Update return message for Deadline Task
    @Override
    public String printAddedTask(){
        return "\t[D]" + super.printAddedTask()  + " (by: " + by + ")";
    }
}