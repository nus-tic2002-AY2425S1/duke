public class Deadline extends Task {
    protected String dueDateTime;

    public Deadline(String description, String dueDateTime) {
        super(description);
        taskType = TaskType.DEADLINE;
        this.dueDateTime = dueDateTime;
    }

    public String getListView() {
        return "[" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description + " (by: " + dueDateTime + ")";
    }

    public String getFileView() {
        return this.getTaskIcon() + " | " +
                this.status.getBoolean() + " | " +
                this.getDescription() + " | " +
                dueDateTime;
    }
}
