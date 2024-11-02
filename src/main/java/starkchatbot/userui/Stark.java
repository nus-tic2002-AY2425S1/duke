package starkchatbot.userui;

import starkchatbot.storage.SaveToFile;
import starkchatbot.storage.TaskStorage;
import starkchatbot.taskmanager.DateTimeParser;
import starkchatbot.taskmanager.Task;

import java.util.ArrayList;


public class Stark {

    private static String fileLocation = "src/Data/TaskLists.txt";

    public static void runChatBot(){
        try {
            ArrayList<Task> storedTasks = new TaskStorage(fileLocation).updateTaskList();

            CommandLineUI userUi = new CommandLineUI(storedTasks);

            ArrayList<Task> updatedTasks = userUi.readUserInputs();

            new SaveToFile(updatedTasks, fileLocation).writeToFile();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            System.out.println("ChatBot closed");
        }
    }

    public Stark(String fileLocation){
        this.fileLocation = fileLocation;
    }

    public static void main(String[] args) {

    try {
        Stark.runChatBot();
//        DateTimeParser.parseDateTime("2001-09-08 1813");
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
    }

}
