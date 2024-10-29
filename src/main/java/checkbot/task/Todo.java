package checkbot.task;

public class Todo extends Task {

    public Todo(String description){
        super(description);
        taskType = TaskType.TODO;
    }

    /**
     * Displays task in list view for user to see in UI.
     */
    public String getListView() {
        return TaskList.tasks.indexOf(this)+1 + ". [" + this.getPriorityIcon() + "][" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description;
    }

    /**
     * Stringifies task for storing of task in storage file.
     */
    public String getFileView() {
        return this.getTaskIcon() + " | " +
                this.status.toString() + " | " +
                this.priority.toString() + " | " +
                this.getDescription();
    }
}
