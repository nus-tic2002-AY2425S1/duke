public class Todo extends Task {

    public Todo(String description){
        super(description);
        taskType = TaskType.TODO;
    }

    public String getListView() {
        return "[" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description;
    }

    public String getFileView() {
        return this.getTaskIcon() + " | " +
                this.status.getBoolean() + " | " +
                this.getDescription();
    }
}
