public class Todo extends Task {

    public Todo(String description){
        super(description);
        taskType = TaskType.TODO;
    }

    public String getListView() {
        return "[" + this.getTaskIcon() + "][" + this.getStatusIcon() + "] " +
                description;
    }
}
