package Storage;

import TaskManager.Task;
import UserUI.StarkException;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class TaskStorage {
    private ArrayList<Task> tasks = new ArrayList<>();
    private String fileName;


    public TaskStorage(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Task> updateTaskList() throws FileNotFoundException {
        try {
            ReadFromFile readFromFile = new ReadFromFile(fileName);
            ArrayList<String> taskDetail = readFromFile.readTasks();
            for (String task : taskDetail) {
                tasks.add(TaskParser.parseTasks(task));
            }
            return tasks;
        } catch (StarkException.InvalidDescriptionException e) {
            throw new StarkException.InvalidDescriptionException(e.getMessage());
        }

    }


}
