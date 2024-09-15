public class InvalidTaskOperationException extends Exception {
    private final String taskOperation;

    public InvalidTaskOperationException(String message, String taskOperation) {
        super(message);
        this.taskOperation = taskOperation;
    }

    public String getTaskOperation() {
        return taskOperation;
    }
}
