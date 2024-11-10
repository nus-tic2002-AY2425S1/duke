package snitch;

import snitch.task.TaskList;
import snitch.task.Task;
import java.util.Scanner;

/**
 * Handles all user interactions for the Snitch chatbot.
 * Provides methods to display messages and read user input.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui instance.
     * Initializes a scanner to read user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the next line of user input.
     *
     * @return The user input as a String.
     */
    public String readCommand() {
        String input = scanner.nextLine();
        assert input != null && !input.trim().isEmpty() : "Input command cannot be null or empty"; // Valid command
        return input;
    }

    /**
     * Displays the welcome message, including a logo.
     */
    public void showWelcome() {
        String logo =
                "       /\\_____/\\         \n" +
                        "      /  o   o  \\        \n" +
                        "     ( ==  ^  == )       \n" +
                        "      )           (      \n" +
                        "     (             )      /\n" +
                        "     (  (__) (__ )  ) ___/    \n" +
                        "      \\___________/      \n" +
                        "                       \n";

        System.out.println(logo);
        System.out.println("Hello! I'm Snitch tha ChATbot! Meow!");

        System.out.println("\nHere are some things I can do for you:");
        System.out.println("1. Add a ToDo: todo <task description>");
        System.out.println("2. Add a Deadline: deadline <task description> /by <d/M/yyyy HHmm>");
        System.out.println("3. Add an Event: event <task description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>");
        System.out.println("4. Delete a task: delete <task number>");
        System.out.println("5. List all tasks: list");
        System.out.println("6. Find tasks by keyword: find <keyword>");
        System.out.println("Type 'bye' to exit the chatbot.");

        System.out.println("\nWhat can I do for you? (meow)\n");
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon man! (meow)");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The TaskList containing tasks to display.
     */
    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list (meow):");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task The task that was added.
     * @param taskCount The total number of tasks after the addition.
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Displays a message when a task is removed.
     *
     * @param task The task that was removed.
     * @param taskCount The total number of tasks after the removal.
     */
    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println("Got it. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }
    public void showMatchingTasks(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list (meow):");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
}