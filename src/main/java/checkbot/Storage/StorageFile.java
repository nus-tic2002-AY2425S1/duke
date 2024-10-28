package checkbot.Storage;

import checkbot.Exception.*;
import checkbot.Task.Task;
import checkbot.Task.TaskList;
import checkbot.Task.TaskPriority;
import checkbot.Ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StorageFile {
    /**
     * Storage file to store existing tasks
     */
    public static File taskFile = new File("data/checkbot.txt");

    /**
     * Overwrites storage file with string input
     *
     * @param textToAdd tasks in String format
     * @throws IOException IO exception when writing file
     */
    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(taskFile);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Captures current taskList in String format to overwrite storage file
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
     * Add saved tasks in storage file into taskList ArrayList
     *
     * @param taskFile storage file
     * @throws FileNotFoundException No storage file found
     */
    public static void recoverTasks(File taskFile) throws FileNotFoundException {
        Scanner scanFile = new Scanner(taskFile);
        List<String> taskList = new ArrayList<>();
        int taskCount = 0;

        while (scanFile.hasNextLine()) {
            taskList.add(scanFile.nextLine());
        }

        for (String task : taskList) {
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

            // Add task using UI command
            try {
                if (TaskList.addTask(taskCommand)) {
                    // if task added successfully, set task status
                    switch (taskArray[1].trim()) {
                        case "NOT DONE":
                            TaskList.tasks.get(taskCount).setDone(false);
                            break;
                        case "DONE":
                            TaskList.tasks.get(taskCount).setDone(true);
                            break;
                        default:
                            System.out.println("Invalid task status! Please check your txt file.");
                            throw new RuntimeException();
                    }

                    // if task added successfully, set task priority
                    switch (taskArray[2].trim()) {
                        case "HIGH":
                            TaskList.tasks.get(taskCount).setPriority(TaskPriority.HIGH);
                            break;
                        case "MEDIUM":
                            TaskList.tasks.get(taskCount).setPriority(TaskPriority.MEDIUM);
                            break;
                        case "LOW":
                            TaskList.tasks.get(taskCount).setPriority(TaskPriority.LOW);
                            break;
                        case "NOT SET":
                            TaskList.tasks.get(taskCount).setPriority(TaskPriority.NOT_SET);
                            break;
                        default:
                            System.out.println("Invalid task priority! Please check your txt file.");
                            throw new RuntimeException();
                    }

                    // increase taskCount to handle next task in list
                    taskCount++;
                } else {
                    // task failed to be added - UI command is wrong, end the program
                    System.out.println("Invalid task command! Please check your txt file.");
                    throw new RuntimeException();
                }
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
                System.out.println("Invalid datetime format! It should follow DD/MM/YYYY HHMM(24H). Please check your txt file.");
                throw new RuntimeException();
            }
        }

        // print list after adding all stored tasks
        TextUi.printTasks();
    }

    public static void readFile() {
        // read file from existing file and add into tasks
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
