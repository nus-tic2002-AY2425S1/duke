package wkduke.exception;

/**
 * Represents an exception that is thrown when an error occurs with the format of a task.
 * This exception is used to indicate issues such as invalid task data or incorrect task arguments.
 */
public class TaskFormatException extends WKDukeException {
    /**
     * Constructs a TaskFormatException with the specified error message, detailed information, and help text.
     *
     * @param message The error message describing the task format issue.
     * @param detail  Additional detail about the task format issue.
     * @param help    Suggested help or guidance for resolving the issue.
     */
    public TaskFormatException(String message, String detail, String help) {
        super(message, detail, help);
    }
}
