package checkbot.utils;

public class Messages {
    public final static String DIVIDER = "--------------------------------------";

    public final static String HELLO = DIVIDER + System.lineSeparator() +
            "Hello! I'm checkbot." + System.lineSeparator() +
            "What task are you checking off today? :)" + System.lineSeparator() +
            DIVIDER;

    public final static String EXIT = DIVIDER + System.lineSeparator() +
            "Bye! Remember to do your tasks!" + System.lineSeparator() +
            DIVIDER;

    public final static String HELP = DIVIDER + System.lineSeparator() +
            "To add a task: Start with \"todo\", \"deadline\", or \"event\"." + System.lineSeparator() +
            "  todo <description>" + System.lineSeparator() +
            "  todo <description> /between <DD/MM/YYYY HHMM(24H)> /and <DD/MM/YYYY HHMM(24H)>" + System.lineSeparator() +
            "  deadline <description> /by <DD/MM/YYYY HHMM(24H)>" + System.lineSeparator() +
            "  event <description> /from <DD/MM/YYYY HHMM(24H)> /to <DD/MM/YYYY HHMM(24H)>" + System.lineSeparator() +
            "If no time is indicated, the default time is 0000." + System.lineSeparator() +
            "To see the full task list: type \"list\"." + System.lineSeparator() +
            "To see undone tasks in order of priority level: type \"ranking\"." + System.lineSeparator() +
            "To delete a task: Start with \"delete\", followed by the task number." + System.lineSeparator() +
            "To mark a task as done or undone: Start with \"mark\" or \"unmark\", followed by the task number." +
            System.lineSeparator() +
            "To rank a task with priority: Start with \"rank\", followed by the task number, followed by priority." +
            System.lineSeparator() +
            "  rank <task number> high" + System.lineSeparator() +
            "  rank <task number> medium" + System.lineSeparator() +
            "  rank <task number> low" + System.lineSeparator() +
            "  rank <task number> none" + System.lineSeparator() +
            "To search for tasks: type \"find\", followed by keywords." + System.lineSeparator() +
            "To end this session: type \"bye\"." + System.lineSeparator() +
            DIVIDER;

    public final static String COMMAND_NOT_FOUND = DIVIDER + System.lineSeparator() +
            "Sorry, I didn't get that. :(" + System.lineSeparator() +
            "Type \"help\" for command manual." + System.lineSeparator() +
            DIVIDER;

    public final static String EMPTY_DESCRIPTION = DIVIDER + System.lineSeparator() +
            "Task cannot be empty!" + System.lineSeparator() +
            "To add a Todo task: Todo <task>" + System.lineSeparator() +
            "To add a period Todo task: Todo <task> /between <datetime> /and <datetime>" + System.lineSeparator() +
            "To add a Deadline task: Deadline <task> /by <datetime>" + System.lineSeparator() +
            "To add an Event task: Event <task> /from <datetime> /to <datetime>" + System.lineSeparator() +
            DIVIDER;

    public final static String EMPTY_TIME = DIVIDER + System.lineSeparator() +
            "We need to know the time! Try following the format below:" + System.lineSeparator() +
            "To add a period Todo task: " +
            "Todo <description> /between <DD/MM/YYYY HHMM(24H)> /and <DD/MM/YYYY HHMM(24H)>" + System.lineSeparator() +
            "To add a Deadline task: Deadline <task> /by <datetime>" + System.lineSeparator() +
            "To add an Event task: Event <task> /from <datetime> /to <datetime>" + System.lineSeparator() +
            DIVIDER;

    public final static String TODO_ERROR = DIVIDER + System.lineSeparator() +
            "Missing keyword \"/between\" and/or \"/and\"!" + System.lineSeparator() +
            "To add a period Todo task: Todo <task> /between <datetime> /and <datetime>" + System.lineSeparator() +
            DIVIDER;

    public final static String DEADLINE_ERROR = DIVIDER + System.lineSeparator() +
            "Missing keyword \"/by\"!" + System.lineSeparator() +
            "To add a Deadline task: Deadline <task> /by <datetime>" + System.lineSeparator() +
            DIVIDER;

    public final static String EVENT_ERROR = DIVIDER + System.lineSeparator() +
            "Missing keyword \"/from\" and/or \"/to\"!" + System.lineSeparator() +
            "To add an Event task: Event <task> /from <datetime> /to <datetime>" + System.lineSeparator() +
            DIVIDER;

    public final static String EMPTY_NUMBER = DIVIDER + System.lineSeparator() +
            "We need to know which task you are referring to! Please include the task number in numerical digits." +
            System.lineSeparator() +
            DIVIDER;

    public final static String EMPTY_SEARCH = DIVIDER + System.lineSeparator() +
            "Search keyword is empty!" + System.lineSeparator() +
            DIVIDER;

    public final static String NON_INTEGER_NUMBER = DIVIDER + System.lineSeparator() +
            "The task number needs to be in numerical digits!" + System.lineSeparator() +
            DIVIDER;

    public final static String INVALID_TASK_NUMBER = DIVIDER + System.lineSeparator() +
            "You've keyed in an invalid task number." + System.lineSeparator() +
            DIVIDER;

    public final static String INVALID_DATETIME = DIVIDER + System.lineSeparator() +
            "You've entered an invalid date time format. Please follow the following:" + System.lineSeparator() +
            "DD/MM/YYYY HHMM(24H)" + System.lineSeparator() +
            DIVIDER;

    public final static String INVALID_PRIORITY = DIVIDER + System.lineSeparator() +
            "Please indicate priority:" + System.lineSeparator() +
            "  HIGH" + System.lineSeparator() +
            "  MEDIUM" + System.lineSeparator() +
            "  LOW" + System.lineSeparator() +
            "If you do not wish to set priority, please input \"none\"." + System.lineSeparator() +
            DIVIDER;
}
