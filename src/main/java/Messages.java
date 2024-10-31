public class Messages {
    
    public static final String SPACE = " ";

    // Ui
    public static final String MESSAGE_WELCOME = "Greetings! It's Javaro at your service. What can I do for you?";
    public static final String MESSAGE_GOODBYE = "Good bye! Hope to see you again soon";

    // Storage: Folder
    public static final String ERROR_CREATE_FOLDER = "Failed to create the data directory";
    
    // Storage: File
    public static final String ERROR_CREATE_FILE = "Error: Unable to create the task file. Please check your file path and permissions.";
    public static final String CHECK_PERMISSIONS = "Please check your permissions.";
    public static final String ERROR_READ_FILE = "Error: Unable to read the task file." + SPACE + CHECK_PERMISSIONS;
    public static final String ERROR_WRITE_FILE = "Error: Unable to write to the task file." + SPACE + CHECK_PERMISSIONS;
    public static final String FAIL_CREATE_FILE = "Failed to create task file due to ";
    public static final String ERROR_IO_CREATE_FILE = FAIL_CREATE_FILE + "I/O error.";
    public static final String ERROR_SECURITY_CREATE_FILE = FAIL_CREATE_FILE + "security restrictions. Please ensure that you have sufficient permissions to create the task file";
    
    // TaskList
    public static final String TASKLIST_EMPTY = "Good job! You're all caught up!";

    // Parser
    public static final String INVALID_COMMAND = "Invalid command.";
    public static final String VALID_COMMANDS = "Please start with 'list', 'mark', 'unmark', 'todo', 'deadline', 'event'. If you are done, please enter 'bye' to exit the chat";
    public static final String INVALID_COMMAND_FORMAT = "Invalid command format for command ";

    // Todo, Deadline, Event
    public static final String MESSAGE_EMPTY_DESCRIPTION_PRE = "The description of a task cannot be empty.";
    public static final String NEW_LINE = "\n";
    public static final String MESSAGE_EMPTY_DESCRIPTION_MID = "Please add a description for the task. Example: \"";
    public static final String MESSAGE_EMPTY_DESCRIPTION_POST = " borrow book\"";


    // public static final String MESSAGE_INIT_FAILED = "Failed to initialise address book application. Exiting...";
    // public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    // public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    // public static final String MESSAGE_PERSON_NOT_IN_ADDRESSBOOK = "Person could not be found in address book";
    // public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    // public static final String MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE = "Launch command format: "
    //         + "java seedu.addressbook.Main [STORAGE_FILE_PATH]";
    // public static final String MESSAGE_USING_STORAGE_FILE = "Using storage file : %1$s";
}
