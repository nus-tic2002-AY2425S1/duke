import java.util.Scanner;

public class checkbot {
    public static String horizontalLine = "--------------------------------------";
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static String readInput() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        return input;
    }

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

    public static void addTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;
        System.out.println(horizontalLine + System.lineSeparator() +
                "added: " + description + System.lineSeparator() +
                horizontalLine);
    }

    public static void printTasks() {
        System.out.println(horizontalLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i+1 + ". [" + tasks[i].getStatusIcon() + "] " +
                    tasks[i].getDescription());
        }
        System.out.println(horizontalLine);
    }

    public static void markTask(int taskNum) {
        // TODO: exception when taskNum is 0 or > taskCount
        tasks[taskNum-1].setDone(true);
        System.out.println(horizontalLine + System.lineSeparator() +
                "Nice! I've marked this task as done: " + System.lineSeparator() +
                "  [" + tasks[taskNum-1].getStatusIcon() + "] " + tasks[taskNum-1].getDescription() + System.lineSeparator() +
                horizontalLine);
    }

    public static void unmarkTask(int taskNum) {
        // TODO: exception when taskNum is 0 or > taskCount
        tasks[taskNum-1].setDone(false);
        System.out.println(horizontalLine + System.lineSeparator() +
                "Okay, I've marked this task as not done yet: " + System.lineSeparator() +
                "  [" + tasks[taskNum-1].getStatusIcon() + "] " + tasks[taskNum-1].getDescription() + System.lineSeparator() +
                horizontalLine);
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
            } else if (input.toLowerCase().startsWith("mark")) {
                markTask(Integer.parseInt(input.substring(5)));
            } else if (input.toLowerCase().startsWith("unmark")) {
                unmarkTask(Integer.parseInt(input.substring(7)));
            } else {
                addTask(input);
            }
        }
    }
}
