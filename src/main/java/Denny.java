import java.util.Scanner;

public class Denny {
    public static void main(String[] args) {
        // Greet the user
        String logo = "____                         \n"
                + "|  _ \\  ___ _ __  _ __  _   _ \n"
                + "| | | |/ _ \\ '_ \\| '_ \\| | | |\n"
                + "| |_| |  __/ | | | | | | |_| |\n"
                + "|____/ \\___|_| |_|_| |_|\\__, |\n"
                + "                        |___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Denny");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        // Create Scanner
        Scanner scanner = new Scanner(System.in);
        String userInput;

        // Array to store up to 100 tasks
        Task[] tasks = new Task[100];
        int taskCount = 0;

        // User input loop processing
        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                // List tasks
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().startsWith("mark")) {
                // Mark task as done
                String[] parts = userInput.split(" ");
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                tasks[taskIndex].markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                // Unmark task as not done
                String[] parts = userInput.split(" ");
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                tasks[taskIndex].markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + tasks[taskIndex]);
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().startsWith("todo")) {
                // Add ToDo task
                String description = userInput.substring(5);
                tasks[taskCount] = new ToDo(description);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().startsWith("deadline")) {
                // Add Deadline task
                String[] parts = userInput.split(" /by ");
                String description = parts[0].substring(9);
                String by = parts[1];
                tasks[taskCount] = new Deadline(description, by);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println("____________________________________________________________");
            } else if (userInput.toLowerCase().startsWith("event")) {
                // Add Event task
                String[] parts = userInput.split(" /from | /to ");
                String description = parts[0].substring(6);
                String from = parts[1];
                String to = parts[2];
                tasks[taskCount] = new Event(description, from, to);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount - 1]);
                System.out.println("____________________________________________________________");
            } else {
                // Invalid command
                System.out.println("____________________________________________________________");
                System.out.println(" Invalid command. Please enter a valid task type.");
                System.out.println("____________________________________________________________");
            }
        }

        // Exit message
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        // Close scanner
        scanner.close();
    }
}
