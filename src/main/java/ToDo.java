public class ToDo extends Task {

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    protected String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + taskName;
    }

}
