import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WKDuke {
    private static final String BORDER_LINE = "\t____________________________________________________________";
    private static final String INDENT = "\t ";
    private static final String NEW_INDENT_LINE = System.lineSeparator() + "\t ";

    private static final String EXIT_KEYWORD = "bye";
    private static final String LIST_TASK_KEYWORD = "list";
    private static final String MARK_TASK_DONE_KEYWORD = "mark";
    private static final String MARK_TASK_UNDONE_KEYWORD = "unmark";
    private static final String ADD_TODO_TASK_KEYWORD = "todo";
    private static final String ADD_DEADLINE_TASK_KEYWORD = "deadline";
    private static final String ADD_EVENT_TASK_KEYWORD = "event";

    private static final List<Task> taskList = new ArrayList<>();

    public static void echo(String message) {
        System.out.println(BORDER_LINE);
        System.out.println(INDENT + message);
        System.out.println(BORDER_LINE + System.lineSeparator());
    }

    public static void printTaskList() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            message = message.concat(NEW_INDENT_LINE + Integer.toString(i + 1) + "." + taskList.get(i));
        }
        echo(message);
    }

    public static boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkUpdateTaskInput(String[] inputWords) {
        String action = inputWords[0];
        // Check if input contain a task number
        if (inputWords.length < 2) {
            echo(String.format("Action: %s%sError: Action required a task number.", action, NEW_INDENT_LINE));
            return false;
        }
        String taskNumber = inputWords[1];
        // Check if task number is a valid integer
        if (!isInteger(taskNumber) || Integer.parseInt(taskNumber) <= 0) {
            echo(String.format("Action: %s%sError: Task number '%s' is invalid.", action, NEW_INDENT_LINE, taskNumber));
            return false;
        }
        // Check if task number exist in taskList
        if (Integer.parseInt(taskNumber) > taskList.size()) {
            echo(String.format("Action: %s%sError: Task number '%s' not found.", action, NEW_INDENT_LINE, taskNumber));
            return false;
        }
        return true;
    }

    public static void markTaskAsDone(String taskNumber) {
        Task task = taskList.get(Integer.parseInt(taskNumber) - 1);
        task.markAsDone();
        String message = "Nice! I've marked this task as done:" + NEW_INDENT_LINE + "  " + task;
        echo(message);
    }

    public static void markTaskAsUndone(String taskNumber) {
        Task task = taskList.get(Integer.parseInt(taskNumber) - 1);
        task.markAsUndone();
        String message = "OK, I've marked this task as not done yet:" + NEW_INDENT_LINE + "  " + task;
        echo(message);
    }

    private static Task parseDeadlineTask(String taskDetail) throws InvalidTaskFormatException {
        String[] taskDetailParts = taskDetail.split("/by");
        if (taskDetailParts.length != 2) {
            throw new InvalidTaskFormatException("Deadline task requires '/by' information.");
        }
        return new Deadline(taskDetailParts[0].trim(), taskDetailParts[1].trim());
    }

    private static Task parseEventTask(String taskDetail) throws InvalidTaskFormatException {
        String[] parts = taskDetail.split("/from|/to");
        if (parts.length != 3) {
            throw new InvalidTaskFormatException("Event task requires '/from' and '/to' information.");
        }
        return new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    public static void addTask(String taskDetail, String taskType) {
        try {
            Task newTask = switch (taskType) {
                case ADD_TODO_TASK_KEYWORD -> new ToDo(taskDetail);
                case ADD_DEADLINE_TASK_KEYWORD -> parseDeadlineTask(taskDetail);
                case ADD_EVENT_TASK_KEYWORD -> parseEventTask(taskDetail);
                default -> throw new InvalidTaskFormatException("Unknown task type.");
            };
            taskList.add(newTask);
            echo(String.format("Got it. I've added this task:%s  %s%sNow you have %s tasks in the list.", NEW_INDENT_LINE, newTask, NEW_INDENT_LINE, taskList.size()));
        } catch (InvalidTaskFormatException e) {
            echo(String.format("Action: addTask%sError: %s", NEW_INDENT_LINE, e.getMessage()));
        }
    }

    public static void main(String[] args) {
        String logo = """
                \t  ___       __   ___  __    ________  ___  ___  ___  __    _______     \s
                \t |\\  \\     |\\  \\|\\  \\|\\  \\ |\\   ___ \\|\\  \\|\\  \\|\\  \\|\\  \\ |\\  ___ \\    \s
                \t \\ \\  \\    \\ \\  \\ \\  \\/  /|\\ \\  \\_|\\ \\ \\  \\\\\\  \\ \\  \\/  /|\\ \\   __/|   \s
                \t  \\ \\  \\  __\\ \\  \\ \\   ___  \\ \\  \\ \\\\ \\ \\  \\\\\\  \\ \\   ___  \\ \\  \\_|/__ \s
                \t   \\ \\  \\|\\__\\_\\  \\ \\  \\\\ \\  \\ \\  \\_\\\\ \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\\s
                \t    \\ \\____________\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\
                \t     \\|____________|\\|__| \\|__|\\|_______|\\|_______|\\|__| \\|__|\\|_______|
                """;
        echo(logo + NEW_INDENT_LINE + "Hello! I'm WKDuke" + NEW_INDENT_LINE + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String action = "";

        while (!action.equals(EXIT_KEYWORD)) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            String[] inputWords = input.split(" ", 2);
            action = inputWords[0];
            switch (action) {
                case EXIT_KEYWORD:
                    break;
                case LIST_TASK_KEYWORD:
                    printTaskList();
                    break;
                case MARK_TASK_DONE_KEYWORD:
                    if (checkUpdateTaskInput(inputWords)) {
                        markTaskAsDone(inputWords[1]);
                    }
                    break;
                case MARK_TASK_UNDONE_KEYWORD:
                    if (checkUpdateTaskInput(inputWords)) {
                        markTaskAsUndone(inputWords[1]);
                    }
                    break;
                case ADD_TODO_TASK_KEYWORD:
                case ADD_DEADLINE_TASK_KEYWORD:
                case ADD_EVENT_TASK_KEYWORD:
                    addTask(inputWords[1], action);
                    break;
                default:
                    echo(String.format("Action: userInput%sError: Unknown command for '%s'.", NEW_INDENT_LINE, input));
                    break;
            }
        }

        echo("Bye. Hope to see you again soon!");
    }
}
