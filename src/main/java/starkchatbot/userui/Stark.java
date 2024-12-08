package starkchatbot.userui;

import starkchatbot.storage.SaveToFile;
import starkchatbot.storage.TaskStorage;
import starkchatbot.taskmanager.Task;

import java.util.ArrayList;


public class Stark {

    private static final String fileLocation = "TaskLists.txt";

    public Stark() {
    }

    public static void runChatBot() {

        try {
            TaskStorage taskStorage = new TaskStorage(fileLocation);
            ArrayList<Task> storedTasks = taskStorage.updateTaskList();
            CommandLineUI userUi = new CommandLineUI(storedTasks);
            ArrayList<Task> updatedTasks = userUi.readUserInputs();
            new SaveToFile(updatedTasks, fileLocation).writeToFile();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("ChatBot closed");
        }
    }

    public static void main(String[] args) {

        try {
            runChatBot();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
