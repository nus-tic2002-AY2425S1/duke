public class Messages {
    
    public static final String SPACE = " ";

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
    public static final String ERROR_READ_FILE = "Error: Unable to read the task file." + SPACE + CHECK_PERMISSIONS;
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
    public static final String NEW_LINE = "\n";
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


    // public static final String MESSAGE_INIT_FAILED = "Failed to initialise address book application. Exiting...";
    // public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    // public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    // public static final String MESSAGE_PERSON_NOT_IN_ADDRESSBOOK = "Person could not be found in address book";
    // public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    // public static final String MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE = "Launch command format: "
    //         + "java seedu.addressbook.Main [STORAGE_FILE_PATH]";
    // public static final String MESSAGE_USING_STORAGE_FILE = "Using storage file : %1$s";
}
