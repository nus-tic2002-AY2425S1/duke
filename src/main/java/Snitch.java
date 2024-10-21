import java.util.Scanner;
import java.util.ArrayList;
    public class Snitch {
        public static void main(String[] args) {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello! I'm Snitch!\n" + "What can I do for you?\n");

            // Set up for user input
            Scanner scanner = new Scanner(System.in);

            // Create a fixed-size array to store up to 100 tasks
            Task[] tasks = new Task[100];
            int taskCount = 0;  // To track the number of tasks added

            //  keep accepting user input
            while (true) {
                try {
                    // Get input from the user
                    String userInput = scanner.nextLine();

                    // Check if the user types "bye"
                    if (userInput.equalsIgnoreCase("bye")) {
                        System.out.println("Bye. Hope to see you again soon man!");
                        break;  // Exit the loop
                    }

                    // Check if the user types "list"
                    if (userInput.equalsIgnoreCase("list")) {
                        if (taskCount == 0) {
                            throw new SnitchException("Your task list is empty.");
                        }
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskCount; i++) {
                            System.out.println((i + 1) + "." + tasks[i].toString());
                        }
                    }
                    // Adding tasks: ToDo, Deadline, Event
                    else if (userInput.startsWith("todo")) {
                        if (userInput.length() <= 5) { // Check if the input is less than or equal to "todo " (5 characters)
                            throw new SnitchException("Come on man!!! The description of a todo cannot be empty.");
                        } else {
                            String description = userInput.substring(5).trim(); // Safely extract the description
                            if (description.isEmpty()) { // Additional check to ensure description is not just space
                                throw new SnitchException("Come on man!!! The description of a todo cannot be empty.");
                            }
                            tasks[taskCount] = new Todo(description);
                            taskCount++;
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + tasks[taskCount - 1]);
                            System.out.println("Now you have " + taskCount + " tasks in the list.");
                        }
                    }
                    else if (userInput.startsWith("deadline")) {
                        String[] parts = userInput.split("/by ");
                        if (parts.length < 2) {
                            throw new SnitchException("Come on man!!! The deadline description cannot be empty or the format is wrong.");
                        }
                        String description = parts[0].substring(9).trim();
                        String by = parts[1].trim();
                        tasks[taskCount] = new Deadline(description, by);
                        taskCount++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[taskCount - 1]);
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                    }
                    else if (userInput.startsWith("event")) {
                        String[] parts = userInput.split("/from | /to ");
                        if (parts.length < 3) {
                            throw new SnitchException("Come on man!!! The event format is incorrect.");
                        }
                        String description = parts[0].substring(6).trim();
                        String from = parts[1].trim();
                        String to = parts[2].trim();
                        tasks[taskCount] = new Event(description, from, to);
                        taskCount++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks[taskCount - 1]);
                        System.out.println("Now you have " + taskCount + " tasks in the list.");
                    }
                    else {
                        throw new SnitchException("Come on man!!! I don't know what that means.");
                    }
                } catch (SnitchException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }

            // Close the scanner
            scanner.close();
        }
    }