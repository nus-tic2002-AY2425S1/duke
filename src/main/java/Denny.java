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
            } else {
                // Add task to array
                tasks[taskCount] = new Task(userInput);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + userInput);
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
