import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

// https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java
public class StorageFile {
    // Default file path used if the user doesn't provide the file name.
    private static final String DEFAULT_STORAGE_FILEPATH = "./data/tasks.txt";
    protected final Path filePath;

    public StorageFile(Path filePath) {
        this.filePath = filePath;
        checkDataFolderExists(filePath);
        checkTaskFileExists(filePath);
    }

    public StorageFile() {
        // filePath = Paths.get(DEFAULT_STORAGE_FILEPATH);
        this(Paths.get(DEFAULT_STORAGE_FILEPATH));
    }


    // Your code must handle (i.e., if the file is missing, your code must create it) 
    // the case where the data file doesn't exist at the start. Reason: when someone else 
    // takes your chatbot and runs it for the first time, the required file will not exist 
    // in their computer. Similarly, if you expect the data file to be in a specific folder 
    // (e.g., ./data/), you must also handle the folder-does-not-exist-yet case.
    public static void checkDataFolderExists(Path filePath) {
        // https://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists
        // Check if "data" directory exists in current folder 
        Path dataFolderPath = filePath.getParent();
        // System.out.println(dataFolderPath);
        // boolean isDataFolderExists = Files.exists(dataFolderPath) && Files.isDirectory(dataFolderPath);
        File dataFolder = new File(dataFolderPath.toString());
        boolean isDataFolderExists = dataFolder.exists() && dataFolder.isDirectory();

        try {
            // Create the directory if it does not exist
            if (!isDataFolderExists) {
                // https://tutorialspoint.com/java/java_directories.htm
                // The mkdir() method creates a directory, returning true on success and false on failure. Failure indicates that the path specified in the File object already exists, or that the directory cannot be created because the entire path does not exist yet.
                boolean isDataFolderCreated = dataFolder.mkdir();
                if (isDataFolderCreated) {
                    System.out.println("Data directory created");
                } else {
                    throw new IOException("An error occurred while creating the data directory");
                }
            }
        } catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }

    }

    public static void checkTaskFileExists(Path filePath) {
        File tasksFile = filePath.toFile();
        // File tasksFile = new File(filePath.toString());
        boolean isTaskFileExists = tasksFile.exists();
        
        try {
            if (!isTaskFileExists) {
                boolean isFileCreated = tasksFile.createNewFile();
                if (isFileCreated) {
                    System.out.println("Task file created");
                } else {
                    throw new IOException("An error occurred while creating the task file");
                }
            }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    // Write all lines in tasks.txt into ArrayList
    // TODO: Handle the situation of the data file being corrupted (i.e., content not in the expected format).
    public static ArrayList<Task> loadTasks() throws StorageFileException {
        ArrayList<Task> taskList = new ArrayList<>();
        StorageFile file = new StorageFile();

        // https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
        try {
			Scanner scanner = new Scanner(file.filePath.toFile());

			while (scanner.hasNextLine()) {

                String rawInput = scanner.nextLine().trim();

                // Check for empty line
                if (rawInput.isEmpty()) {
                    throw new StorageFileException("Empty line found. Please ensure that all lines in tasks.txt contain valid data.");
                }
                
                // System.out.println(scanner.nextLine());
                String[] taskData = rawInput.split(" \\| ");
                // System.out.println("taskData: " + Arrays.toString(taskData));

                // [T] --> taskData.length = 1
                // [T, 1, read book]

                if (taskData.length < 3) {
                    throw new StorageFileException("Task data has missing components. Please check tasks.txt. Expected format: [T|D|E] | [0|1] | description [| additional info]. Found: " + rawInput);
                }

                Task task = null;

                // for (int i = 0; i < line.length; i++) {
                //     System.out.println(line[i]);
                // }
                
                // taskType is either "T" or "D" or "E"

                String taskType;
                boolean isDone;
                String description;

                /*
                 * T|0|read book
                 * T |0|read book
                 * T|0 |read book
                 * T | 0|read book
                 * T |0|read book
                 */
                
                /* 
                try {   
                    taskType = line[0];
                    System.out.println("taskType: " + taskType);

                    // if (taskType.contains("|")) {
                    //     throw new StorageFileException("Task type contains |");
                    // }
                } catch (StorageFileException e) {
                    throw new StorageFileException("Invalid task format. Please ensure that each line in tasks.txt is split by \" | \"");
                }
                */

                taskType = taskData[0];
                
                // if (line.length < 2) {
                //     throw new StorageFileException("Invalid task format: " + line);
                // }
                
                try {
                    isDone = Boolean.valueOf(taskData[1]);
                    if (Integer.parseInt(taskData[1]) == 1) {
                        isDone = true;
                    }
                } catch (NumberFormatException e) {
                    throw new StorageFileException("Invalid completion status found. Expected 0 or 1, but found: " + taskData[1]);
                }

                description = taskData[2];

                // System.out.println("taskType: " + taskType + "\n" + "isDone: " + isDone  + "\n" + "description: " + description);

                /*
                1.[T][X] read book
                2.[D][X] return book (by: June 6th)
                3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
                4.[T][X] join sports club
                5.[T][ ] borrow book 
                
                T | 1 | read book
                D | 1 | return book | June 6th
                E | 0 | project meeting | Aug 6th 2pm | 4pm
                T | 1 | join sports club
                T | 0 | borrow book
                */
                
                switch (taskType) {
                    case "T":
                        task = new Todo(description, isDone);
                        break;
                    case "D":
                        if (taskData.length < 4) {
                            throw new StorageFileException("Invalid Deadline format");
                        }
                        String due = taskData[3];
                        task = new Deadline(description, isDone, due);
                        break;
                    case "E":
                        if (taskData.length < 5) {
                            throw new StorageFileException("Invalid Event format");
                        }
                        String start = taskData[3];
                        String end = taskData[4];
                        task = new Event(description, isDone, start, end);
                        break;
                    default:
                        throw new StorageFileException("Invalid task type: " + taskType);
                }

                /* 
                try {
                    switch (taskType) {
                        case "T":
                            // System.out.println("in case T: " + Arrays.toString(taskData));
                            task = new Todo(description, isDone);
                            break;
                        case "D":
                            // System.out.println("in case D: " + Arrays.toString(taskData));
                            if (taskData.length < 4) {
                                throw new StorageFileException("Invalid Deadline format");
                            }
                            String due = taskData[3];

                            // try {
                            //     if (taskData.length < 4) {
                            //         throw new StorageFileException("Invalid Deadline format");
                            //     }
                            //     String due = taskData[3];
                            // } catch (StorageFileException e) {
                            // }

                            // if (taskData.length < 4) {
                            //     throw new StorageFileException("Invalid Deadline format.");
                            // }
                            // if (line.length < 3) {
                            //     throw new StorageFileException("Invalid Deadline format: " + line);
                            // }

                            // String due = taskData[3];
                            task = new Deadline(description, isDone, due);
                            break;
                        case "E":
                            System.out.println("in case T: " + Arrays.toString(taskData));
                            // System.out.println("In event, line.length is " + line.length);
                            // if (line.length < 4) {
                            //     throw new StorageFileException("Invalid Event format: " + line.toString());
                            // }
                            String start = taskData[3];
                            String end = taskData[4];
                            task = new Event(description, isDone, start, end);
                            break;
                        default:
                            throw new StorageFileException("Invalid task type " + taskType + ". Please ensure that the task type is \"T\", \"D\" or \"E\"");
                    }
    
                    // if (taskType.equals("T")) {
                    //     task = new Todo(description, isDone);
                    // } else if (taskType.equals("D")) {
                    //     String due = line[3];
                    //     task = new Deadline(description, isDone, due);
                    // } else if (taskType.equals("E")) {
                    //     String start = line[3];
                    //     String end = line[4];
                    //     task = new Event(description, isDone, start, end);
                    // }
                    
                    taskList.add(task);
                } catch (Exception e) {
                    throw new StorageFileException("Error creating task from line: " + Arrays.toString(taskData) + "\n" + e);
                }
                */

                // Task task = line.toString();
                // System.out.println("this is task : " + task);
			}
			
            scanner.close();
            return taskList;

		} catch (FileNotFoundException e) {
			// e.printStackTrace();
            throw new StorageFileException("Task file not found: " + e.getMessage());
		} 
    }

    // Save the tasks in the hard disk automatically whenever the task list changes. Load the data from the hard disk when the chatbot starts up.
    public static void save(ArrayList<Task> taskList) {
        try {
            FileWriter fw = new FileWriter(DEFAULT_STORAGE_FILEPATH);

            for (int i = 0; i < taskList.size(); i++) {
                Task currentTask = taskList.get(i);
                // System.out.println("currentTask " + currentTask);
                // System.out.println("task: " + currentTask);

                String encodedTask = currentTask.encodeTask();
                
            }

            // for (int i = 0; i < taskList.size(); i++) {
            //     String task = taskList.get(i).toString();
            //     fw.write(task);
            // }
            
            fw.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    // For writing new task to file
    public void writeToFile(String text) {
        try {
            FileWriter fw = new FileWriter(filePath.toString(), true);
            fw.write(text + System.lineSeparator());
            fw.close();
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

    public void markTask(ArrayList<Task> taskList, int taskIndex) {
        try {
            Task originalTask = taskList.get(taskIndex);
            // System.out.println(taskList.get(taskIndex));
            String originalTaskEncoded = originalTask.encodeTask();
            // System.out.println("encoded: " + originalTaskEncoded);

            List<String> lines = Files.readAllLines(filePath);
            // Replace the line if the line number is valid
            if (taskIndex >= 0 && taskIndex < lines.size()) {
                lines.set(taskIndex, originalTaskEncoded);
            } else {
                throw new IllegalArgumentException("Line number is out of range.");
            }

            // Write the modified lines back to the file
            Files.write(filePath, lines);


            // System.out.println("indexToMark is " + Integer.valueOf(taskIndex));
            
            // System.out.println(filePath);
            // List<String> lines = Files.readAllLines(filePath);
            // System.out.println("test");
            
            // if (Files.size(filePath) == 0) {
            //     System.out.println("The file is empty.");
            //     return;
            // }
            
            // // System.out.println("Number of lines read: " + lines.size());

            // Task originalTask = taskList.get(taskIndex);
            // // System.out.println(taskList.get(taskIndex));
            // String originalTaskEncoded = originalTask.encodeTask();
            // System.out.println("encoded: " + originalTaskEncoded);
            
            // for (int i = 0; i < lines.size(); i++) {
            //     String line = lines.get(i);
            //     System.out.println(line);
            //     if (line.equals(originalTaskEncoded)) {
            //         System.out.println("Found the task to mark.");
            //     }
            // }


            // for (String line : lines) {
            //     System.out.println(line);
			// }


            // FileWriter fw = new FileWriter(filePath.toString());


            // for (int i = 0; i < taskList.size(); i++) {
            //     Task currentTask = taskList.get(i);
            //     System.out.println("currentTask " + currentTask);
            //     System.out.println("task: " + task);
            //     if (currentTask == task) {
            //         // fw.write(task);
            //     }
            // }
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }

}



/*
 * for 'list' command, maybe can just read from file directly
 * 
 * Commands that will change the task list:
 * 1. add todo / event / deadline --> write to file directly, instead of adding to task list
 * 2. mark task as done / undone --> 
 * 3. delete
 */