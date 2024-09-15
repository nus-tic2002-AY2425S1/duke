import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WKDuke {
    private static final String borderLine = "\t____________________________________________________________";
    private static final String stringIndent = "\t ";
    private static final String newIndentLine = System.lineSeparator() + "\t ";

    private static final String exitKeyword = "bye";
    private static final String listTaskKeyword = "list";
    private static final String markTaskDoneKeyword = "mark";
    private static final String markTaskUndoneKeyword = "unmark";
    private static final String addToDoTaskKeyword = "todo";
    private static final String addDeadlineTaskKeyword = "deadline";
    private static final String addEventTaskKeyword = "event";

    private static final List<Task> taskList = new ArrayList<>();

    public static void echo(String message) {
        System.out.println(borderLine);
        System.out.println(stringIndent + message);
        System.out.println(borderLine + System.lineSeparator());
    }

    public static void printTaskList() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            message = message.concat(newIndentLine + Integer.toString(i + 1) + "." + taskList.get(i));
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
            echo(String.format("Action: %s%sError: Action required a task number.", action, newIndentLine));
            return false;
        }
        String taskNumber = inputWords[1];
        // Check if task number is a valid integer
        if (!isInteger(taskNumber) || Integer.parseInt(taskNumber) <= 0) {
            echo(String.format("Action: %s%sError: Task number '%s' is invalid.", action, newIndentLine, taskNumber));
            return false;
        }
        // Check if task number exist in taskList
        if (Integer.parseInt(taskNumber) > taskList.size()) {
            echo(String.format("Action: %s%sError: Task number '%s' not found.", action, newIndentLine, taskNumber));
            return false;
        }
        return true;
    }

    public static void markTaskAsDone(String taskNumber) {
        Task task = taskList.get(Integer.parseInt(taskNumber) - 1);
        task.markAsDone();
        String message = "Nice! I've marked this task as done:" + newIndentLine + "  " + task;
        echo(message);
    }

    public static void markTaskAsUndone(String taskNumber) {
        Task task = taskList.get(Integer.parseInt(taskNumber) - 1);
        task.markAsUndone();
        String message = "OK, I've marked this task as not done yet:" + newIndentLine + "  " + task;
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
                case addToDoTaskKeyword -> new ToDo(taskDetail);
                case addDeadlineTaskKeyword -> parseDeadlineTask(taskDetail);
                case addEventTaskKeyword -> parseEventTask(taskDetail);
                default -> throw new InvalidTaskFormatException("Unknown task type.");
            };
            taskList.add(newTask);
            echo(String.format("Got it. I've added this task:%s  %s%sNow you have %s tasks in the list.", newIndentLine, newTask, newIndentLine, taskList.size()));
        } catch (InvalidTaskFormatException e) {
            echo(String.format("Action: addTask%sError: %s", newIndentLine, e.getMessage()));
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
        echo(logo + newIndentLine + "Hello! I'm WKDuke" + newIndentLine + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        String action = "";

        while (!action.equals(exitKeyword)) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            String[] inputWords = input.split(" ", 2);
            action = inputWords[0];
            switch (action) {
                case exitKeyword:
                    break;
                case listTaskKeyword:
                    printTaskList();
                    break;
                case markTaskDoneKeyword:
                    if (checkUpdateTaskInput(inputWords)) {
                        markTaskAsDone(inputWords[1]);
                    }
                    break;
                case markTaskUndoneKeyword:
                    if (checkUpdateTaskInput(inputWords)) {
                        markTaskAsUndone(inputWords[1]);
                    }
                    break;
                case addToDoTaskKeyword:
                case addDeadlineTaskKeyword:
                case addEventTaskKeyword:
                    addTask(inputWords[1], action);
                    break;
                default:
                    echo(String.format("Action: userInput%sError: Unknown command for '%s'.", newIndentLine, input));
                    break;
            }
        }

        echo("Bye. Hope to see you again soon!");
    }
}
