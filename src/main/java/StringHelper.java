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
            "To see the full task list, type \"list\"." + System.lineSeparator() +
            "To end this session, type \"bye\"." + System.lineSeparator() +
            outputLine;
    public static String emptyInput = outputLine + System.lineSeparator() +
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
}
