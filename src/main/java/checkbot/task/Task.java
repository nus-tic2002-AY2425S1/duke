package checkbot.task;

public abstract class Task {
    protected String description;
    protected TaskStatus status;
    protected TaskType taskType;
    protected TaskPriority priority;

    /**
     * Constructs Task object. Sets status as not done and priority as not set.
     *
     * @param description Task description
     */
    public Task(String description) {
        this.description = description;
        status = TaskStatus.NOT_DONE;
        priority = TaskPriority.NOT_SET;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return status.getStatusIcon();
    }

    public TaskStatus getDone() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public String getTaskIcon(){
        return taskType.getTaskIcon();
    }

    /**
     * Returns task priority for list view.
     *
     * @return String of exclamation mark(s) if priority is set
     */
    public String getPriorityIcon(){
        return priority.getPriorityIcon();
    }

    public String getPriorityString(){
        return priority.toString();
    }

    public void setDone(boolean isDone) {
        if (isDone) {
            status = TaskStatus.DONE;
        } else {
            status = TaskStatus.NOT_DONE;
        }
    }

    public void setPriority(TaskPriority priority) {
        switch (priority) {
        case LOW:
            this.priority = TaskPriority.LOW;
            break;
        case MEDIUM:
            this.priority = TaskPriority.MEDIUM;
            break;
        case HIGH:
            this.priority = TaskPriority.HIGH;
            break;
        case NOT_SET:
            this.priority = TaskPriority.NOT_SET;
            break;
        default:
            // do nothing
        }
    }

    public abstract String getListView();
    public abstract String getFileView();
}
