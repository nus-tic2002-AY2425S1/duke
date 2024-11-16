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

    public static final String MESSAGE_DUPLICATE_TASK = "The task already exists in your task list!";
    public static final String MESSAGE_DUPLICATE_TASK_IN_FILE = "A duplicate task was found in your task file!";
    public static final String MESSAGE_DUPLICATE_TASK_HELP = "Use the 'list' command to view all tasks and avoid duplicates.";
    public static final String MESSAGE_DUPLICATE_TASK_IN_FILE_HELP = "Please check your file to ensure no duplicate entries exist.";

    public static final String MESSAGE_CREATE_FILE_ERROR = "Error while creating folder or file!";
    public static final String MESSAGE_READ_FILE_ERROR = "Error while reading from file!";
    public static final String MESSAGE_WRITE_FILE_ERROR = "Error while writing to file!";
    public static final String MESSAGE_FILE_PATH_ERROR = "Storage file should end with '.txt'";

    public static final String MESSAGE_FLEXIBLE_INPUT_PROMPT = "\t Please enter your filepath: ";
    public static final String MESSAGE_FLEXIBLE_INPUT_RETRY_PROMPT = "\t Please enter your filepath again: ";
    public static final String MESSAGE_DEFAULT_DATA_SOURCE_POST = "\t WKDuke is loading the default data source: ./data/tasks.txt";
    public static final String MESSAGE_CUSTOM_DATA_SOURCE_POST = "\t WKDuke is loading the custom data source: %s";
    public static final String MESSAGE_FLEXIBLE_DATA_SOURCE = """
                  +----------------------------------------------------------+
                  |                                                          |
                  |                 WKDuke Pre-initialisation                |
                  |                                                          |
                  |   Please enter the custom file path to load your tasks   |
                  |           (relative or full path) or press 'Enter'       |
                  |     to use the default data source (./data/tasks.txt).   |
                  |                                                          |
                  +----------------------------------------------------------+
            """;


    public static final String MESSAGE_AVAILABLE_COMMAND = """
            Available commands:
              list       - Lists all tasks.
              list /on   - Lists tasks on a specific date.
              find       - Finds tasks by keyword(s).
              todo       - Adds a Todo task.
              deadline   - Adds a Deadline task.
              event      - Adds an Event task.
              delete     - Deletes task(s).
              mark       - Marks task(s) as done.
              unmark     - Unmarks task(s) as not done.
              sort       - Sorts tasks by type, priority, or datetime.
              update-priority - Updates the priority of a task.
              help       - Displays this help message.
            
              Tips: You can type the command name (e.g., 'todo', 'delete', etc.) to see detailed usage and examples for that command.
            """;
    public static final String MESSAGE_TASK_LIST_TIPS = """
            \nTips: [TaskType][TaskPriority][TaskStatus]
              [TaskType]: [T] for Todo, [D] for Deadline, [E] for Event
              [TaskPriority]: [H] for High, [M] for Medium, [L] for Low
              [TaskStatus]: [X] for done, [ ] for not done
            """;
}
