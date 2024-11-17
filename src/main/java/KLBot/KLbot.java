package KLBot;

import KLBot.Parser.Parser;
import KLBot.Storage.Storage;
import KLBot.TaskList.Task;
import KLBot.TaskList.TaskList;
import KLBot.Ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The KLbot class represents the main bot functionality. It interacts with the user,
 * processes commands, handles tasks, and manages the loading and saving of tasks to a file.
 */
public class KLbot {
    private static final String fileDirectory = "data";
    private static final String filePath = "data/KLBot.txt";
    private static final Scanner in = new Scanner(System.in);
    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage(fileDirectory, filePath);
    private static final Parser parser = new Parser();
    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) throws IOException {
        ui.greetUser();
        loadTasksFromFile();
        botLoop();
        saveTasksToFile();
        ui.sayGoodbye();
        in.close();
    }

    /**
     * The main loop of the bot. Continuously prompts the user for input, processes commands,
     * and handles tasks (e.g., mark, unmark, add, delete, list).
     */
    private static void botLoop() {
        while (true) {
            String userInput = in.nextLine();
            if (parser.isExit(userInput)) break;
            try {
                if (userInput.toLowerCase().startsWith("mark") || userInput.toLowerCase().startsWith("unmark")) {
                    handleTaskAction(userInput);
                } else if (userInput.toLowerCase().startsWith("todo") || userInput.toLowerCase().startsWith("deadline") || userInput.toLowerCase().startsWith("event")) {
                    addTask(userInput);
                } else if (parser.isShowList(userInput)) {
                    ui.showTaskList(taskList);
                } else if (userInput.toLowerCase().startsWith("delete")) {
                    deleteTask(userInput);
                } else if (userInput.startsWith("search")) {
                    String keyword = userInput.replace("search", "").trim();
                    searchTasks(keyword);
                } else {
                    throw new KLBotException("I did not quite catch that. Could you try again?");
                }
            } catch (KLBotException | IOException e) {
                ui.showError(e.getMessage());
            }
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
    /**
     * Marks a task as completed or uncompleted based on the user's input.
     *
     * @param userInput The user's input to mark or unmark a task.
     * @throws KLBotException If the task index is invalid or other errors occur.
     */
    private static void handleTaskAction(String userInput) throws KLBotException, IOException {
        int taskIndex = parseTaskIndex(userInput);

        if (taskIndexIsValid(taskIndex)) {
            Task task = taskList.getTask(taskIndex);
            if (userInput.toLowerCase().startsWith("mark")) {
                task.markAsCompleted();
                ui.showTaskMarked();
            } else {
                task.markAsIncomplete();
                ui.showTaskUnmarked();
            }
            System.out.println(task);
            ui.printLine();
            saveTasksToFile();
        } else {
            throw new KLBotException("Oops! The task number seems to be invalid. Please try again. ");
        }
    }

    /**
     * Adds a new task based on the user's input, which could be a ToDo, Deadline, or Event.
     *
     * @param userInput The user's input to create and add a new task.
     * @throws KLBotException If the task description is invalid or cannot be parsed.
     */
    private static void addTask(String userInput) throws KLBotException, IOException {
        Task task = parser.createTask(userInput);
        if (task != null) {
            taskList.addTask(task);
            ui.showTaskAdded(task.getDescription());
            saveTasksToFile();
        }
    }

    /**
     * Deletes a task based on the user's input.
     *
     * @param userInput The user's input to delete a task.
     * @throws KLBotException If the task index is invalid or other errors occur.
     */
    private static void deleteTask(String userInput) throws KLBotException, IOException {
        int taskIndex = parseTaskIndex(userInput);
        if (taskIndexIsValid(taskIndex)) {
            Task task = taskList.getTask(taskIndex);
            taskList.removeTask(taskIndex);
            ui.showTaskRemoved(task.getDescription());
            saveTasksToFile();
        }
    }

    /**
     * Loads tasks from the file and adds them to the task list.
     */
    private static void loadTasksFromFile() {
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();
        taskList.addTasks(loadedTasks);
    }

    /**
     * Saves the current list of tasks to the file.
     */
    private static void saveTasksToFile() {
        storage.saveTasksToFile(taskList);
    }


    /**
     * Searches for tasks in the task list that contain the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    private static void searchTasks(String keyword) {
        List<Task> matchingTasks = taskList.searchTasks(keyword);

        if (matchingTasks.isEmpty()) {
            System.out.println("Oops! I could not find any tasks with the keyword: " + keyword);
        } else {
            System.out.println("Yay! Here are the tasks I found that match your search for '" + keyword + "':");
            for (Task task : matchingTasks) {
                System.out.println(task);
            }
        }
    }
}
