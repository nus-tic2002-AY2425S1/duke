package storage;

import common.Messages;
import exception.CommandException;
import exception.FileContentException;
import exception.StorageOperationException;
import exception.TaskListDecoderException;
import task.TaskList;
import task.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

// Reference: https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java

/**
 * Represents the storage file used to manage storage of tasks data.
 * Handles the creation, verification, and management of the task file and its directory {@code data}.
 * Deals with loading tasks from the task file and saving tasks in the task file.
 */
public class Storage {

//        System.out.println("java.class.path: " + System.getProperty("java.class.path"));
//        System.out.println("user.dir: " + System.getProperty("user.dir") + "/data/tasks.txt");
//        System.out.println("user.home: " + System.getProperty("user.home"));
//        System.out.println("storage: " + System.getProperty("storage"));

//    private static final String BASE_FILEPATH = System.getProperty("user.dir") + "/data/";

    // Default file path used if the user doesn't provide the file name.
//    private static final String DEFAULT_STORAGE_FILEPATH = BASE_FILEPATH + "tasks.txt";
//    private static final String DEFAULT_STORAGE_FILEPATH = "data/tasks.txt";
//    private static final String DEFAULT_STORAGE_FILEPATH = "./src/main/java/data/tasks.txt";
//    private static final String DEFAULT_STORAGE_FILEPATH = "../data/tasks.txt";
    private static final String DEFAULT_STORAGE_FILEPATH = System.getProperty("storage.file", "./data/tasks.txt");

//    private static final String ARCHIVE_STORAGE_FILEPATH = "../data/archive.txt";
//    private static final String ARCHIVE_STORAGE_FILEPATH = BASE_FILEPATH + "archive.txt";
    private static final String ARCHIVE_STORAGE_FILEPATH = System.getProperty("storage.file", "./data/archive.txt");
    private final Path tasksFilePath;
    private final Path archiveTasksFilePath;

    /**
     * Constructs an instance of Storage and initializes the file path to the default value.
     * The constructor checks for the existence of the data folder and task file, and creates them if they don't exist.
     *
     * @throws StorageOperationException if an error occurs while creating the data folder or task file.
     */
    public Storage() throws StorageOperationException {
//        System.out.println("java.class.path: " + System.getProperty("java.class.path"));
//        System.out.println("project root: " + System.getProperty("user.dir"));
//        System.out.println("user.dir: " + System.getProperty("user.dir") + "/data/tasks.txt");
//        System.out.println("user.home: " + System.getProperty("user.home"));
//        System.out.println("storage: " + System.getProperty("storage.file"));
//        System.out.println("test: " + Paths.get("src", "main", "java", "data"));

        this.tasksFilePath = Paths.get(DEFAULT_STORAGE_FILEPATH);
        this.archiveTasksFilePath = Paths.get(ARCHIVE_STORAGE_FILEPATH);
        checkDataFolderExists();
        checkTaskFileExists();
        checkArchiveFileExists();
    }

    /**
     * Retrieves the file path for the storage, i.e. the task file.
     *
     * @return the file path of the task file as a Path object.
     */
    public Path getTasksFilePath() {
        return tasksFilePath;
    }

    public Path getArchiveTasksFilePath() {
        return archiveTasksFilePath;
    }

    /**
     * Retrieves the task file associated with the storage.
     *
     * @return the file object of the task file as a File object
     */
    public File getTasksFile() {
        return getTasksFilePath().toFile();
    }

    public File getArchiveTasksFile() {
        return getArchiveTasksFilePath().toFile();
    }

    /**
     * Checks if the data folder exists. Handles missing data folder by creating it.
     * The data folder is expected to be in the `/data` directory. It must match the file path exactly.
     *
     * @throws StorageOperationException if an error occurs while checking or creating the data folder,
     *                                   e.g. folder cannot be created
     */
    public void checkDataFolderExists() throws StorageOperationException {
        // https://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists
        // Check if "data" directory exists in current folder 
        Path dataFolderPath = getTasksFilePath().getParent();
        String dataFolderPathString = dataFolderPath.toString();
        File dataFolder = new File(dataFolderPathString);
        boolean isDataFolderExists = dataFolder.exists() && dataFolder.isDirectory();

        // Create the directory if it does not exist
        if (!isDataFolderExists) {
            // https://tutorialspoint.com/java/java_directories.htm
            // The mkdir() method creates a directory, returning true on success and false on failure. 
            // Failure indicates that the path specified in the File object already exists, 
            // or that the directory cannot be created because the entire path does not exist yet.
            boolean isDataFolderCreated = dataFolder.mkdir();
            if (!isDataFolderCreated) {
                throw new StorageOperationException(
                    String.format("%s at %s", Messages.ERROR_CREATE_FOLDER_PRE, dataFolderPathString),
                    Messages.ERROR_CREATE_FOLDER_POST
                );
            }
        }
    }

    public void checkFileExists(File file) throws StorageOperationException {
        // Note: It is intentional that I do not check that the file ends with txt.
        // The file.exists() method checks for the existence of the exact file specified by the full path,
        // including the filename and its extension. It is looking for a file named "tasks.txt" specifically.
        boolean isFileExists = file.exists();
//        System.out.println("is " + file.toString() + " exists: " + isFileExists);

        String filePathString = file.getPath().toString();

        if (!isFileExists) {
            try {
                boolean isFileCreated = file.createNewFile();
                if (!isFileCreated) {
                    throw new StorageOperationException(
                        String.format("%s at %s", Messages.ERROR_CREATE_FILE_PRE, filePathString),
                        Messages.ERROR_CREATE_FILE_POST
                    );
                }
            } catch (IOException e) {
                throw new StorageOperationException(
                    String.format("%s at %s", Messages.ERROR_CREATE_FILE_PRE, filePathString),
                    Messages.ERROR_IO_CREATE_FILE
                );
            } catch (SecurityException e) {
                throw new StorageOperationException(
                    String.format("%s at %s due to %s", Messages.ERROR_CREATE_FILE_PRE,
                        filePathString, Messages.ERROR_SECURITY_CREATE_FILE),
                    String.format("%s create a new file.", Messages.MESSAGE_INSUFFICIENT_PERMISSIONS_PRE)
                );
            }
        }
    }

    /**
     * Checks if the task file exists and creates it if it does not.
     *
     * @throws StorageOperationException if an error occurs while creating the task file.
     */
    // Handles missing data file by creating it
    public void checkTaskFileExists() throws StorageOperationException {
        File tasksFile = getTasksFile();
        checkFileExists(tasksFile);
    }

    public void checkArchiveFileExists() throws StorageOperationException {
        File archiveFile = getArchiveTasksFile();
        checkFileExists(archiveFile);
    }

    /**
     * Reads all lines from the task file.
     *
     * @return a list of strings representing the lines in the task file.
     * @throws IOException if an error occurs while reading the file.
     */
    public List<String> getAllLines() throws IOException {
        Path filePath = getTasksFilePath();
        List<String> lines;
        lines = Files.readAllLines(filePath);
        return lines;
    }

    /**
     * Loads all tasks from the task file into a {@code TaskList} object.
     *
     * @return a TaskList containing the loaded tasks.
     * @throws IOException              if an error occurs while reading the file.
     * @throws FileContentException     if the content of the file is invalid.
     * @throws TaskListDecoderException if an error occurs while decoding the task list.
     * @throws CommandException         if an error occurs while processing the command.
     */
    // Write all lines in tasks.txt into TaskList
    public TaskList loadTasks() throws IOException, FileContentException, TaskListDecoderException, CommandException {
        TaskList taskList;
        List<String> lines;
        try {
            lines = getAllLines();
            taskList = TaskListDecoder.decodeTaskList(lines);
        } catch (IOException e) {
            throw new IOException(Messages.ERROR_READ_FILE);
        }
        return taskList;
    }

    public void appendEncodedTaskToFile(Task task, Path filePath) throws StorageOperationException {
        try {
            String encodedTask = TaskListEncoder.encodeTaskToString(task);
            // https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
            encodedTask += System.lineSeparator();
            Files.write(filePath, encodedTask.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ioe) {
            throw new StorageOperationException(
                String.format("%s at %s", Messages.ERROR_WRITE_FILE, filePath.toString()),
                String.format("%s write to the task file", Messages.MESSAGE_INSUFFICIENT_PERMISSIONS_PRE)
            );
        }
    }

    public void writeEncodedTasksToFile(TaskList taskList, Path filePath) throws StorageOperationException {
        try {
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
            Files.write(filePath, encodedTaskList);
        } catch (IOException ioe) {
            throw new StorageOperationException(
                String.format("%s at %s", Messages.ERROR_WRITE_FILE, filePath.toString()),
                String.format("%s write to the task file", Messages.MESSAGE_INSUFFICIENT_PERMISSIONS_PRE)
            );
        }
    }

    /**
     * Writes and saves all tasks from the {@code TaskList} object into the task file (tasks.txt).
     *
     * @param taskList represents the task list object to be saved.
     * @throws StorageOperationException if an error occurs while writing to the file.
     */
    public void saveTasks(TaskList taskList) throws StorageOperationException {
        writeEncodedTasksToFile(taskList, getTasksFilePath());
    }

    public void archiveTask(Task task) throws StorageOperationException {
        appendEncodedTaskToFile(task, getArchiveTasksFilePath());
    }

    public void archiveTasks(TaskList taskList) throws StorageOperationException {
        writeEncodedTasksToFile(taskList, getArchiveTasksFilePath());
    }

}
