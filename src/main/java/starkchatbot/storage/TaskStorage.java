package starkchatbot.storage;

import starkchatbot.taskmanager.Task;
import starkchatbot.userui.StarkException;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Handles storage and retrieval of tasks from a file.
 * Reads tasks from the specified file and updates the task list.
 */
public class TaskStorage {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final String fileName;


    /**
     * Creates a TaskStorage instance to manage tasks stored in the given file.
     *
     * @param fileName the name of the file used for store tasks in the file.
     */
    public TaskStorage(String fileName) {
        this.fileName = fileName;
    }


    /**
     * Reads tasks from the file, parses them into Task objects, and updates the task list.
     * Skips empty or invalid lines in the file.
     *
     * @return the updated list of tasks read from the file.
     * @throws StarkException.InvalidDescriptionException if the file is not found or contains invalid task descriptions.
     */
    public ArrayList<Task> updateTaskList() {
        try {
            ReadFromFile readFromFile = new ReadFromFile(fileName);
            ArrayList<String> taskDetail = readFromFile.readTasks();
            for (String task : taskDetail) {
                if (task.isEmpty()) {
                    continue;
                }
                Task readTask = TaskParser.parseTasks(task);

                if (readTask == null) {
                    continue;
                }
                tasks.add(readTask);
            }
            return tasks;
        } catch (StarkException.InvalidDescriptionException | FileNotFoundException e) {
            throw new StarkException.InvalidDescriptionException(e.getMessage());

        }

    }


}
