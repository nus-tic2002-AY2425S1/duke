// import java.io.File;
// import java.io.FileNotFoundException;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Scanner;
// import java.util.Arrays;

// // https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java

// /*
// Summary from https://stackoverflow.com/questions/8101585/cannot-make-a-static-reference-to-the-non-static-field
// A static method cannot refer to a non-static variable. 
// A non-static variable has meaning only when it is referred through an object reference, e.g. file1.path, file2.path
// A non-static variable has no meaning when it is referred through class, e.g. file.path
// */

// public class StorageFile {
//     // Default file path used if the user doesn't provide the file name.
//     private static final String DEFAULT_STORAGE_FILEPATH = "./data/tasks.txt";
//     protected Path filePath;

//     public StorageFile() throws IOException {
//         Path filePath = Paths.get(DEFAULT_STORAGE_FILEPATH);
//         this.filePath = filePath;
//         checkDataFolderExists();
//         checkTaskFileExists();
//     }

//     public Path getFilePath() {
//         return filePath;
//     }

//     public File getFile() {
//         return getFilePath().toFile();
//     }

//     // Your code must handle (i.e., if the file is missing, your code must create it) 
//     // the case where the data file doesn't exist at the start. Reason: when someone else 
//     // takes your chatbot and runs it for the first time, the required file will not exist 
//     // in their computer. Similarly, if you expect the data file to be in a specific folder 
//     // (e.g., ./data/), you must also handle the folder-does-not-exist-yet case.
//     public void checkDataFolderExists() throws IOException {
//         // https://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists
//         // Check if "data" directory exists in current folder 
//         Path dataFolderPath = getFilePath().getParent();
//         // System.out.println(dataFolderPath);
//         // boolean isDataFolderExists = Files.exists(dataFolderPath) && Files.isDirectory(dataFolderPath);
//         File dataFolder = new File(dataFolderPath.toString());
//         boolean isDataFolderExists = dataFolder.exists() && dataFolder.isDirectory();

//         // Create the directory if it does not exist
//         if (!isDataFolderExists) {
//             // https://tutorialspoint.com/java/java_directories.htm
//             // The mkdir() method creates a directory, returning true on success and false on failure. Failure indicates that the path specified in the File object already exists, or that the directory cannot be created because the entire path does not exist yet.
//             boolean isDataFolderCreated = dataFolder.mkdir();
//             if (!isDataFolderCreated) {
//                 throw new IOException(Messages.ERROR_CREATE_FOLDER_PRE);
//             }
//         }
        
//     }

//     public void checkTaskFileExists() throws IOException {
//         File tasksFile = getFile();
//         // File tasksFile = new File(filePath.toString());
//         boolean isTaskFileExists = tasksFile.exists();
        
//         if (!isTaskFileExists) {
//             boolean isFileCreated = tasksFile.createNewFile();
//             if (!isFileCreated) {
//                 throw new IOException(Messages.ERROR_CREATE_FILE_PRE);
//             }
//         }
//     }

//     // Write all lines in tasks.txt into ArrayList
//     // TODO: Handle the situation of the data file being corrupted (i.e., content not in the expected format).
//     public ArrayList<Task> loadTasks() throws StorageFileException {
//         ArrayList<Task> taskList = new ArrayList<>();

//         // System.out.println("in loadTasks");
//         // https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
//         try {
// 			Scanner scanner = new Scanner(getFile());

// 			while (scanner.hasNextLine()) {

//                 String rawInput = scanner.nextLine().trim();
//                 System.out.println(rawInput);
//                 // Check for empty line
//                 if (rawInput.isEmpty()) {
//                     throw new StorageFileException(
//                         Messages.MESSAGE_EMPTY_LINE,
//                         String.format("%s %s %s", Messages.MESSAGE_INVALID_TASKS_DATA_PRE, 
//                                       getFilePath().toString(), Messages.MESSAGE_INVALID_TASKS_DATA_POST)
//                     );
//                 }
                
//                 String[] taskData = rawInput.split(" \\| ");

//                 final String EXPECTED_FORMAT_TODO = "T | 0 | <task description>";
//                 final String EXPECTED_FORMAT_DEADLINE = "D | 0 | <task description> | <task deadline>";
//                 final String EXPECTED_FORMAT_EVENT = "E | 0 | <task description> | <event start date/time> | <event end date/time>";

//                 if (taskData.length < 3) {
//                     // "Task data has missing components. Please check tasks.txt. Expected format: [T|D|E] | [0|1] | description [| additional info]. Found: " + rawInput
//                     throw new StorageFileException(
//                         String.format("%s %s %s %s", Messages.MESSAGE_TASK_MISSING_COMPONENTS, Messages.MESSAGE_INVALID_TASKS_DATA_PRE, 
//                                       getFilePath().toString(), Messages.MESSAGE_INVALID_TASKS_DATA_POST),
//                         String.format("Received `%s`", rawInput),
//                         String.format("Expected format: `%s` or `%s` or `%s`", EXPECTED_FORMAT_TODO, 
//                                       EXPECTED_FORMAT_DEADLINE, EXPECTED_FORMAT_EVENT)
//                     );
//                 }

//                 Task task = null;

//                 String taskType;
//                 boolean isDone;
//                 String description;

//                 taskType = taskData[0];
                
//                 final String MESSAGE_INVALID_COMPLETION_STATUS = "Task has invalid completion status";
//                 final String VALID_COMPLETION_STATUS = "`1` or `0`";

//                 try {
//                     isDone = Boolean.valueOf(taskData[1]);
//                     if (Integer.parseInt(taskData[1]) == 1) {
//                         isDone = true;
//                     }
//                 } catch (NumberFormatException e) {
//                     // "Invalid completion status found. Expected 0 or 1, but found: " + taskData[1]
//                     throw new StorageFileException(
//                         MESSAGE_INVALID_COMPLETION_STATUS,
//                         String.format("Received `%s`", taskData[1]),
//                         String.format("Expected %s", VALID_COMPLETION_STATUS)
//                     );
//                 }

//                 description = taskData[2];
                
//                 switch (taskType) {
//                     case "T":
//                         task = new Todo(description, isDone);
//                         break;
//                     case "D":
//                         if (taskData.length < 4) {
//                             throw new StorageFileException("Invalid Deadline format");
//                         }
//                         String due = taskData[3];
//                         task = new Deadline(description, isDone, due);
//                         break;
//                     case "E":
//                         if (taskData.length < 5) {
//                             throw new StorageFileException("Invalid Event format");
//                         }
//                         String start = taskData[3];
//                         String end = taskData[4];
//                         task = new Event(description, isDone, start, end);
//                         break;
//                     default:
//                         throw new StorageFileException("Invalid task type: " + taskType);
//                 }

//                 taskList.add(task);
// 			}
			
//             scanner.close();
//             return taskList;

// 		} catch (FileNotFoundException e) {
// 			// e.printStackTrace();
//             throw new StorageFileException("Task file not found: " + e.getMessage());
// 		} 
//     }

//     public List<String> getAllLines() throws IOException {
//         Path filePath = getFilePath();
//         List<String> lines = null;
//         try {
//             lines = Files.readAllLines(filePath);
//         } catch (IOException e) {
//             throw e;
//         }
//         return lines;
//     }

//     public void removeLine(Task taskToDelete) throws IOException {
//         // Read all lines from the file
//         List<String> lines = getAllLines();
        
        
//         for (String line : lines) {

//         }
//     }

//     // Save the tasks in the hard disk automatically whenever the task list changes. Load the data from the hard disk when the chatbot starts up.
//     public void save(ArrayList<Task> taskList) {
//         try {
//             FileWriter fw = new FileWriter(getFilePath().toString());

//             for (int i = 0; i < taskList.size(); i++) {
//                 Task currentTask = taskList.get(i);
//                 // System.out.println("currentTask " + currentTask);
//                 // System.out.println("task: " + currentTask);

//                 String encodedTask = currentTask.encodeTask();
//                 fw.write(encodedTask + "\n");
//             }

//             // for (int i = 0; i < taskList.size(); i++) {
//             //     String task = taskList.get(i).toString();
//             //     fw.write(task);
//             // }
            
//             fw.close();
//         } catch (IOException ioException) {
//             System.out.println(ioException.getMessage());
//         }
//     }

//     // For writing new task to file
//     public void writeToFile(String text) {
//         try {
//             FileWriter fw = new FileWriter(filePath.toString(), true);
//             fw.write("\n" + text.trim());
//             fw.close();
//         } catch (IOException ioException) {
//             System.out.println(ioException.getMessage());
//         }
//     }

//     public void markTask(ArrayList<Task> taskList, int taskIndex) {
//         try {
//             Task originalTask = taskList.get(taskIndex);
//             // System.out.println(taskList.get(taskIndex));
//             String originalTaskEncoded = originalTask.encodeTask();
//             // System.out.println("encoded: " + originalTaskEncoded);

//             List<String> lines = Files.readAllLines(filePath);
//             // Replace the line if the line number is valid
//             if (taskIndex >= 0 && taskIndex < lines.size()) {
//                 lines.set(taskIndex, originalTaskEncoded);
//             } else {
//                 throw new IllegalArgumentException("Line number is out of range.");
//             }

//             // Write the modified lines back to the file
//             Files.write(filePath, lines);

//         } catch (IOException ioException) {
//             System.out.println(ioException.getMessage());
//         }
//     }

// }
