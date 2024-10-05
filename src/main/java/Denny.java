import java.util.Scanner;
import java.util.ArrayList;

public class Denny {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private enum Command {
        BYE("bye"),
        LIST("list"),
        MARK("mark"),
        UNMARK("unmark"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete");

        private final String commandText;

        Command(String commandText) {
            this.commandText = commandText;
        }

        public static Command fromString(String text) {
            for (Command command : Command.values()) {
                if (text.toLowerCase().startsWith(command.commandText)) {
                    return command;
                }
            }
            throw new IllegalArgumentException("Unknown command: " + text);
        }
    }

    public static void main(String[] args) {
        UIUtil.printGreeting();
        processUserInput();
        UIUtil.printExitMessage();
    }

    private static void processUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String userInput = scanner.nextLine().trim();
                if (userInput.equalsIgnoreCase(Command.BYE.commandText)) {
                    break;
                }
                processCommand(userInput);
            }
        }
    }

    private static void processCommand(String userInput) {
        try {
            Command command = Command.fromString(userInput);
            switch (command) {
                case LIST -> listTasks();
                case MARK -> markTaskAsDone(userInput);
                case UNMARK -> unmarkTask(userInput);
                case TODO -> addTodoTask(userInput);
                case DEADLINE -> addDeadlineTask(userInput);
                case EVENT -> addEventTask(userInput);
                case DELETE -> deleteTask(userInput);
            }
        } catch (IllegalArgumentException e) {
            UIUtil.printUnknownCommand(e.getMessage());
        } catch (Exception e) {
            UIUtil.printError("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void listTasks() {
        UIUtil.printListTasksHeader();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTaskAsDone(String userInput) {
        processTaskStatusChange(userInput, true);
    }

    private static void unmarkTask(String userInput) {
        processTaskStatusChange(userInput, false);
    }

    private static void processTaskStatusChange(String userInput, boolean markAsDone) {
        try {
            int taskIndex = getTaskIndex(userInput);
            Task task = tasks.get(taskIndex);
            if (markAsDone) {
                task.markAsDone();
                UIUtil.printTaskMarkedDone(task);
            } else {
                task.markAsNotDone();
                UIUtil.printTaskUnmarked(task);
            }
        } catch (Exception e) {
            UIUtil.printError("Error changing task status: " + e.getMessage());
        }
    }

    private static String extractDescription(String input, String taskType) {
        String description = input.substring(taskType.length()).trim();
        if (description.isEmpty()) {
            throw new IllegalArgumentException("The description of a " + taskType + " cannot be empty.");
        }
        return description;
    }

    private static void addTodoTask(String userInput) {
        try {
            String description = extractDescription(userInput, "todo");
            Task newTask = new ToDo(description);
            tasks.add(newTask);
            UIUtil.printTaskAddedMessage(newTask);
        } catch (IllegalArgumentException e) {
            UIUtil.printError(e.getMessage());
        }
    }

    private static void addDeadlineTask(String userInput) {
        try {
            String[] parts = userInput.split(" /by ", 2);
            if (parts.length < 2) {
                throw new IllegalArgumentException("Please provide the deadline in the format: deadline <description> /by <date>");
            }
            String description = extractDescription(parts[0], "deadline");
            String by = parts[1].trim();
            Task newTask = new Deadline(description, by);
            tasks.add(newTask);
            UIUtil.printTaskAddedMessage(newTask);
        } catch (IllegalArgumentException e) {
            UIUtil.printError(e.getMessage());
        }
    }

    private static void addEventTask(String userInput) {
        try {
            String[] parts = userInput.split(" /from | /to ");
            if (parts.length < 3) {
                throw new IllegalArgumentException("Please provide the event details in the format: event <description> /from <start date> /to <end date>");
            }
            String description = extractDescription(parts[0], "event");
            String from = parts[1].trim();
            String to = parts[2].trim();
            Task newTask = new Event(description, from, to);
            tasks.add(newTask);
            UIUtil.printTaskAddedMessage(newTask);
        } catch (IllegalArgumentException e) {
            UIUtil.printError(e.getMessage());
        }
    }

    private static void deleteTask(String userInput) {
        try {
            int taskIndex = getTaskIndex(userInput);
            Task deletedTask = tasks.remove(taskIndex);
            UIUtil.printTaskDeletedMessage(deletedTask, tasks.size());
        } catch (Exception e) {
            UIUtil.printError("Error deleting task: " + e.getMessage());
        }
    }

    private static int getTaskIndex(String userInput) {
        try {
            String[] parts = userInput.split(" ");
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new IndexOutOfBoundsException("Task index out of bounds.");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid task number format.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid task number. Please enter a valid number.");
        }
    }
}
