package mochi.common;
import mochi.Mochi;
/**
 * DialogMessages is an enumeration of common messages used for user interaction.
 * Each enum constant represents a message string, making it easy to manage and access
 * consistent dialog messages throughout the application.
 */
public enum DialogMessages {
   GREETINGS("Hello! I'm " + Mochi._name + System.lineSeparator()
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
            1. todo X                               - Adds a new to-do task. Example: todo Buy groceries
            2. event X /from Mon 5pm /to Mon 7pm    - Adds a new event task. Example: event Attend meeting between a given date
            3. deadline X /by Mon 5pm               - Adds a new deadline task. Example: Deadline Submit report by a given date
            4. list                                 - Displays all current tasks, events, and deadlines.
            5. delete X                             - Deletes a existing task with task ID. Example : delete 1
            6. bye                                  - Exits the program.
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
