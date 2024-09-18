import java.util.Scanner;

public class Denny {
    private static final int MAX_TASKS = 100;
    private static final Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        UIUtil.printGreeting();
        processUserInput();
        UIUtil.printExitMessage();
    }

    private static void processUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            try {
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
                    throw new IllegalArgumentException("Unknown command: " + userInput);
                }
            } catch (IllegalArgumentException e) {
                UIUtil.printUnknownCommand(e.getMessage());
            } catch (Exception e) {
                UIUtil.printError(" An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void listTasks() {
        UIUtil.printListTasksHeader();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTaskAsDone(String userInput) {
        try {
            int taskIndex = getTaskIndex(userInput);
            if (taskIndex != -1) {
                tasks[taskIndex].markAsDone();
                UIUtil.printTaskMarkedDone(tasks[taskIndex]);
            }
        } catch (Exception e) {
            UIUtil.printError("Error marking task as done: " + e.getMessage());
        }
    }

    private static void unmarkTask(String userInput) {
        try {
            int taskIndex = getTaskIndex(userInput);
            if (taskIndex != -1) {
                tasks[taskIndex].markAsNotDone();
                UIUtil.printTaskUnmarked(tasks[taskIndex]);
            }
        } catch (Exception e) {
            UIUtil.printError("Error unmarking task: " + e.getMessage());
        }
    }

    private static void addTodoTask(String userInput) {
        try {
            if (userInput.length() <= 5) { // Check if input is too short
                throw new IllegalArgumentException("The description of a todo cannot be empty.");
            }
            String description = userInput.substring(5).trim(); // Extract the description
            if (description.isEmpty()) {
                throw new IllegalArgumentException("The description of a todo cannot be empty.");
            }
            tasks[taskCount] = new ToDo(description);
            taskCount++;
            UIUtil.printTaskAddedMessage(tasks[taskCount - 1]);
        } catch (StringIndexOutOfBoundsException e) {
            UIUtil.printError("An error occurred while processing your todo task. Please make sure you provide a description.");
        } catch (IllegalArgumentException e) {
            UIUtil.printError(e.getMessage());
        } catch (Exception e) {
            UIUtil.printError("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void addDeadlineTask(String userInput) {
        try {
            if (!userInput.contains(" /by ")) {
                throw new IllegalArgumentException("Please provide a valid deadline format: 'deadline <description> /by <date>'.");
            }
            String[] parts = userInput.split(" /by ", 2);
            if (parts.length < 2) {
                throw new IllegalArgumentException("Please provide the deadline in the format: deadline <description> /by <date>");
            }
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            tasks[taskCount] = new Deadline(description, by);
            taskCount++;
            UIUtil.printTaskAddedMessage(tasks[taskCount - 1]);
        } catch (StringIndexOutOfBoundsException e) {
            UIUtil.printError("An error occurred while processing your deadline task. Please make sure the description is properly formatted.");
        } catch (IllegalArgumentException e) {
            UIUtil.printError(e.getMessage());
        } catch (Exception e) {
            UIUtil.printError("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void addEventTask(String userInput) {
        try {
            String[] parts = userInput.split(" /from | /to ");
            if (parts.length < 3) {
                throw new IllegalArgumentException("Please provide the event details in the format: event <description> /from <start date> /to <end date>");
            }
            String description = parts[0].substring(6).trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new IllegalArgumentException("None of the event details can be empty.");
            }
            tasks[taskCount] = new Event(description, from, to);
            taskCount++;
            UIUtil.printTaskAddedMessage(tasks[taskCount - 1]);
        } catch (IllegalArgumentException e) {
            UIUtil.printError(e.getMessage());
        }
    }

    private static int getTaskIndex(String userInput) {
        try {
            String[] parts = userInput.split(" ");
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskCount) {
                return taskIndex;
            } else {
                throw new IndexOutOfBoundsException("Task index out of bounds.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number format.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid task number. Please enter a valid number.");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
