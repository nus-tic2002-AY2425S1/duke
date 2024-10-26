package checkbot.Task;

public class Todo extends Task {

    public Todo(String description){
        super(description);
        taskType = TaskType.TODO;
    }

    public String getListView() {
        return "[Priority: " + this.getPriority() + "][" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description;
    }

    public String getFileView() {
        return this.getTaskIcon() + " | " +
                this.status.toString() + " | " +
                this.priority.getPriority() + " | " +
                this.getDescription();
    }
}
