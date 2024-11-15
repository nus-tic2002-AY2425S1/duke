package jibberJabber.tasks;

import jibberJabber.commands.ExceptionHandling;
import jibberJabber.commands.KeywordHandling;
import jibberJabber.ui.Message;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TaskFiles {
    private final String filePath;

    public TaskFiles(String filePath) {
        this.filePath = filePath;
    }
    public TaskList extractTasksFromFile() {
        TaskList todoTaskList = new TaskList();
        int itemIndex = 0;
        String relativePath = System.getProperty("user.dir") + File.separator + "data/tasks.txt";
        TaskFiles taskFiles = new TaskFiles(relativePath);
        File file = new File(relativePath);
        try {
            Scanner scanner = new Scanner(file);
            if (taskFiles.fileExists()) {
                while (scanner.hasNext()) {
                    // Keep track of the index of the added item from the list
                    itemIndex ++;
                    KeywordHandling keywordHandling = new KeywordHandling();
                    String task = scanner.nextLine().trim();
                    boolean isTaskCompleted = task.contains("[X]");
                    char keywordCharacter = task.charAt(1);
                    task = switch (keywordCharacter) {
                        case 'T' -> task.replaceAll("\\[T]\\[([ X])]", "todo").trim();
                        case 'D' -> task.replaceAll("\\[D]\\[([ X])]", "deadline").replaceAll("\\(by:", "/by").trim();
                        default ->  task.replaceAll("\\[E]\\[([ X])]", "event").replaceAll("\\(from:", "/from").replaceAll("to:", "/to").trim();
                    };
                    // Happens after first attempt to load file, so manually remove additional closing parenthesis
                    task = task.replaceAll("\\)+$", "");
                    String keyword = task.split(" ")[0].trim();
                    Task.addTask(todoTaskList, task, keyword, keywordHandling, true);
                    if (isTaskCompleted) {
                        keywordHandling.processMarkKeyword(todoTaskList, Integer.toString(itemIndex), true, taskFiles, true);
                    }
                }
            } else {
                taskFiles.createFile();
                return new TaskList();
            }
        } catch (FileNotFoundException e) {
            taskFiles.createFile();
            return new TaskList();
        }
        return todoTaskList;
    }
    private boolean createDirectory(){
        String parentDirectory = new File(filePath).getParent();
        File directory = new File(parentDirectory);
        // Check if the parent directory folder is created
        if (!directory.exists()) {
            return directory.mkdirs();
        }
        return true;
    }
    protected void createFile() {
        if (!createDirectory()) {
            Message.printFailedDirectoryCreation();
            return;
        }
        File file = new File(filePath);
        if (!fileExists() & !ExceptionHandling.isFileCreated(file)) {
            Message.printFailedToCreateFile();
        }
    }
    public void writeToTextFile(TaskList todoTaskList, Task task, boolean isAppend) {
        try (FileWriter fw = new FileWriter(filePath, isAppend)) {
            // Append to list via terminal
            if (isAppend && task != null) {
                fw.write(task.printAddedTask() + "\n");
            } else {
                // Reads from the file and add
                for (Task i : todoTaskList.getTasks()) {
                    fw.write(i.printAddedTask() + "\n");
                }
            }
        } catch (IOException e) {
            Message.printFailedToAppendToFile();
        }
    }
    public boolean fileExists() {
        File file = new File(filePath);
        return file.exists();
    }
}
