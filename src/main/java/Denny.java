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
        Scanner scanner = new Scanner(System.in);
        String userInput;

        try {
            while (true) {
                userInput = scanner.nextLine();

                try {
                    Command command = Command.fromString(userInput);
                    switch (command) {
                        case BYE:
                            return;
                        case LIST:
                            listTasks();
                            break;
                        case MARK:
                            markTaskAsDone(userInput);
                            break;
                        case UNMARK:
                            unmarkTask(userInput);
                            break;
                        case TODO:
                            addTodoTask(userInput);
                            break;
                        case DEADLINE:
                            addDeadlineTask(userInput);
                            break;
                        case EVENT:
                            addEventTask(userInput);
                            break;
                        case DELETE:
                            deleteTask(userInput);
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    UIUtil.printUnknownCommand(e.getMessage());
                } catch (Exception e) {
                    UIUtil.printError("An unexpected error occurred: " + e.getMessage());
                }
            }
        } finally {
            scanner.close();
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
        try {
            int taskIndex = getTaskIndex(userInput);
            if (taskIndex != -1) {
                tasks.get(taskIndex).markAsDone();
                UIUtil.printTaskMarkedDone(tasks.get(taskIndex));
            }
        } catch (Exception e) {
            UIUtil.printError("Error marking task as done: " + e.getMessage());
        }
    }

    private static void unmarkTask(String userInput) {
        try {
            int taskIndex = getTaskIndex(userInput);
            if (taskIndex != -1) {
                tasks.get(taskIndex).markAsNotDone();
                UIUtil.printTaskUnmarked(tasks.get(taskIndex));
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
            Task newTask = new ToDo(description);
            tasks.add(newTask);
            UIUtil.printTaskAddedMessage(newTask);
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
            Task newTask = new Deadline(description, by);
            tasks.add(newTask);
            UIUtil.printTaskAddedMessage(newTask);
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
            if (taskIndex != -1) {
                Task deletedTask = tasks.remove(taskIndex);
                UIUtil.printTaskDeletedMessage(deletedTask, tasks.size());
            }
        } catch (Exception e) {
            UIUtil.printError("Error deleting task: " + e.getMessage());
        }
    }

    private static int getTaskIndex(String userInput) {
        try {
            String[] parts = userInput.split(" ");
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
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
