package mochi.common;

import mochi.Mochi;

/**
 * DialogMessages is an enumeration of common messages used for user interaction.
 * Each enum constant represents a message string, making it easy to manage and access
 * consistent dialog messages throughout the application.
 */
public enum DialogMessages {
    GREETINGS("Hello! I'm " + Mochi.NAME + System.lineSeparator()
            + "What can I do for you?"),
    INVALID_MULTI_PATTERN("The given mass pattern is wrong, please use [1,3,5] if you wish to select 3 task with task id 1,3,5"),
    LOAD_TASK_LINE_ERROR("Please check the following line number as it is in the wrong save format. Line:"),
    LOAD_TASK_ERROR("Unable to load database file, it could be corrupted"),
    SAVE_TASK_FOUND("Successfully loaded from save! Please use `list` command to check on save!"),
    SAVE_TASK_NOT_FOUND("No save file (MochiSaves.txt) found in current directory. Please start to feed me!"),
    LIST_TASK("Here are the tasks in your list:"),
    LIST_TASK_FILTERED("Here are the filtered tasks in your list:"),
    LIST_TASK_FILTERED_DATE("Here are the filtered tasks by date:"),
    LIST_TASK_FILTERED_NAME("Here are the filtered tasks by given keyword:"),
    LIST_TASK_EMPTY("There are no task in given type or date filter"),
    MARK_TASK("Nice! I've marked this task as done:"),
    MARK_TASK_MASS("Nice! I've marked the following task ids :"),
    DELETE_TASK("Noted. I've removed this task:"),
    DELETE_TASK_MASS("Nice! I've removed the following task ids :"),
    INVALID_TASK("parameter is in wrong date format, this will not be added!"),
    INVALID_TASK_FROM_AFTER_TO("parameter /from must be before /to, this will not be added!"),
    UNMARK_TASK("OK, I've marked this task as not done yet:"),
    UNMARK_TASK_MASS("Nice! I've unmarked the following task ids :"),
    BYE("Bye. Hope to see you again soon!"),
    TASK_ADDED("Got it. I've added this task:"),
    TASK_LOADED("Loaded:"),
    COMMANDS_LIST("""
             1. todo "Read Book"                                               - Adds a new todo task.
             2. event "Read Book" /from 01/10/2024 0800 /to 01/10/2024 0800    - Adds a new "Read Book" event between a given date time in "d/M/yyyy HHmm" format.
             3. deadline "Return Book" /by 01/10/2024 0800                     - Adds a new "Return Book" deadline by a given date time in "d/M/yyyy HHmm" format.
             4. list                                                           - Displays all current tasks, events, and deadlines.
             5. list event                                                     - Displays filtered tasks by task type of "event".
             6. list event /before 01/10/2024 0800                             - Displays filtered tasks by task type "event" and before a given date time in "d/M/yyyy HHmm" format.
             7. list deadline /after 01/10/2024 0800                           - Displays filtered tasks by task type "deadline" and after a given date time in "d/M/yyyy HHmm" format.
             8. view 01/10/24                                                  - Display all tasks that is happening on the given day in "d/M/yyyy" format.
             9. find "Book"                                                    - Display all tasks with description that contains "Book" keyword.
            10. mark 1                                                         - Mark a task with id 1.
            11. mark [1,2,3]                                                   - Mark tasks with a group of ids(1,2,3).
            12. unmark 1                                                       - Unmark a task with task ID of 1.
            13. unmark [1,2,3]                                                 - Unmark tasks with a group of task IDs of (1,2,3).
            13. delete 1                                                       - Deletes an existing task with task ID of 1.
            14. delete [1,2,3]                                                 - Deletes a group of existing tasks with task IDs of (1,2,3).
            15. bye                                                            - Exits the program.
            """),
    INPUT_UNKNOWN("Looks like you have input the wrong input, here are the available commands"
            + System.lineSeparator() + COMMANDS_LIST.getValue());
    private final String message;

    DialogMessages(String message) {
        this.message = message;
    }

    public String getValue() {
        return message;
    }
}
