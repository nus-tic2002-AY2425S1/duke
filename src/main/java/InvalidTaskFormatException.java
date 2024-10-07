public class InvalidTaskFormatException extends Exception {
    private final String taskType;

    public InvalidTaskFormatException(String message, String taskType) {
        super(message);
        this.taskType = taskType;
    }

    public String getTaskType() {
        return taskType;
    }
}
