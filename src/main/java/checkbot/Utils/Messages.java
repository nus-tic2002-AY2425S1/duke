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

    public static String help = divider + System.lineSeparator() +
            "To add a task: Start with \"todo\", \"deadline\", or \"event\"." + System.lineSeparator() +
            "  todo <description>" + System.lineSeparator() +
            "  deadline <description> /by <DD/MM/YYYY HHMM(24H)>" + System.lineSeparator() +
            "  event <description> /from <DD/MM/YYYY HHMM(24H)> /to <DD/MM/YYYY HHMM(24H)>" + System.lineSeparator() +
            "To see the full task list: type \"list\"." + System.lineSeparator() +
            "To delete a task: Start with \"delete\", followed by the task number." + System.lineSeparator() +
            "To mark a task as done or undone: Start with \"mark\" or \"unmark\", followed by the task number." + System.lineSeparator() +
            "To rank a task with priority: Start with \"rank\", followed by the task number, followed by priority." + System.lineSeparator() +
            "  rank <task number> high" + System.lineSeparator() +
            "  rank <task number> medium" + System.lineSeparator() +
            "  rank <task number> low" + System.lineSeparator() +
            "  rank <task number> none" + System.lineSeparator() +
            "To end this session, type \"bye\"." + System.lineSeparator() +
            divider;

    public static String commandNotFound = divider + System.lineSeparator() +
            "Sorry, I didn't get that. :(" + System.lineSeparator() +
            "Type \"help\" for command manual." + System.lineSeparator() +
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

    public static String invalidDateTime = divider + System.lineSeparator() +
            "You've entered an invalid date time format. Please follow the following:" + System.lineSeparator() +
            "DD/MM/YYYY HHMM(24H)" + System.lineSeparator() +
            divider;

    public static String invalidPriority = divider + System.lineSeparator() +
            "Please indicate priority:" + System.lineSeparator() +
            "  HIGH" + System.lineSeparator() +
            "  MEDIUM" + System.lineSeparator() +
            "  LOW" + System.lineSeparator() +
            "If you do not wish to set priority, please input \"none\"." + System.lineSeparator() +
            divider;
}
