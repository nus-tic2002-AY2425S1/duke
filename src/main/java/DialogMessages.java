public class DialogMessages {
  public static final String GREETINGS = " Hello! I'm " + Mochi._name + System.lineSeparator()
    + " What can I do for you?";
  public static final String LIST_TASK = "Here are the task in your list:";
  public static final String MARK_TASK = "Nice! I've marked this task as done:";
  public static final String UNMARK_TASK = "OK, I've marked this task as not done yet:";
  public static final String BYE = "Bye. Hope to see you again soon!";

  public static final String TASK_ADDED = " Got it. I've added this task:";

  public static final String COMMANDS_LIST = """
    1. todo X                               - Adds a new to-do task. Example: todo Buy groceries
    2. event X /from Mon 5pm /to Mon 7pm    - Adds a new event task. Example: event Attend meeting between a given date
    3. deadline X /by Mon 5pm               - Adds a new deadline task. Example: Deadline Submit report by a given date
    4. list                                 - Displays all current tasks, events, and deadlines.
    5. bye                                  - Exits the program.
    """;
  public static final String INPUT_UNKNOWN = "Looks like you have input the wrong input, here are the available commands"
    + System.lineSeparator() + COMMANDS_LIST;
}
