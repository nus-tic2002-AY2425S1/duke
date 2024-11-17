package josbot.common;

public class Messages {
    public static final String START_GREETING = "I'm JosBot\nWhat can I do for you?";
    public static final String END_GREETING = "Bye. Hope to see you again soon!";


    public static final String ERROR_INVALID_COMMAND = "Invalid Command : No such command found!";
    public static final String ERROR_INVALID_DATETIME_FORMAT = "Invalid date & time format! Please use date format (dd/MM/yyyy) and if you want to add time,\nuse the time format (24-hour format, eg. 1800 is 6PM)";
    public static final String ERROR_INVALID_TAG = "Invalid tag detected! Please use the correct tag format\nfor tag use ('tag <task_list_number> <tag_description>')\n for untag use ('untag <task_list_number>')";
    public static final String ERROR_MISSING_DESCRIPTION = "Missing description detected! Please specify the description of the task";
    public static final String ERROR_MISSING_MARK_NUMBER = "Missing number detected! Please specify the task number";
    public static final String ERROR_FILE_CORRUPTED = "File is corrupted/File is not found\n Creating new file..";
    public static final String ERROR_LOADING = "Error: Failed to load file";
    public static final String ERROR_FILE_NOT_FOUND = "Error: File are not found!\nCreating new file..";
    public static final String ERROR_INVALID_DATETIME = "Invalid date & time detected! Please make sure that the date or time you've listed is correct";
    public static final String ERROR_INDEX_OUT_OF_BOUND = "The number you've selected is not on the list! Please choose the correct number.";
    public static final String ERROR_NUMBER_FORMAT = "The command format that you've selected is wrong! Please use number/Integer (e.g <Command> <number/integer>)";
    public static final String ERROR_DATEITME = "Invalid date & time format! Please use date format (dd/MM/yyyy) and if you want to add time,\nuse the time format (24-hour format, eg. 1800)";
    public static final String ERROR_INVALID_LIST = "Invalid list format! Please use the date format (dd/MM/yyyy)";

    public static final String LIST_MESSAGES = "Here are the tasks in your list:";
    public static final String REMINDER_MESSAGES = "Reminder - Here are the lists of task with deadline \nsorted from the oldest at the top that have not been marked as done:\n";
    public static final String ADD_MESSAGES = "Got it. I've added this task:";
    public static final String TASK_MESSAGES_START = "Now you have ";
    public static final String TASK_MESSAGES_END = " task in the list.";
    public static final String MARK_MESSAGES = "Nice! I've marked this task as done:";
    public static final String UNMARK_MESSAGES = "OK, I've marked this task as not done yet:";
    public static final String TAG_MESSAGES = "OK, I've tagged this task as follow:";
    public static final String UNTAG_MESSAGES = "OK, I've removed the tag from this task:";
    public static final String DELETE_MESSAGES = "Noted. I've removed this task:";
    public static final String NO_TASK_FOUND_MESSAGES = "No task result found!";
    public static final String TASK_LIST_MESSAGES_START = "\nThere are a total of ";
    public static final String TASK_LIST_MESSAGES_END = " tasks shown in the above list.";
    public static final String HELP_MESSAGES = "List of Command:\n"
            + "1. todo (format: todo <description>)\n"
            + "2. deadline (format: deadline <description> /by <date / date & time> (format: date <dd/mm/yyyy> , date & time <dd/mm/yyyy hhmm>))\n"
            + "3. event (format: event <description> /from <date / date & time> /to <date / date & time> (format: date <dd/mm/yyyy> , date & time <dd/mm/yyyy hhmm>))\n"
            + "4. tag/untag (format: tag/untag <task list number> <description>)\n"
            + "5. mark/unmark (format: mark/unmark <description>)\n"
            + "6. delete (format: delete <task list number>)\n"
            + "7. list (format: list), for more specific list filtered by date (format: list <date> (format: date <dd/mm/yyyy>))\n"
            + "8. reminder (format: reminder)\n"
            + "9. find (format: find <description>)\n"
            + "10. bye (format: bye)\n"
            + "\nUse the command 'bye' to exit the program.";
}
