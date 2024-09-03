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

            // TODO: Fix design for loop
            String command = echo.split(" ")[0];
            if (command.equalsIgnoreCase("mark")) {
                TaskList.markDone(Integer.parseInt(echo.split(" ")[1]));
//                Nice! I've marked this task as done:
//       [X] return book

                continue;
            }

            if (command.equalsIgnoreCase("unmark")) {
                TaskList.unmarkDone(Integer.parseInt(echo.split(" ")[1]));
                continue;
            }

            if (command.equalsIgnoreCase("todo")) {
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
