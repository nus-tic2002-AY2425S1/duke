package mochi.common;
import mochi.Mochi;

public enum DialogMessages {
   GREETINGS("Hello! I'm " + Mochi._name + System.lineSeparator()
     + "What can I do for you?"),
   LOAD_TASK_LINE_ERROR("Please check the following line number as it is in the wrong save format. Line:"),
   LOAD_TASK_ERROR("Unable to load database file, it could be corrupted"),
   SAVE_TASK_FOUND("Successfully loaded from save! Please use `list` command to check on save!"),
   SAVE_TASK_NOT_FOUND("No save file (MochiSave.txt) found in current directory. Please start to feed me!"),
   LIST_TASK("Here are the tasks in your list:"),
   MARK_TASK("Nice! I've marked this task as done:"),
   DELETE_TASK("Noted. I've removed this task:"),
   UNMARK_TASK("OK, I've marked this task as not done yet:"),
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
