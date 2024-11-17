package KLBot.Storage;

import KLBot.TaskList.Task;
import KLBot.TaskList.TaskList;
import KLBot.Ui.Ui;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String directoryPath;
    private String filePath;
    private static final Ui ui = new Ui();
    public Storage(String directoryPath,String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    /**
     * Creates the directory and any necessary parent directories if they don't exist.
     *
     * @param directory The directory to create.
     * @return true if the directory is created or already exists, false otherwise.
     */
    public static boolean createDirectoryIfNeeded(File directory) {
        if (!directory.exists()) {
            return directory.mkdirs();
        }
        return false;
    }

    /**
     * Creates the file if it doesn't exist and prints the corresponding message.
     *
     * @param file The file to create.
     * @throws IOException If an error occurs while creating the file.
     */
    public static void createFileIfNeeded(File file) throws IOException {
        if (!file.exists()) {
            System.out.println("Oops! It looks like the file to save your list doesn't exist yet. I'll create it for you...");
            if (file.createNewFile()) {
                System.out.println("Yay! The file has been created successfully.");
            } else {
                System.out.println("Uh-oh! Something went wrong and I couldn't create the file.");
            }
        }
    }
    public void saveTasksToFile(TaskList taskList) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.getTask(i).toFileFormat()+ "\n");
            }
            System.out.println("Everything is saved! Your tasks are safe and sound.");
        } catch (IOException e) {
            System.out.println("Oops! Something went wrong while saving tasks to file. Please try again! 😟");
        }
    }

    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        File directory = new File(directoryPath);
        File file = new File(filePath);

        try {
            if (createDirectoryIfNeeded(directory)) {
                createFileIfNeeded(file);
            }
        } catch (IOException e) {
            System.out.println("Error occurred while creating the file: " + e.getMessage());
        }

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
                    System.out.println("Yay! I found some tasks was saved earlier:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("\t" + (i + 1) + "." + taskList.get(i));
                    }
                    ui.printLine();
                }
            }catch (FileNotFoundException e) {
                System.out.println("Uh-oh, I couldn’t find your saved tasks. Maybe you don’t have any yet?");
            }
        }else {
            System.out.println("File not found at " + file.getAbsolutePath());
        }

        return taskList;
    }
}
