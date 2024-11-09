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

        // Initialize storage
        Storage storage = new Storage("./data/snitch.txt");
        ArrayList<Task> tasks;

        // Load tasks from file
        try {
            tasks = storage.load();
            System.out.println("Tasks loaded successfully.");
        } catch (SnitchException e) {
            System.out.println("Failed to load tasks: " + e.getMessage());
            tasks = new ArrayList<>();
        }

        // Keep accepting user input
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
                    if (tasks.isEmpty()) {
                        throw new SnitchException("Your task list is empty.");
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                }
                // Adding tasks: ToDo, Deadline, Event
                else if (userInput.startsWith("todo")) {
                    if (userInput.length() <= 5) {
                        throw new SnitchException("Come on man!!! The description of a todo cannot be empty.");
                    } else {
                        String description = userInput.substring(5).trim();
                        if (description.isEmpty()) {
                            throw new SnitchException("Come on man!!! The description of a todo cannot be empty.");
                        }
                        tasks.add(new Todo(description));
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                else if (userInput.startsWith("deadline")) {
                    String[] parts = userInput.split("/by ");
                    if (parts.length < 2) {
                        throw new SnitchException("Come on man!!! The deadline description cannot be empty or the format is wrong. (Do it like this: deadline task /by xxxx)");
                    }
                    String description = parts[0].substring(9).trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
                else if (userInput.startsWith("event")) {
                    String[] parts = userInput.split("/from ");
                    if (parts.length < 2 || !parts[1].contains("/to ")) {
                        throw new SnitchException("Come on man!!! The event format is incorrect.");
                    }
                    String description = parts[0].substring(6).trim();
                    String[] timeParts = parts[1].split(" /to ");
                    String from = timeParts[0].trim();
                    String to = timeParts[1].trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
                // Deleting tasks
                else if (userInput.startsWith("delete")) {
                    try {
                        int index = Integer.parseInt(userInput.substring(7).trim()) - 1;  // Convert input to zero-based index
                        if (index >= tasks.size() || index < 0) {
                            throw new SnitchException("Invalid task number.");
                        }
                        Task taskToDelete = tasks.remove(index);
                        System.out.println("Got it. I've removed this task:");
                        System.out.println("  " + taskToDelete);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } catch (NumberFormatException e) {
                        throw new SnitchException("Invalid task number format. Please enter a valid number.");
                    }
                }
                else {
                    throw new SnitchException("Come on man!!! I don't know what that means.");
                }

                // Save tasks after every modification
                storage.save(tasks);

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