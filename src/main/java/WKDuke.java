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

    //Solution below inspired by https://stackoverflow.com/questions/69576641
    public static void printTaskList() {
        StringBuilder messageBuilder = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            messageBuilder.append(NEW_INDENT_LINE).append(i + 1).append(".").append(taskList.get(i));
        }
        echo(messageBuilder.toString());
    }

    public static boolean invalidTaskNumber(String string) {
        try {
            int taskNumber = Integer.parseInt(string);
            return taskNumber <= 0;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private static int getTaskNumber(String[] inputWords) throws InvalidTaskOperationException {
        String taskOperation = inputWords[0];
        // Check if input contain a task number
        if (inputWords.length < 2) {
            throw new InvalidTaskOperationException("Action required a task number.", taskOperation);
        }
        // Check if task number is not a valid integer
        String taskNumber = inputWords[1];
        if (invalidTaskNumber(taskNumber)) {
            throw new InvalidTaskOperationException(String.format("Task number '%s' is invalid.", taskNumber), taskOperation);
        }
        // Check if task number exist in taskList
        if (Integer.parseInt(taskNumber) > taskList.size()) {
            throw new InvalidTaskOperationException(String.format("Task number '%s' not found.", taskNumber), taskOperation);
        }
        return Integer.parseInt(taskNumber);
    }

    public static void markTaskAsDone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone();
        echo(String.format("Nice! I've marked this task as done:%s  %s", NEW_INDENT_LINE, task));
    }

    public static void markTaskAsUndone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsUndone();
        echo(String.format("OK, I've marked this task as not done yet:%s  %s", NEW_INDENT_LINE, task));
    }

    public static void updateTask(String action, String[] inputWords) {
        try {
            int taskNumber = getTaskNumber(inputWords);
            switch (action) {
                case MARK_TASK_DONE_KEYWORD -> markTaskAsDone(taskNumber);
                case MARK_TASK_UNDONE_KEYWORD -> markTaskAsUndone(taskNumber);
                default -> throw new InvalidTaskOperationException("Unknown task operation.", action);
            }
        } catch (InvalidTaskOperationException e) {
            echo(String.format("Action: %s%sError: %s", e.getTaskOperation(), NEW_INDENT_LINE, e.getMessage()));
        }
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

    public static void addTask(String taskType, String taskDetail) {
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
                 ___       __   ___  __    ________  ___  ___  ___  __    _______
                \t |\\  \\     |\\  \\|\\  \\|\\  \\ |\\   ___ \\|\\  \\|\\  \\|\\  \\|\\  \\ |\\  ___ \\    \s
                \t \\ \\  \\    \\ \\  \\ \\  \\/  /|\\ \\  \\_|\\ \\ \\  \\\\\\  \\ \\  \\/  /|\\ \\   __/|   \s
                \t  \\ \\  \\  __\\ \\  \\ \\   ___  \\ \\  \\ \\\\ \\ \\  \\\\\\  \\ \\   ___  \\ \\  \\_|/__ \s
                \t   \\ \\  \\|\\__\\_\\  \\ \\  \\\\ \\  \\ \\  \\_\\\\ \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\\s
                \t    \\ \\____________\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\
                \t     \\|____________|\\|__| \\|__|\\|_______|\\|_______|\\|__| \\|__|\\|_______|
                """;
        echo(String.format("%s%sHello! I'm WKDuke%sWhat can I do for you?", logo, NEW_INDENT_LINE, NEW_INDENT_LINE));

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
                case MARK_TASK_UNDONE_KEYWORD:
                    updateTask(action, inputWords);
                    break;
                case ADD_TODO_TASK_KEYWORD:
                case ADD_DEADLINE_TASK_KEYWORD:
                case ADD_EVENT_TASK_KEYWORD:
                    addTask(action, inputWords[1]);
                    break;
                default:
                    echo(String.format("Action: userInput%sError: Unknown command for '%s'.", NEW_INDENT_LINE, input));
                    break;
            }
        }

        echo("Bye. Hope to see you again soon!");
    }
}
