import java.util.Scanner;

public class checkbot {
    public static String horizontalLine = "--------------------------------------";
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static void printHello() {
        System.out.println(horizontalLine + System.lineSeparator() +
                "Hello! I'm checkbot." + System.lineSeparator() +
                "What can I do for you? :)" + System.lineSeparator() +
                horizontalLine);
    }

    public static void printExit() {
        System.out.println(horizontalLine + System.lineSeparator() +
                "Bye. Hope to see you again soon!" + System.lineSeparator() +
                horizontalLine);
    }

    public static void printTasks() {
        System.out.println(horizontalLine);
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i+1 + ". " + tasks[i].getDescription());
        }
        System.out.println(horizontalLine);
    }

    public static void addTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;
        System.out.println(horizontalLine + System.lineSeparator() +
                "added: " + description + System.lineSeparator() +
                horizontalLine);
    }

    public static String readInput() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        return input;
    }

    public static void main(String[] args) {
        printHello();

        while (true) {
            String input = readInput();

            if (input.trim().equalsIgnoreCase("bye")) {
                printExit();
                break;
            } else if (input.trim().equalsIgnoreCase("list")) {
                printTasks();
            } else {
                addTask(input);
            }
        }
    }
}
