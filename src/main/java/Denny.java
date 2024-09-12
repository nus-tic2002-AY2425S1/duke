import java.util.Scanner;

public class Denny {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        greetUser();
        processUserInput();
        exitMessage();
    }

    private static void greetUser() {
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
    }

    private static void processUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                listTasks();
            } else if (userInput.toLowerCase().startsWith("mark")) {
                markTaskAsDone(userInput);
            } else if (userInput.toLowerCase().startsWith("unmark")) {
                unmarkTask(userInput);
            } else if (userInput.toLowerCase().startsWith("todo")) {
                addTodoTask(userInput);
            } else if (userInput.toLowerCase().startsWith("deadline")) {
                addDeadlineTask(userInput);
            } else if (userInput.toLowerCase().startsWith("event")) {
                addEventTask(userInput);
            } else {
                invalidCommand();
            }
        }

        scanner.close();
    }

    private static void listTasks() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTaskAsDone(String userInput) {
        int taskIndex = getTaskIndex(userInput);
        if (taskIndex != -1) {
            tasks[taskIndex].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks[taskIndex]);
            System.out.println("____________________________________________________________");
        }
    }

    private static void unmarkTask(String userInput) {
        int taskIndex = getTaskIndex(userInput);
        if (taskIndex != -1) {
            tasks[taskIndex].markAsNotDone();
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks[taskIndex]);
            System.out.println("____________________________________________________________");
        }
    }

    private static void addTodoTask(String userInput) {
        String description = userInput.substring(5);
        tasks[taskCount] = new ToDo(description);
        taskCount++;
        printTaskAddedMessage();
    }

    private static void addDeadlineTask(String userInput) {
        String[] parts = userInput.split(" /by ");
        String description = parts[0].substring(9);
        String by = parts[1];
        tasks[taskCount] = new Deadline(description, by);
        taskCount++;
        printTaskAddedMessage();
    }

    private static void addEventTask(String userInput) {
        String[] parts = userInput.split(" /from | /to ");
        String description = parts[0].substring(6);
        String from = parts[1];
        String to = parts[2];
        tasks[taskCount] = new Event(description, from, to);
        taskCount++;
        printTaskAddedMessage();
    }

    private static void invalidCommand() {
        System.out.println("____________________________________________________________");
        System.out.println(" Invalid command. Please enter a valid task type.");
        System.out.println("____________________________________________________________");
    }

    private static void printTaskAddedMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + tasks[taskCount - 1]);
        System.out.println("____________________________________________________________");
    }

    private static int getTaskIndex(String userInput) {
        try {
            String[] parts = userInput.split(" ");
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                return taskIndex;
            } else {
                System.out.println(" Task number is out of bounds.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println(" Invalid task number.");
        }
        return -1;
    }

    private static void exitMessage() {
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
