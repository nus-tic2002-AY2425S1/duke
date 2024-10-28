package checkbot.storage;

import checkbot.exception.EmptyInputException;
import checkbot.exception.EmptyTimeException;
import checkbot.task.Task;
import checkbot.task.TaskList;
import checkbot.task.TaskPriority;
import checkbot.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageFile {
    /** Storage file to store existing tasks */
    public static File taskFile = new File("data/checkbot.txt");

    /**
     * Overwrites storage file with string input.
     *
     * @param textToAdd All tasks in String format
     * @throws IOException IO exception when writing file
     */
    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fileWriter = new FileWriter(taskFile);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }

    /**
     * Captures current tasks in String format to overwrite storage file.
     */
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

    /**
     * Sets status and priority of a task when recovering tasks from storage.
     */
    public static void setTaskStatus(Task task, String status, String priority) {
        // set task status
        switch (status) {
        case "NOT DONE":
            task.setDone(false);
            break;
        case "DONE":
            task.setDone(true);
            break;
        default:
            System.out.println("Invalid task status! Please check your txt file.");
            throw new RuntimeException();
        }

        // set task priority
        switch (priority) {
        case "HIGH":
            task.setPriority(TaskPriority.HIGH);
            break;
        case "MEDIUM":
            task.setPriority(TaskPriority.MEDIUM);
            break;
        case "LOW":
            task.setPriority(TaskPriority.LOW);
            break;
        case "NOT SET":
            task.setPriority(TaskPriority.NOT_SET);
            break;
        default:
            System.out.println("Invalid task priority! Please check your txt file.");
            throw new RuntimeException();
        }
    }

    /**
     * Adds saved tasks in storage file into tasks.
     *
     * @param taskFile Storage file
     * @throws FileNotFoundException No storage file found
     */
    public static void recoverTasks(File taskFile) throws FileNotFoundException {
        Scanner scanFile = new Scanner(taskFile);
        List<String> storageTaskList = new ArrayList<>();

        while (scanFile.hasNextLine()) {
            storageTaskList.add(scanFile.nextLine());
        }

        for (String task : storageTaskList) {
            // Format: <task type> | <task status> | <task priority> | <description w/ datetime (if applicable)>
            String[] taskArray = task.split("\\|");

            // Convert stored tasks into UI command
            String taskCommand = switch (taskArray[0].trim()) {
                case "T" -> "Todo " + taskArray[3].trim();
                case "D" -> "Deadline " + taskArray[3].trim();
                case "E" -> "Event " + taskArray[3].trim();
                default -> {
                    // invalid task type, end the program
                    System.out.println("Invalid task type! Please check your txt file.");
                    throw new RuntimeException();
                }
            };

            // Add task using UI command, and set task status
            try {
                Task newTask = TaskList.addTask(taskCommand);
                setTaskStatus(newTask, taskArray[1].trim(), taskArray[2].trim());
            } catch (EmptyTimeException e) {
                // empty time in command, end the program
                System.out.println("There's no time indicated where necessary! Please check your txt file.");
                throw new RuntimeException();
            } catch (EmptyInputException e) {
                // empty task, end the program
                System.out.println("There's missing input! Please check your txt file.");
                throw new RuntimeException();
            } catch (DateTimeException e) {
                // invalid datetime indicated, end the program
                System.out.println("There's invalid time! Please check your txt file.");
                throw new RuntimeException();
            } catch (NumberFormatException e) {
                // datetime format is wrong, end the program
                System.out.println("Invalid datetime format! It should follow DD/MM/YYYY HHMM(24H). " +
                        "Please check your txt file.");
                throw new RuntimeException();
            }
        }

        // print list after adding all stored tasks
        TextUi.printTasks();
    }

    /**
     * Reads file from existing storage file.
     */
    public static void readFile() {
        try {
            StorageFile.recoverTasks(StorageFile.taskFile);
        } catch (FileNotFoundException e) {
            try {
                StorageFile.taskFile.getParentFile().mkdir();
                StorageFile.taskFile.createNewFile();
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
        }
    }
}
