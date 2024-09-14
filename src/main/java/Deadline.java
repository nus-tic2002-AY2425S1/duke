public class Deadline extends Task{
    // Add in Deadline variable
    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
