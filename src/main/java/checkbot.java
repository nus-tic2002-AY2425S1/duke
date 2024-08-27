import java.util.Scanner;

public class checkbot {
    public static String horizontalLine = "--------------------------------------";
    public static String[] tasks = new String[100];
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
            System.out.println(i+1 + ". " + tasks[i]);
        }

        System.out.println(horizontalLine);
    }

    public static void printEcho(String input) {
        System.out.println(horizontalLine + System.lineSeparator() +
                "added: " + input + System.lineSeparator() +
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

            if (input.equals("bye")) {
                printExit();
                break;
            } else if (input.equals("list")) {
                printTasks();
            } else {
                tasks[taskCount] = input;
                taskCount++;
                printEcho(input);
            }
        }
    }
}
