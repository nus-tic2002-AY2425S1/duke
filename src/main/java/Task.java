public abstract class Task {
    protected String description;
    protected TaskStatus status;
    protected TaskType taskType;

    public Task(String description) {
        this.description = description;
        status = TaskStatus.NOT_DONE;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return status.getStatusIcon();
    }

    public String getTaskIcon(){
        return taskType.getTaskIcon();
    }

    public void setDone(boolean done) {
        if (done) {
            status = TaskStatus.DONE;
        } else {
            status = TaskStatus.NOT_DONE;
        }
    }

    public abstract String getListView();
    public abstract String getFileView();
}
