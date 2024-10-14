public class InvalidTaskFormatException extends Exception {
    private final TaskType taskType;

    public InvalidTaskFormatException(String message, TaskType taskType) {
        super(message);
        this.taskType = taskType;
    }

    public TaskType getTaskType() {
        return taskType;
    }
}
