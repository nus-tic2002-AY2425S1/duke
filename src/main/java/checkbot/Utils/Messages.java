package checkbot.Utils;

public class Messages {
    public static String divider = "--------------------------------------";
    public static String hello = divider + System.lineSeparator() +
            "Hello! I'm checkbot." + System.lineSeparator() +
            "What can I do for you? :)" + System.lineSeparator() +
            divider;
    public static String exit = divider + System.lineSeparator() +
            "Bye. Hope to see you again soon!" + System.lineSeparator() +
            divider;
    public static String commandNotFound = divider + System.lineSeparator() +
            "Sorry, I didn't get that. :(" + System.lineSeparator() +
            "Try starting a task with \"todo\", \"deadline\", or \"event\"." + System.lineSeparator() +
            "To delete a task, type \"delete\", followed by the task number." + System.lineSeparator() +
            "To mark a task as done or undone, type \"mark\" or \"unmark\", followed by the task number." + System.lineSeparator() +
            "To see the full task list, type \"list\"." + System.lineSeparator() +
            "To end this session, type \"bye\"." + System.lineSeparator() +
            divider;
    public static String emptyDescription = divider + System.lineSeparator() +
            "Task cannot be empty!" + System.lineSeparator() +
            "To add a Todo task: Todo <task>" + System.lineSeparator() +
            "To add a Deadline task: Deadline <task> /by <datetime>" + System.lineSeparator() +
            "To add an Event task: Event <task> /from <datetime> /to <datetime>" + System.lineSeparator() +
            divider;
    public static String emptyTime = divider + System.lineSeparator() +
            "We need to know the time! Try following the format below:" + System.lineSeparator() +
            "To add a Deadline task: Deadline <task> /by <datetime>" + System.lineSeparator() +
            "To add an Event task: Event <task> /from <datetime> /to <datetime>" + System.lineSeparator() +
            divider;
    public static String deadlineError = divider + System.lineSeparator() +
            "Missing keyword \"/by\"!" + System.lineSeparator() +
            "To add a Deadline task: Deadline <task> /by <datetime>" + System.lineSeparator() +
            divider;
    public static String eventError = divider + System.lineSeparator() +
            "Missing keyword \"/from\" and/or \"/to\"!" + System.lineSeparator() +
            "To add an Event task: Event <task> /from <datetime> /to <datetime>" + System.lineSeparator() +
            divider;
    public static String emptyNumber = divider + System.lineSeparator() +
            "We need to know which task you are referring to! Please include the task number in numerical digits." + System.lineSeparator() +
            divider;
    public static String nonIntegerNumber = divider + System.lineSeparator() +
            "The task number needs to be in numerical digits!" + System.lineSeparator() +
            divider;
    public static String invalidTaskNumber = divider + System.lineSeparator() +
            "You've keyed in an invalid task number." + System.lineSeparator() +
            divider;
}
