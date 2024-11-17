package storage;

import common.Constants;
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

// Solution below adapted from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java

/**
 * Represents the storage file used to manage storage of tasks data.
 * Handles the creation, verification, and management of the task file and its directory {@code data}.
 * Deals with loading tasks from the task file and saving tasks in the task file.
 */
public class Storage {

    private static final String DEFAULT_STORAGE_FILEPATH =
        System.getProperty("user.dir") + File.separator + Paths.get("duke", "duke", "src", "main", "java", "data", "tasks.txt");

    private static final String ARCHIVE_STORAGE_FILEPATH =
        System.getProperty("user.dir") + File.separator + Paths.get("duke", "duke", "src", "main", "java", "data", "archive.txt");
    private final Path tasksFilePath;
    private final Path archiveTasksFilePath;

    /**
     * Constructs an instance of Storage and initializes the file path to the default value.
     * The constructor checks for the existence of the data folder and task file, and creates them if they don't exist.
     *
     * @throws StorageOperationException if an error occurs while creating the data folder or task file.
     */
    public Storage() throws StorageOperationException {
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

    private StorageOperationException throwStorageOperationException
        (String errorMessagePre, String filePathString, String errorMessagePost) {

        return new StorageOperationException(
            String.format("%s at %s", errorMessagePre, filePathString),
            errorMessagePost
        );

    }

    /**
     * Checks if the data folder exists. Handles missing data folder by creating it.
     * The data folder is expected to be in the {@code /data} directory. It must match the file path exactly.
     *
     * @throws StorageOperationException if an error occurs while checking or creating the data folder,
     *                                   e.g. folder cannot be created
     */
    public void checkDataFolderExists() throws StorageOperationException {
        // Solution below referenced from https://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists
        // Check if "data" directory exists in current folder
        Path dataFolderPath = getTasksFilePath().getParent();
        String dataFolderPathString = dataFolderPath.toString();
        assert dataFolderPath != null : "Data folder path should not be null";

        File dataFolder = new File(dataFolderPathString);
        assert !dataFolder.exists() || dataFolder.isDirectory() : "Data folder must exist and be a directory";
        boolean isDataFolderExists = dataFolder.exists() && dataFolder.isDirectory();

        // Create the directory if it does not exist
        if (!isDataFolderExists) {
            // Solution below referenced from https://tutorialspoint.com/java/java_directories.htm
            // The mkdir() method creates a directory, returning true on success and false on failure.
            // Failure indicates that the path specified in the File object already exists,
            // or that the directory cannot be created because the entire path does not exist yet.
            boolean isDataFolderCreated = dataFolder.mkdir();
            if (!isDataFolderCreated) {
                // Error: Failed to create the data directory at ./data
                //     Please check your folder path and permissions.
//                assert isDataFolderCreated : "Failed to create the data directory at " + dataFolderPath;
                throw throwStorageOperationException(Messages.ERROR_CREATE_FOLDER_PRE, dataFolderPathString,
                    Messages.ERROR_CREATE_FOLDER_POST);
            }
        }
    }

    public void checkFileExists(File file) throws StorageOperationException {
        assert file != null : "File reference should not be null";
        assert file.getPath() != null : "File path should not be null";

        // Note: It is intentional that I do not check that the file ends with txt.
        // The file.exists() method checks for the existence of the exact file specified by the full path,
        // including the filename and its extension. It is looking for a file named "tasks.txt" specifically.
        boolean isFileExists = file.exists();

        assert !isFileExists || file.isFile() : "Expected a valid file at " + file.getPath();

        String filePathString = file.getPath();

        if (!isFileExists) {
            try {
                boolean isFileCreated = file.createNewFile();
                if (!isFileCreated) {
                    // Error: Failed to create the task file at ./data/tasks.txt
                    // Please check your file path and permissions.
                    assert isFileCreated : "Failed to create the task file at " + file.getPath();
                    throw throwStorageOperationException(Messages.ERROR_CREATE_FILE_PRE, filePathString,
                        Messages.ERROR_CREATE_FILE_POST);
                }
            } catch (IOException e) {
                throw throwStorageOperationException(Messages.ERROR_CREATE_FILE_PRE, filePathString,
                    Messages.ERROR_IO_CREATE_FILE);
            } catch (SecurityException e) {
                String modifiedFilePathString = filePathString + Constants.SPACE + "due to" +
                    Constants.SPACE + Messages.ERROR_SECURITY_CREATE_FILE;
                String errorMessagePost = Messages.MESSAGE_INSUFFICIENT_PERMISSIONS_PRE +
                    Constants.SPACE + "create a new file.";
                throw throwStorageOperationException(Messages.ERROR_CREATE_FILE_PRE, modifiedFilePathString,
                    errorMessagePost);
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
        assert Files.exists(tasksFilePath) : "Task file must exist at " + tasksFilePath;
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

    public void handleWriteToFileIOException(Path filePath) throws StorageOperationException {
        String filePathString = filePath.toString();
        String fileName = new File(filePathString).getName();
        String errorMessagePost = Messages.MESSAGE_INSUFFICIENT_PERMISSIONS_PRE + "write to " + fileName;

        throw throwStorageOperationException(Messages.ERROR_WRITE_FILE, filePathString, errorMessagePost);

    }

    public void appendEncodedTaskToFile(Task task, Path filePath) throws StorageOperationException {
        try {
            String encodedTask = TaskListEncoder.encodeTaskToString(task);
            // https://stackoverflow.com/questions/1625234/how-to-append-text-to-an-existing-file-in-java
            encodedTask += System.lineSeparator();
            Files.write(filePath, encodedTask.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException ioe) {
            handleWriteToFileIOException(filePath);
        }
    }

    public void writeEncodedTasksToFile(TaskList taskList, Path filePath) throws StorageOperationException {
        final String TASKS_FILE = "tasks.txt";
        final String ARCHIVE_FILE = "archive.txt";
        boolean isEndsWithTasks = filePath.endsWith(TASKS_FILE);
        boolean isEndsWithArchive = filePath.endsWith(ARCHIVE_FILE);

        try {
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
            if (isEndsWithArchive) {
                Files.write(filePath, encodedTaskList, StandardOpenOption.APPEND);
            } else if (isEndsWithTasks) {
                Files.write(filePath, encodedTaskList);
            }
        } catch (IOException ioe) {
            handleWriteToFileIOException(filePath);
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
