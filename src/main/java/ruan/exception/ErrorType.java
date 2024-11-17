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
    FAIL_TO_LOAD_TASK("Failed to load task."),
    DUPLICATE_TASK("This task already exists in your list.");

    private final String message;

    /**
     * Constructs an ErrorType with the specified error message
     * @param message Detailed error message associated with this error type
     */
    ErrorType(String message) {
        this.message = "Oh no! "+message;
    }

    /**
     * Gets the error message associated with this error type
     * @return The error message as a string
     */
    public String getMessage() {
        return message;
    }
}