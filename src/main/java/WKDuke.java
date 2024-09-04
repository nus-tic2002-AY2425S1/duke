import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WKDuke {
    private static final String startBorderLine = "\t____________________________________________________________";
    private static final String endBorderLine = "\t____________________________________________________________\n";
    private static final String stringIndent = "\t ";
    private static final String exitKeyword = "bye";
    private static final String listItemKeyword = "list";

    public static void echo(String message) {
        System.out.println(startBorderLine);
        System.out.println(stringIndent + message);
        System.out.println(endBorderLine);
    }

    public static void printList(List<String> list) {
        System.out.println(startBorderLine);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(stringIndent + Integer.toString(i + 1) + ". " + list.get(i));
        }
        System.out.println(endBorderLine);
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

        String input;
        List<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            input = sc.nextLine();
            input = input.trim();
            if (input.equals(exitKeyword)) {
                break;
            } else if (input.equals(listItemKeyword)) {
                printList(list);
            } else {
                list.add(input);
                echo("added: " + input);
            }
        }

        System.out.println(startBorderLine);
        System.out.println(stringIndent + "Bye. Hope to see you again soon!");
        System.out.println(endBorderLine);
    }
}
