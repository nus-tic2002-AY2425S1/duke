package KLBot.Storage;

import KLBot.TaskList.Task;
import KLBot.TaskList.TaskList;
import KLBot.Ui.Ui;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private static final Ui ui = new Ui();
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasksToFile(TaskList taskList) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.getTask(i).toFileFormat()+ "\n");
            }
            System.out.println("Everything is saved! Your tasks are safe and sound. 😊");
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong while saving tasks to file. Please try again! 😟");
        }
    }

    public ArrayList<Task> loadTasksFromFile() {
        File file = new File(filePath);
        ArrayList<Task> taskList = new ArrayList<>();

        if (file.exists()) {
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    Task task = Task.fromFileFormat(line);
                    if (task != null) {
                        taskList.add(task);
                    }
                }
                if (!taskList.isEmpty()) {
                    System.out.println("Yay! I found some tasks you’ve saved earlier:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("\t" + (i + 1) + "." + taskList.get(i));
                    }
                    ui.printLine();
                }
            }catch (FileNotFoundException e) {
                System.out.println("Uh-oh, I couldn’t find your saved tasks. Maybe you don’t have any yet? 😊");
            }
        }else {
            System.out.println("File not found at " + file.getAbsolutePath());
        }

        return taskList;
    }
}
