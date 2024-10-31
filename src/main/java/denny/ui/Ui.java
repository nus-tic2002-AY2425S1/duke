package denny.ui;

import denny.task.Task;
import denny.task.Event;
import denny.task.Deadline;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Handles all user interface operations including input and output.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private final Scanner scanner;
    private static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    /**
     * Creates a new UI instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and logo.
     */
    public void showWelcome() {
        String logo = """
            ____
            |  _ \\  ___ _ __  _ __  _   _
            | | | |/ _ \\ '_ \\| '_ \\| | | |
            | |_| |  __/ | | | | | | |_| |
            |____/ \\___|_| |_|_| |_|\\__, |
                                    |___/
            """;
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println(" Hello! I'm Denny");
        System.out.println(" What can I do for you?");
        showLine();
    }

    /**
     * Reads a command from user input.
     * @return The command string entered by the user
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a horizontal line for better readability in the UI.
     */
    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays a message indicating how many tasks have been loaded from storage.
     *
     * @param taskCount The number of tasks loaded.
     */
    public void showTasksLoaded(int taskCount) {
        System.out.println(" " + taskCount + " task(s) loaded from storage.");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println(" Oops! " + message);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to display.
     */
    public void showTaskList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks in the list!");
            return;
        }

        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.printf(" %d.%s%n", i + 1, formatTaskWithDates(task));
        }
    }

    /**
     * Formats the task with its associated date(s) for display.
     *
     * @param task The task to format.
     * @return A string representation of the task with its date(s).
     */
    private String formatTaskWithDates(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return String.format("%s (by: %s)",
                    task.toString(),
                    deadline.getBy().format(DISPLAY_FORMATTER));
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return String.format("%s (from: %s to: %s)",
                    task.toString(),
                    event.getFrom().format(DISPLAY_FORMATTER),
                    event.getTo().format(DISPLAY_FORMATTER));
        }
        return task.toString();
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The task that was added.
     * @param totalTasks The total number of tasks in the list after addition.
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task.toString().trim());
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarkedDone(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    /**
     * Displays a message indicating that a task has been unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param task The task that was deleted.
     * @param remainingTasks The total number of tasks remaining after deletion.
     */
    public void showTaskDeleted(Task task, int remainingTasks) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
    }

    /**
     * Displays an exit message to the user.
     */
    public void showExitMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Displays a message indicating that multiple tasks have been deleted.
     *
     * @param deletedTasks The list of tasks that were deleted.
     * @param remainingTasks The total number of tasks remaining after deletion.
     */
    public void showBulkTasksDeleted(List<Task> deletedTasks, int remainingTasks) {
        System.out.println(" Noted. I've removed these tasks:");
        for (Task task : deletedTasks) {
            System.out.println("   " + task);
        }
        System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
    }

    public void showFoundTasks(List<Task> foundTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            Task task = foundTasks.get(i);
            System.out.printf(" %d.%s%n", i + 1, formatTaskWithDates(task));
        }
    }

    public void showNoMatchingTasksFound() {
        System.out.println("No tasks matching your search were found.");
    }
}