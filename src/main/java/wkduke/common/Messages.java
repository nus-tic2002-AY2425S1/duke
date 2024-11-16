package wkduke.common;

/**
 * Contains common messages used throughout the application.
 * These messages are displayed to the user's CLI for guidance, feedback, or error handling.
 */
public class Messages {
    public static final String MESSAGE_WELCOME = "Hello! I'm WKDuke. What can I do for you?";
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!";
    public static final String MESSAGE_INVALID_TASK_FORMAT = "Invalid task format!";

    public static final String MESSAGE_INVALID_TASK_NUMBERS_FORMAT = "Invalid task number argument format! '%s'";
    public static final String MESSAGE_INVALID_TASK_NUMBER = "Invalid task number 'Task not found'!";

    public static final String MESSAGE_INVALID_DATETIME_FORMAT = "Invalid datetime argument format!";
    public static final String MESSAGE_INVALID_DATETIME_RANGE = MESSAGE_INVALID_DATETIME_FORMAT + " '%s' is after '%s'!";

    public static final String MESSAGE_INVALID_TASK_ENCODED_FORMAT = "Invalid encoded task content!";
    public static final String MESSAGE_INVALID_DEADLINE_ENCODED = "Deadline task missing 'by' information!";
    public static final String MESSAGE_INVALID_EVENT_ENCODED = "Event task missing 'from' or 'to' information!";

    public static final String MESSAGE_CREATE_FILE_ERROR = "Error while creating folder or file!";
    public static final String MESSAGE_READ_FILE_ERROR = "Error while reading from file!";
    public static final String MESSAGE_WRITE_FILE_ERROR = "Error while writing to file!";
    public static final String MESSAGE_FILE_PATH_ERROR = "Storage file should end with '.txt'";

    public static final String MESSAGE_AVAILABLE_COMMAND = "list, todo, deadline, event, mark, unmark, find, sort, update-priority, bye";

}
