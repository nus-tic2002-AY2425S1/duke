import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveTask {
    private static final String FILE = "./data/duke.txt";

    // save all the task in the txt file
    public static void saveTasks (ArrayList<Task> taskList){
        try{
            File file = new File(FILE);
            // check if file exist else create the directory file
            if (!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }

            FileWriter writer = new FileWriter(FILE);
            for(Task task : taskList){
                writer.write(task.toFileString()+System.lineSeparator());
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Load all the task from the task File
    public static ArrayList<Task> loadTasks(){
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(FILE);

        // If there is no file or file does not exist. return empty list
        if (!file.exists()){
            System.out.println("No task file found, starting with empty task list. " );
            return taskList;
        }

        try{
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] taskDetail = line.split(" \\| ");
                Task task;

                switch (taskDetail[0]){
                    case "T":
                        task = new ToDo(taskDetail[2]);
                        break;
                    case "D":
                        task = new Deadline(taskDetail[2],taskDetail[3]);
                        break;
                    case "E":
                        task = new Event(taskDetail[2],taskDetail[3],taskDetail[4]);
                    default:
                        throw new ErrorException("Invalid task type in the file.");
                }

                // mark task as done if it's marked in the file
                if (taskDetail[1].equals("1")){
                    task.markAsDone();
                }
                taskList.add(task);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Task file not found.");
        }
        catch(Exception e){
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return taskList;
    }
}
