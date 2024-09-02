import java.util.Scanner;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startEcho() {
        while (true) {
            String echo = getString();
            Log.printSeparator();

            switch (echo.toLowerCase()) {
                case "bye":
                    Log.printMsg("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    EchoStorage.list();
                    break;
                default:
                    EchoStorage.add(echo);
                    Log.printMsg(echo);
                    break;
            }
        }
    }

    public static String getString() {
        return scanner.nextLine().trim();
    }
}
