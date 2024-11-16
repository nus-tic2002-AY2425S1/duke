import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class KLbot {
    private static final String botName = "KLbot";
    private static final Scanner in = new Scanner(System.in);
    private static final ArrayList<TaskList> taskList = new ArrayList<>();
    private static String errorTask;
    public static String filePath = "data/KLBot.txt";

    public static void main(String[] args) throws KLBotException {
        greetUser();
        loadTasksFromFile();
        botLoop();
        saveTasksToFile();
        sayGoodbye();
        in.close();
    }

    private static void greetUser() {
        printLine();
        System.out.println("Hey there! I'm " + botName + ", your cheerful helper buddy! 😊");
        System.out.println("Here’s a quick guide to help you out! 😊");
        System.out.println("\t✅ To mark a task as complete or undo it, just type 'mark' or 'unmark' followed by the task number. For example: 'mark 1'");
        System.out.println("\t📚 To add a ToDo task, simply type 'todo' followed by your task description. For example: 'todo borrow book'");
        System.out.println("\t📅 To add an Event task, type 'event' followed by the event details and date/time. For example: 'event project meeting /from Mon 2pm /to 4pm'");
        System.out.println("\t⏰ To add a Deadline task, type 'deadline' followed by your task description and the deadline day. For example: 'deadline return book /by Sunday'");

        System.out.println("What can I do for you today? (Type 'bye' when you're ready to go!)");
        printLine();
    }

    private static void botLoop() throws KLBotException {
        while (true) {
            String userInput = in.nextLine();
            String inputToLowerCase = userInput.toLowerCase();
            if (isExit(userInput)) break;
            try {
                if (inputToLowerCase.isBlank()) {
                    throw new KLBotException("Oops! It looks like you didn’t enter anything. Please type something to continue.");
                } else if (inputToLowerCase.startsWith("mark")) {
                    handleTaskAction(userInput, true);
                } else if (inputToLowerCase.startsWith("unmark")) {
                    handleTaskAction(userInput, false);
                } else if (inputToLowerCase.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                    addTask(userInput);
                } else if (isShowList(userInput)) {
                    displayTaskList();
                } else if (inputToLowerCase.startsWith("delete")) {
                    deleteTask(userInput);
                } else {
                    throw new KLBotException("Hmm, I didn't quite catch that. Could you please try again?");
                }
            } catch (KLBotException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean isExit(String userInput) {
        return "bye".equalsIgnoreCase(userInput);
    }

    private static boolean isShowList(String userInput) {
        return "list".equalsIgnoreCase(userInput);
    }

    private static void handleTaskAction(String userInput, boolean isMarkAction) throws KLBotException {
        int taskIndex = parseTaskIndex(userInput);
        if (taskIndexIsValid(taskIndex)) {
            TaskList task = taskList.get(taskIndex);
            if (isMarkAction) {
                task.markAsCompleted();
                System.out.println("Yay! You've successfully marked this task as done!");
            } else {
                task.markAsIncomplete();
                System.out.println("Alright, I've marked this task as not done yet. Let me know if you need anything else!");
            }
            System.out.println(task);
            printLine();
            saveTasksToFile();
        }
    }

    private static int parseTaskIndex(String userInput) throws KLBotException {
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new KLBotException("Oops! The task number seems to be invalid. Please try again. ");
        }
    }

    private static boolean taskIndexIsValid(int taskIndex) throws KLBotException {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new KLBotException("Oops! This task number doesn't exist. Could you check again?");
        }
        return true;
    }

    private static void addTask(String userInput) throws KLBotException {
        TaskList task = createTask(userInput);
        if (task != null) {
            taskList.add(task);
            System.out.println("Got it! I've added this task to your list:");
            System.out.println(task);
            printLine();
            System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
            saveTasksToFile();
        }
    }

    private static boolean hasDescription(String userInput) {
        if (userInput.startsWith("todo")) {
            String[] todoDescription = userInput.split(" ");
            if (todoDescription.length <= 1) {
                errorTask = "Oh no! It seems there’s a little hiccup with your To Do task description. Make sure it follows this format: 'todo borrow book'. Thanks!";
                return false;
            }
            return true;
        }
        if (userInput.startsWith("deadline")) {
            String[] deadlineDescription = userInput.split(" /by ");
            if (deadlineDescription.length != 2) {
                errorTask = "Oh no! It seems there’s a little hiccup with your Deadline task description. Make sure it follows this format: 'deadline return book /by Sunday'. Thanks!";
                return false;
            }
            return true;
        }
        if (userInput.startsWith("event")) {
            String[] eventDescription = userInput.split(" /from | /to ");
            if (eventDescription.length != 3) {
                errorTask = "Oh no! It seems there’s a little hiccup with your Event task description. Make sure it follows this format: 'event project meeting /from Mon 2pm /to 4pm'. Thanks!";
                return false;
            }
            return true;
        }
        return false;
    }

    private static TaskList createTask(String userInput) throws KLBotException {
        try {
            if (!hasDescription(userInput)) {
                throw new KLBotException(errorTask);
            }
            if (userInput.startsWith("todo")) {
                return new ToDo(userInput.replace("todo ", "").trim());
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.replace("deadline ", "").split("/by", 2);
                if (parts.length < 2) return null;
                return new Deadline(parts[0].trim(), parts[1].trim());
            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.replace("event ", "").split("/", 3);
                if (parts.length < 3) return null;
                return new Event(parts[0].trim(), parts[1].replace("from ", "").trim(), parts[2].replace("to ", "").trim());
            }
        } catch (KLBotException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void deleteTask(String userInput) throws KLBotException {
        int taskIndex = parseTaskIndex(userInput);
        if (taskIndexIsValid(taskIndex)) {
            TaskList task = taskList.get(taskIndex);
            printLine();
            System.out.println("Alrighty! I've successfully removed this task: \n\t" + taskList.get(taskIndex));
            taskList.remove(task);
            System.out.println("You've now got " + taskList.size() + " task(s) left in your list. Keep up the great work!");
            printLine();
            saveTasksToFile();
        }
    }

    public static void displayTaskList() {
        if (taskList.isEmpty()) {
            System.out.println("It looks like your task list is empty. No worries! Add some tasks, and I'll be here to help you manage them!");
            return;
        }

        printLine();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + taskList.get(i));
        }
        printLine();
    }

    private static void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (TaskList task : taskList) {
                writer.write(task.toFileFormat() + "\n");
            }
            System.out.println("Everything is saved! Your tasks are safe and sound. 😊");
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong while saving tasks to file. Please try again! 😟");
        }
    }

    private static void loadTasksFromFile() {
        File file = new File(filePath);
        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    TaskList task = TaskList.fromFileFormat(line);

                    if (task != null) {
                        taskList.add(task);
                    }
                }
                if (!taskList.isEmpty()) {
                    System.out.println("Yay! I found some tasks you’ve saved earlier:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("\t" + (i + 1) + "." + taskList.get(i));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Uh-oh, I couldn’t find your saved tasks. Maybe you don’t have any yet? 😊");
            }
        }
    }

    private static void sayGoodbye() {
        printLine();
        System.out.println("Aww, you're leaving? It was so nice chatting with you! Take care and see you soon! 💖");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
