public class Deadline extends Task {
    protected String dueDateTime;

    public Deadline(String description, String dueDateTime) {
        super(description);
        this.dueDateTime = dueDateTime;
    }

    public String getTaskIcon(){
        return "D";
    }

    public String getListView() {
        return "[" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description + " (by: " + dueDateTime + ")";
    }
}
