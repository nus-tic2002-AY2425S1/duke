import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WKDuke {
    private static final String startBorderLine = "\t____________________________________________________________";
    private static final String endBorderLine = "\t____________________________________________________________\n";
    private static final String stringIndent = "\t ";
    private static final String newIndentLine = System.lineSeparator() + "\t ";

    private static final String exitKeyword = "bye";
    private static final String listTaskKeyword = "list";
    private static final String markTaskDoneKeyword = "mark";
    private static final String markTaskUndoneKeyword = "unmark";
    private static List<Task> taskList = new ArrayList<>();

    public static void echo(String message) {
        System.out.println(startBorderLine);
        System.out.println(stringIndent + message);
        System.out.println(endBorderLine);
    }

    public static void printList(List<Task> taskList) {
        System.out.println(startBorderLine);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(stringIndent + Integer.toString(i + 1) + ". " + taskList.get(i));
        }
        System.out.println(endBorderLine);
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

        System.out.println(startBorderLine);
        System.out.println(logo);
        System.out.println(stringIndent + "Hello! I'm WKDuke\n" + stringIndent + "What can I do for you?");
        System.out.println(endBorderLine);

        Scanner sc = new Scanner(System.in);
        String action = "";

        while (!action.equals(exitKeyword)) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            String[] inputWords = input.split(" ");
            action = inputWords[0];
            switch (action) {
                case exitKeyword:
                    break;
                case listTaskKeyword:
                    printList(taskList);
                    break;
                case markTaskDoneKeyword:
                    if (checkUpdateTaskInput(inputWords)) {
                        // Mark Task Done
                    }
                    break;
                case markTaskUndoneKeyword:
                    if (checkUpdateTaskInput(inputWords)) {
                        // Mark Task Undone
                    }
                    break;
                default:
                    taskList.add(new Task(input));
                    echo("added: " + input);
                    break;
            }
        }

        System.out.println(startBorderLine);
        System.out.println(stringIndent + "Bye. Hope to see you again soon!");
        System.out.println(endBorderLine);
    }
}
