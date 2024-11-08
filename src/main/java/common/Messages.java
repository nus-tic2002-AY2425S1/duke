package common;

/**
 * A utility class that contains various message constants used throughout the application.
 * 
 * <p>
 * The Messages class centralizes all the strings used for user feedback, error messages, and prompts to ensure consistency and ease of maintenance. 
 * This design allows for easy modification of messages without changing the logic of the application.
 * </p>
 */
public class Messages {
    // Ui
    public static final String MESSAGE_WELCOME = "Greetings! It's Javaro at your service. What can I do for you?";
    public static final String MESSAGE_GOODBYE = "Good bye! Hope to see you again soon";

    // Storage: Folder
    public static final String ERROR = "Error: ";
    public static final String ERROR_CREATE_FOLDER_PRE = ERROR + "Failed to create the data directory";
    public static final String ERROR_CREATE_FOLDER_POST = "Please check your folder path and permissions.";
    
    // Storage: File
    public static final String ERROR_CREATE_FILE_PRE = ERROR + "Failed to create the task file";
    public static final String ERROR_CREATE_FILE_POST = "Please check your file path and permissions.";
    public static final String CHECK_PERMISSIONS = "Please check your permissions.";
    public static final String ERROR_READ_FILE = "Error: Unable to read the task file." + Constants.SPACE + CHECK_PERMISSIONS;
    // public static final String ERROR_WRITE_FILE = "Error: Unable to write to the task file." + SPACE + CHECK_PERMISSIONS;
    public static final String ERROR_WRITE_FILE = ERROR + "Failed to write to task file";
    public static final String FAIL_CREATE_FILE = "Error: Failed to create task file due to ";
    public static final String ERROR_IO_CREATE_FILE = "An I/O error occurred.";
    public static final String ERROR_SECURITY_CREATE_FILE = "security restrictions.";
    public static final String MESSAGE_INSUFFICIENT_PERMISSIONS_PRE = "Please ensure that you have sufficient permissions to ";
    public static final String MESSAGE_INSUFFICIENT_PERMISSIONS = MESSAGE_INSUFFICIENT_PERMISSIONS_PRE + "create the task file.";
    
    public static final String MESSAGE_EMPTY_LINE = "Empty line found";
    public static final String MESSAGE_INVALID_TASKS_DATA = "Please ensure that all lines in ./data/tasks.txt contain valid data";
    public static final String MESSAGE_TASK_MISSING_COMPONENTS = "Task data has missing components";

    // TaskList
    public static final String TASKLIST_EMPTY = "Good job! You're all caught up!";

    // Parser
    public static final String ERROR_INVALID_COMMAND = "Error: Invalid command.";
    public static final String VALID_COMMANDS = "Please start with 'list', 'mark', 'unmark', 'todo', 'deadline', 'event'. If you are done, please enter 'bye' to exit the chat";
    public static final String ERROR_INVALID_COMMAND_FORMAT = "Error: Invalid command format for command ";       // to append actual command part entered by user at end of string

    // Todo, Deadline, Event
    public static final String MESSAGE_EMPTY_DESCRIPTION_PRE = "The description of a task cannot be empty.";
    
    public static final String MESSAGE_EMPTY_DESCRIPTION_MID = "Please add a description for the task. Example: \"";
    public static final String MESSAGE_EMPTY_DESCRIPTION_POST = " borrow book\"";
    
    public static final String ERROR_INVALID_TASK_NUMBER = "Error: Invalid task number";
    public static final String MESSAGE_NONEXISTENT_TASK_PRE = "Task number";
    public static final String MESSAGE_NONEXISTENT_TASK_POST = "cannot be found.";
    // public static final String MESSAGE_NONEXISTENT_TASK_POST = "Please enter a valid task number from 1 to";
    public static final String MESSAGE_NOW_YOU_HAVE = "Now you have ";
    public static final String MESSAGE_IN_THE_LIST = " in the list.";

    // TaskLisDecoder
    public static final String ERROR_INVALID_TASK_FORMAT = "Error: Invalid task format for ";

    public static final String ERROR_TASK_NONEXISTENT = "Error: Task does not exist";
    // public static final String MESSAGE_NONEXISTENT_TASK_PRE = "Task number";
    // public static final String MESSAGE_NONEXISTENT_TASK_POST = "not found.";
    public static final String MESSAGE_ENTER_VALID_TASK_NUMBER = "Please enter a valid task number from 1 to";

    public static final String ERROR_INVALID_DATETIME_FORMAT = ERROR + "Invalid date/time format";
    public static final String ERROR_END_BEFORE_START = ERROR + "Start date/time must be before end date/time";

    public static final String ERROR_EMPTY_INPUT = ERROR + "Input cannot be empty."; 

    // MarkCommand and UnmarkCommand
    public static final String MESSAGE_EMPTY_TASKLIST = "The task list is empty. Please add a task first.";
}
