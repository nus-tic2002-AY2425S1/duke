package duke.dancepop;

import duke.dancepop.entities.Task;
import duke.dancepop.entities.Todo;

import java.util.Scanner;

public class Input {

    private static final Scanner scanner = new Scanner(System.in);

    public static void startEcho() {
        while (true) {
            String echo = getString();
            Task task = new Todo(echo);
            Log.printSeparator();

            // TODO: Fix design for loop
            String markOrUnmark = echo.split(" ")[0];
            if (markOrUnmark.equalsIgnoreCase("mark")) {
                TaskList.markDone(Integer.parseInt(echo.split(" ")[1]));
                continue;
            }

            if (markOrUnmark.equalsIgnoreCase("unmark")) {
                TaskList.unmarkDone(Integer.parseInt(echo.split(" ")[1]));
                continue;
            }

            switch (echo.toLowerCase()) {
                case "bye":
                    Log.printMsg("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    TaskList.print();
                    break;
                default:
                    TaskList.add(task);
                    Log.printMsg(echo);
                    break;
            }
        }
    }

    public static String getString() {
        return scanner.nextLine().trim();
    }
}
