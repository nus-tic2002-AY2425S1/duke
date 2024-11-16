package KLBot;

import KLBot.Parser.Parser;
import KLBot.Storage.Storage;
import KLBot.TaskList.Task;
import KLBot.TaskList.TaskList;
import KLBot.Ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class KLbot {

    private static final Scanner in = new Scanner(System.in);
    private static final Ui ui = new Ui();
    private static final Storage storage = new Storage("data/KLBot.txt");
    private static final Parser parser = new Parser();
    private static final TaskList taskList = new TaskList();

    public static void main(String[] args) throws KLBotException {
        ui.greetUser();
        loadTasksFromFile();
        botLoop();
        saveTasksToFile();
        ui.sayGoodbye();
        in.close();
    }

    private static void botLoop() throws KLBotException {
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
                } else {
                    throw new KLBotException("I didn't quite catch that. Could you try again?");
                }
            } catch (KLBotException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private static void handleTaskAction(String userInput) throws KLBotException {
        int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
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
        }
    }

    private static void addTask(String userInput) throws KLBotException {
        Task task = parser.createTask(userInput);
        if (task != null) {
            taskList.addTask(task);
            ui.showTaskAdded(task.getDescription());
            saveTasksToFile();
        }
    }

    private static void deleteTask(String userInput) throws KLBotException {
        int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task task = taskList.getTask(taskIndex);
            taskList.removeTask(taskIndex);
            ui.showTaskRemoved(task.getDescription());
            saveTasksToFile();
        }
    }

    private static void loadTasksFromFile() {
        ArrayList<Task> loadedTasks = storage.loadTasksFromFile();
        taskList.addTasks(loadedTasks);
    }

    private static void saveTasksToFile() {
        storage.saveTasksToFile(taskList);
    }
}
