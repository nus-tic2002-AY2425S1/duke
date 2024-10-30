package wkduke.common;

public class Messages {
    public static final String MESSAGE_WELCOME = "Hello! I'm WKDuke. What can I do for you?";
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";

    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format!";
    public static final String MESSAGE_AVAILABLE_COMMAND = "list, todo, deadline, event, mark, unmark, delete, bye";

    public static final String MESSAGE_INVALID_TASK_FORMAT = "Invalid task format!";
    public static final String MESSAGE_INVALID_TASK_ARG_FORMAT = "Invalid task argument format!";
    public static final String MESSAGE_INVALID_TASK_NUMBER = "Invalid task number (task not found)!";
    public static final String MESSAGE_INVALID_TASK_ENCODED_FORMAT = "Invalid encoded task content!";
    public static final String MESSAGE_INVALID_DEADLINE_ENCODED = "duke.task.Deadline task missing 'by' information!";
    public static final String MESSAGE_INVALID_EVENT_ENCODED = "duke.task.Event task missing 'from' or 'to' information!";
    public static final String MESSAGE_INVALID_DATETIME_FORMAT = "Expected format is {yyyy-MM-dd} or {yyyy/MM/dd} with optional time in 24-hour format {HH:mm} or {HHmm}";
    public static final String MESSAGE_INVALID_DATETIME_RANGE = "'%s' is after '%s'!";

    public static final String MESSAGE_CREATE_FILE_ERROR = "Error while creating folder or file!";
    public static final String MESSAGE_READ_FILE_ERROR = "Error while reading from file!";
    public static final String MESSAGE_WRITE_FILE_ERROR = "Error while writing to file!";
    public static final String MESSAGE_FILE_PATH_ERROR = "duke.storage.Storage file should end with '.txt'";
}
