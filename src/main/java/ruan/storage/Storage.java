package ruan.storage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import ruan.task.Task;
import ruan.exception.*;

/**
 * with help of ChatGPT on how to read & write to file
 */

public class Storage {
    
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws RuanException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        //scan file and get tasks info
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Task.fromFileFormat(line);
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new RuanException(ErrorType.FAIL_TO_LOAD_FILE);
        }

        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws RuanException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        // create directory when parent directory is not found
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        //write tasks to file
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuanException(ErrorType.FAIL_TO_SAVE);
        }
    }

}
