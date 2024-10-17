package checkbot.Utils;

public class StringHelper {
    public static String outputLine = "--------------------------------------";
    public static String hello = outputLine + System.lineSeparator() +
            "Hello! I'm checkbot." + System.lineSeparator() +
            "What can I do for you? :)" + System.lineSeparator() +
            outputLine;
    public static String exit = outputLine + System.lineSeparator() +
            "Bye. Hope to see you again soon!" + System.lineSeparator() +
            outputLine;
    public static String commandNotFound = outputLine + System.lineSeparator() +
            "Sorry, I didn't get that. :(" + System.lineSeparator() +
            "Try starting a task with \"todo\", \"deadline\", or \"event\"." + System.lineSeparator() +
            "To delete a task, type \"delete\", followed by the task number." + System.lineSeparator() +
            "To mark a task as done or undone, type \"mark\" or \"unmark\", followed by the task number." + System.lineSeparator() +
            "To see the full task list, type \"list\"." + System.lineSeparator() +
            "To end this session, type \"bye\"." + System.lineSeparator() +
            outputLine;
    public static String emptyDescription = outputLine + System.lineSeparator() +
            "Task cannot be empty!" + System.lineSeparator() +
            "To add a Todo task: Todo <task>" + System.lineSeparator() +
            "To add a Deadline task: Deadline <task> /by <datetime>" + System.lineSeparator() +
            "To add an Event task: Event <task> /from <datetime> /to <datetime>" + System.lineSeparator() +
            outputLine;
    public static String emptyTime = outputLine + System.lineSeparator() +
            "We need to know the time! Try following the format below:" + System.lineSeparator() +
            "To add a Deadline task: Deadline <task> /by <datetime>" + System.lineSeparator() +
            "To add an Event task: Event <task> /from <datetime> /to <datetime>" + System.lineSeparator() +
            outputLine;
    public static String deadlineError = outputLine + System.lineSeparator() +
            "Missing keyword \"/by\"!" + System.lineSeparator() +
            "To add a Deadline task: Deadline <task> /by <datetime>" + System.lineSeparator() +
            outputLine;
    public static String eventError = outputLine + System.lineSeparator() +
            "Missing keyword \"/from\" and/or \"/to\"!" + System.lineSeparator() +
            "To add an Event task: Event <task> /from <datetime> /to <datetime>" + System.lineSeparator() +
            outputLine;
    public static String emptyNumber = outputLine + System.lineSeparator() +
            "We need to know which task you are referring to! Please include the task number in numerical digits." + System.lineSeparator() +
            outputLine;
    public static String nonIntegerNumber = outputLine + System.lineSeparator() +
            "The task number needs to be in numerical digits!" + System.lineSeparator() +
            outputLine;
    public static String invalidTaskNumber = outputLine + System.lineSeparator() +
            "You've keyed in an invalid task number." + System.lineSeparator() +
            outputLine;
}
