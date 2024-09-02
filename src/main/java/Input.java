import java.util.Scanner;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startEcho() {
        String echo = "";
        while (!echo.equalsIgnoreCase("bye")) {
            echo = getString();
            Log.printSeperator();
            Log.printMsg(echo);
        }
        Log.printMsg("Bye. Hope to see you again soon!");
    }

    public static String getString() {
        return scanner.nextLine().trim();
    }
}
