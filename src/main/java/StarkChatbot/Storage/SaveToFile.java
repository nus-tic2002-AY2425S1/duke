package StarkChatbot.Storage;

import StarkChatbot.TaskManager.*;
import StarkChatbot.UserUI.StarkException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.File;

public class SaveToFile {
    private ArrayList<Task> tasks;
    private String filePath;


    public SaveToFile(ArrayList<Task> taskList, String filePath) throws StarkException.FileNotFoundException {
        this.tasks = taskList;
        this.filePath = filePath;
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("File doesn't exist at the given path, Creating a new one");
            try {
                File file = new File(filePath);
                System.out.println("File created at: " + filePath);
            } catch (Exception e) {
                throw new StarkException.FileNotFoundException("Creating a new file failed");
            }
        }

    }

    public void writeToFile() throws StarkException.WriteToFileException {

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : tasks) {
                if (task.getClass() == Deadline.class) {
                    fileWriter.append("D ]").append(task.getDescription()).append(" ]").append(task.getStatus()).append(" ]");
                } else if (task.getClass() == Todo.class) {
                    fileWriter.append("T ]").append(task.getDescription()).append(" ]").append(task.getStatus()).append(" ]");
                } else if (task.getClass() == Event.class) {
                    fileWriter.append("E ]").append(task.getDescription()).append(" ]").append(task.getStatus()).append(" ]");
                }
                fileWriter.append(System.lineSeparator());
            }
            fileWriter.close();
            System.out.println("Tasks saved to the file successfully");
        } catch (IOException e) {
            throw new StarkException.WriteToFileException("Saving the task to \" " + filePath + " \" failed...");
        }
    }
}
