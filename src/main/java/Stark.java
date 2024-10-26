import StarkChatbot.Storage.SaveToFile;
import StarkChatbot.Storage.TaskStorage;
import StarkChatbot.TaskManager.Task;
import StarkChatbot.UserUI.CommandLineUI;
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


    public static void main(String[] args) {
    try {
        runChatBot();
    }catch (Exception e){
        System.out.println(e.getMessage());
    }
    }

}
