package storage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

import common.Messages;
import exception.CommandException;
import exception.FileContentException;
import exception.StorageOperationException;
import exception.TaskListDecoderException;
import task.TaskList;

// Reference: https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java

/**
 * Represents the storage file used to manage storage of tasks data.
 * Handles the creation, verification, and management of the task file and its directory {@code data}.
 * Deals with loading tasks from the task file and saving tasks in the task file.
 */
public class Storage {

    // Default file path used if the user doesn't provide the file name.
    private static final String DEFAULT_STORAGE_FILEPATH = System.getProperty("storage.file", "./data/tasks.txt");
    protected final Path filePath;

    /**
     * Constructs an instance of Storage and initializes the file path to the default value.
     * The constructor checks for the existence of the data folder and task file, and creates them if they don't exist.
     * 
     * @throws StorageOperationException if an error occurs while creating the data folder or task file.
     */
    public Storage() throws StorageOperationException {
        Path filePath = Paths.get(DEFAULT_STORAGE_FILEPATH);
        this.filePath = filePath;
        checkDataFolderExists();
        checkTaskFileExists();
    }

    /**
     * Retrieves the file path for the storage, i.e. the task file.
     * 
     * @return the file path of the task file as a Path object.
     */
    public Path getFilePath() {
        return filePath;
    }

    /**
     * Retrieves the task file associated with the storage.
     * 
     * @return the file object of the task file as a File object
     */
    public File getFile() {
        return getFilePath().toFile();
    }

    /**
     * Checks if the data folder exists. Handles missing data folder by creating it.
     * The data folder is expected to be in the `/data` directory. It must match the file path exactly.
     * 
     * @throws StorageOperationException if an error occurs while checking or creating the data folder, 
     * e.g. folder cannot be created
     */ 
    public void checkDataFolderExists() throws StorageOperationException {
        // https://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists
        // Check if "data" directory exists in current folder 
        Path dataFolderPath = getFilePath().getParent();
        String dataFolderPathString = dataFolderPath.toString();
        File dataFolder = new File(dataFolderPathString);
        boolean isDataFolderExists = dataFolder.exists() && dataFolder.isDirectory();
        // boolean isDataFolderExists = Files.exists(dataFolderPath) && Files.isDirectory(dataFolderPath);

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

    /**
     * Checks if the task file exists and creates it if it does not.
     * 
     * @throws StorageOperationException if an error occurs while creating the task file.
     */
    // Handles missing data file by creating it
    public void checkTaskFileExists() throws StorageOperationException {
        File taskFile = getFile();
        // Note: It is intentional that I do not check that the file ends with txt. 
        // The file.exists() method checks for the existence of the exact file specified by the full path, 
        // including the filename and its extension. It is looking for a file named "tasks.txt" specifically.
        boolean isTaskFileExists = taskFile.exists();
        
        if (!isTaskFileExists) {
            try {
                boolean isFileCreated = taskFile.createNewFile();
                if (!isFileCreated) {
                    throw new StorageOperationException(
                        String.format("%s at %s", Messages.ERROR_CREATE_FILE_PRE, getFilePath().toString()),
                        Messages.ERROR_CREATE_FILE_POST
                    );
                }
            } catch (IOException e) {
                throw new StorageOperationException(
                    String.format("%s at %s", Messages.ERROR_CREATE_FILE_PRE, getFilePath().toString()),
                    Messages.ERROR_IO_CREATE_FILE
                );
            } catch (SecurityException e) {
                throw new StorageOperationException(
                    String.format("%s at %s due to ", Messages.ERROR_CREATE_FILE_PRE, 
                        getFilePath().toString(), Messages.ERROR_SECURITY_CREATE_FILE),
                    String.format("%s create a new file.", Messages.MESSAGE_INSUFFICIENT_PERMISSIONS_PRE)
                );
            }
        }
    }

    /**
     * Reads all lines from the task file.
     * 
     * @return a list of strings representing the lines in the task file.
     * @throws IOException if an error occurs while reading the file.
     */
    public List<String> getAllLines() throws IOException {
        Path filePath = getFilePath();
        List<String> lines = null;
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            throw e;
        }
        return lines;
    }

    /**
     * Loads all tasks from the task file into a {@code TaskList} object.
     * 
     * @return a TaskList containing the loaded tasks.
     * @throws IOException if an error occurs while reading the file.
     * @throws FileContentException if the content of the file is invalid.
     * @throws TaskListDecoderException if an error occurs while decoding the task list.
     * @throws CommandException if an error occurs while processing the command.
     */
    // Write all lines in tasks.txt into TaskList
    public TaskList loadTasks() throws IOException, FileContentException, TaskListDecoderException, CommandException {
        TaskList taskList = new TaskList();
        List<String> lines = null;
        try {
            lines = getAllLines();
            taskList = TaskListDecoder.decodeTaskList(lines);
        } catch (IOException e) {
            throw new IOException(Messages.ERROR_READ_FILE);
        } 
        return taskList;
    }

    /**
     * Saves all tasks from the {@code TaskList} object into the task file.
     * 
     * @param taskList represents the task list object to be saved.
     * @throws StorageOperationException if an error occurs while writing to the file.
     */
    // Write all tasks in taskList into tasks.txt
    public void saveTasks(TaskList taskList) throws StorageOperationException {
        try {
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
            Files.write(getFilePath(), encodedTaskList);
        } catch (IOException ioe) {
            throw new StorageOperationException(
                String.format("%s at %s", Messages.ERROR_WRITE_FILE, getFilePath().toString()),
                String.format("%s write to the task file", Messages.MESSAGE_INSUFFICIENT_PERMISSIONS_PRE)
            );
        }
    }

}
