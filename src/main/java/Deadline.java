public class Deadline extends Task{
    // Add in Deadline variable
    protected String by;

    public Deadline(String taskName, String by){
        super(taskName);
        this.by = by;
    }

    @Override
    protected String getTaskType(){
        return "D";
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() +"] " + taskName + " (by: " + by +")";
    }
}
