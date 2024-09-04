import java.util.Scanner;

public class WKDuke {
    private static final String startBorderLine = "\t____________________________________________________________";
    private static final String endBorderLine = "\t____________________________________________________________\n";
    private static final String stringIndent = "\t ";
    private static final String exitKeyword = "bye";

    public static void echo(String message) {
        System.out.println(startBorderLine);
        System.out.println(stringIndent + message);
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
        do {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if (!input.equals(exitKeyword)) {
                echo(input);
            }
        } while (!input.equals(exitKeyword));

        System.out.println(startBorderLine);
        System.out.println(stringIndent + "Bye. Hope to see you again soon!");
        System.out.println(endBorderLine);
    }
}
