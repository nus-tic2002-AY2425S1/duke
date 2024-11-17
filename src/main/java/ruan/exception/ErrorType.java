package ruan.exception;
/**
 * List of enum for errors
 */
public enum ErrorType {
    EMPTY_DESCRIPTION("The instruction cannot be empty."),
    UNKNOWN_DESCRIPTION("Your instruction is not recognize."),
    INVALID_TASK_NUMBER("Task number not found, please try another one."),
    MISSING_DEADLINE("Deadline is missing."),
    MISSING_EVENT_TIMES("Event times is/are missing."),
    INVALID_TASK_FORMAT("Invalid task format."),
    INVALID_TASK_TYPE("Invalid task type."),
    FAIL_TO_SAVE("Failed to save task."),
    FAIL_TO_LOAD_FILE("Failed to load file."),
    FAIL_TO_LOAD_TASK("Failed to load task.");

    private final String message;

    ErrorType(String message) {
        this.message = "Oh no! "+message;
    }

    public String getMessage() {
        return message;
    }
}