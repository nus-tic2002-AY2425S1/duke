public class Deadline extends Task {
    protected String by;
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }
    @Override
    public String printAddedTask(){
        return "\t[D]" + super.printAddedTask()  + " (by: " + by + ")";
    }
}