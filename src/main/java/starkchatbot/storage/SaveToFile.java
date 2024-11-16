package starkchatbot.storage;

import starkchatbot.taskmanager.*;
import starkchatbot.userui.StarkException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SaveToFile {
    private final ArrayList<Task> tasks;
    private final String filePath;


    /**
     * Constructs a SaveToFile object with a list of tasks and the file path where the tasks will be saved.
     * If the file does not exist, it attempts to create a new file at the given path.
     *
     * @param taskList The list of tasks to be saved to the file.
     * @param filePath The path of the file where tasks will be saved.
     * @throws StarkException.FileNotFoundException If the file cannot be created or found.
     */
    public SaveToFile(ArrayList<Task> taskList, String filePath)
            throws StarkException.FileNotFoundException {
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


    /**
     * Writes the tasks to the file at the specified file path.
     * The task data is saved in a specific format based on the task type.
     *
     * @throws StarkException.WriteToFileException If an error occurs while writing to the file.
     */
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
                } else if (task.getClass() == TentativeTask.class) {
                    fileWriter.append("TE ]").append(task.getDescription()).append(" ]").append(task.getStatus()).append(" ]");
                }
                fileWriter.append(System.lineSeparator());
            }
            fileWriter.close();
            System.out.println("Tasks saved to the file successfully");
        } catch (IOException e) {
            throw new StarkException.WriteToFileException(e.getMessage());
        }
    }
}
