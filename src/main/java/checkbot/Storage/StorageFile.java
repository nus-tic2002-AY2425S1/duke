package checkbot.Storage;

import checkbot.Exception.*;
import checkbot.Task.Task;
import checkbot.Task.TaskList;
import checkbot.Ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageFile {
    public static File taskFile = new File("data/checkbot.txt");

    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(taskFile);
        fw.write(textToAdd);
        fw.close();
    }

    public static void updateFile() {
        String textToAdd = "";

        for (Task task : TaskList.tasks) {
            textToAdd = textToAdd.concat(task.getFileView() + System.lineSeparator());
        }

        try {
            writeToFile(textToAdd);
        } catch (IOException e) {
            System.out.println("Oops! Something wrong happened with saving of file.");
        }
    }

    public static void readFile(File taskFile) throws FileNotFoundException {
        Scanner scanFile = new Scanner(taskFile);
        List<String> taskList = new ArrayList<>();
        int taskCount = 0;

        while (scanFile.hasNextLine()) {
            taskList.add(scanFile.nextLine());
        }

        for (String task : taskList) {
            String[] taskArray = task.split("\\|");
            String taskCommand = switch (taskArray[0].trim()) {
                case "T" -> "Todo " + taskArray[2].trim();
                case "D" -> "Deadline " + taskArray[2].trim();
                case "E" -> "Event " + taskArray[2].trim();
                default -> {
                    // invalid task type, end the program
                    System.out.println("Invalid task type! Please check your txt file.");
                    throw new RuntimeException();
                }
            };

            try {
                if (TaskList.addTask(taskCommand)) {
                    // if task added successfully, set task status
                    if (taskArray[1].trim().equals("NOT_DONE")) {
                        TaskList.tasks.get(taskCount).setDone(false);
                    } else if (taskArray[1].trim().equals("DONE")) {
                        TaskList.tasks.get(taskCount).setDone(true);
                    } else {
                        System.out.println("Invalid task status! Please check your txt file.");
                        throw new RuntimeException();
                    }
                    // increase taskCount for next line in taskList
                    taskCount++;
                } else {
                    // command input is wrong, end the program
                    System.out.println("Invalid task! Please check your txt file.");
                    throw new RuntimeException();
                }
            } catch (EmptyTimeException e) {
                // empty time, end the program
                TextUi.printEmptyTime();
                System.out.println("Invalid input! Please check your txt file.");
                throw new RuntimeException();
            } catch (EmptyInputException e) {
                // empty task, end the program
                TextUi.printEmptyDescription();
                System.out.println("Invalid input! Please check your txt file.");
                throw new RuntimeException();
            }
        }
    }
}
