import java.util.Scanner;
import java.util.ArrayList;

public class Snitch {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Snitch!\n" + "What can I do for you?\n" );

        // Set up for user input
        Scanner scanner = new Scanner(System.in);

        // Create a fixed-size array to store up to 100 tasks
        Task[] tasks = new Task[100];
        int taskCount = 0;  // To track the number of tasks added

        //  keep accepting user input
        while (true) {
            // Get input from the user
            String userInput = scanner.nextLine();

            // Check if the user types "bye"
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;  // Exit the loop
            }

            // Check if the user types "list"
            if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i].toString());
                }
            }
            // Adding tasks: ToDo, Deadline, Event
            else if (userInput.startsWith("todo")) {
                String description = userInput.substring(5);
                tasks[taskCount] = new Todo(description);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
            else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split("/by ");
                String description = parts[0].substring(9);
                String by = parts[1];
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
            else if (userInput.startsWith("event")) {
                String[] parts = userInput.split("/from | /to ");
                String description = parts[0].substring(6);
                String from = parts[1];
                String to = parts[2];
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + tasks[taskCount - 1]);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
            }
            else {
                System.out.println("What are you asking??");
            }
        }

        // Close the scanner
        scanner.close();
    }
}