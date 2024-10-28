package checkbot.task;

public class Todo extends Task {

    public Todo(String description){
        super(description);
        taskType = TaskType.TODO;
    }

    /**
     * Display task in list view for user to see in UI.
     *
     * @return String
     */
    public String getListView() {
        return "[" + this.getPriorityIcon() + "][" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description;
    }

    /**
     * Stringify task for storing of task in storage file.
     *
     * @return String
     */
    public String getFileView() {
        return this.getTaskIcon() + " | " +
                this.status.toString() + " | " +
                this.priority.toString() + " | " +
                this.getDescription();
    }
}
