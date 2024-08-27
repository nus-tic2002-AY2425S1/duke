import java.util.Scanner;

public class checkbot {
    public static String horizontalLine = "--------------------------------------";

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

    public static void printEcho() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        if (input.equals("bye")) {
            printExit();
        } else {
            System.out.println(horizontalLine + System.lineSeparator() +
                    input + System.lineSeparator() +
                    horizontalLine);
            printEcho();
        }
    }

    public static void main(String[] args) {
        printHello();
        printEcho();
    }
}
