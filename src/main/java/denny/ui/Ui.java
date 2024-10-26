package denny.ui;

import denny.task.Task;
import denny.task.Event;
import denny.task.Deadline;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private final Scanner scanner;
    private static final DateTimeFormatter DISPLAY_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

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

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    public void showTasksLoaded(int taskCount) {
        System.out.println(" " + taskCount + " task(s) loaded from storage.");
    }

    public void showError(String message) {
        System.out.println(" Oops! " + message);
    }

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

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskMarkedDone(Task task) {
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
    }

    public void showTaskDeleted(Task task, int remainingTasks) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + remainingTasks + " tasks in the list.");
    }

    public void showExitMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
    }
}