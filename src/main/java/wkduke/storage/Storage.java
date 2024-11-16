package wkduke.storage;

import wkduke.common.Messages;
import wkduke.exception.command.CommandOperationException;
import wkduke.exception.storage.FileContentException;
import wkduke.exception.storage.StorageFilePathException;
import wkduke.exception.storage.StorageOperationException;
import wkduke.storage.decoder.TaskListDecoder;
import wkduke.storage.encoder.TaskListEncoder;
import wkduke.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static wkduke.common.Messages.MESSAGE_DUPLICATE_TASK_IN_FILE;
import static wkduke.common.Messages.MESSAGE_DUPLICATE_TASK_IN_FILE_HELP;

/**
 * Manages storage of task data in the file system.
 * Handles file reading, writing, and ensures proper file setup.
 */
public class Storage {
    public static final String DEFAULT_STORAGE_FILEPATH = "./data/tasks.txt";
    private final Path filePath;

    /**
     * Initializes a Storage instance with the default file path.
     *
     * @throws StorageOperationException If there is an error in creating the file or directories.
     */
    public Storage() throws StorageOperationException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Initialises a Storage instance with a specified file path.
     *
     * @param filePathString The file path for storing tasks.
     * @throws StorageOperationException If there is an error in creating the file or directories.
     * @throws StorageFilePathException  If the file path is invalid (must end with ".txt").
     */
    public Storage(String filePathString) throws StorageOperationException {
        assert filePathString != null : "Precondition failed: 'filePathString' cannot be null";
        filePath = Paths.get(filePathString);
        if (!isValidPath(filePath)) {
            throw new StorageFilePathException(Messages.MESSAGE_FILE_PATH_ERROR);
        }
        createFileAndDirectories(filePath);
        assert filePath != null : "Postcondition failed: 'filePath' should be initialised to a valid path";
    }

    /**
     * Validates that the file path ends with ".txt".
     *
     * @param filePath The file path to validate.
     * @return {@code true} if the path ends with ".txt"; {@code false} otherwise.
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Creates the necessary directories and file if they do not exist.
     *
     * @param filePath The file path for the task storage.
     * @throws StorageOperationException If an error occurs while creating the file or directories.
     */
    private void createFileAndDirectories(Path filePath) throws StorageOperationException {
        Path folderPath = filePath.getParent();

        try {
            if (folderPath != null && Files.notExists(folderPath)) {
                Files.createDirectories(folderPath);
            }
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new StorageOperationException(
                    Messages.MESSAGE_CREATE_FILE_ERROR,
                    String.format("FilePath='%s'", filePath)
            );
        }
    }

    /**
     * Retrieves the file path where tasks are stored.
     *
     * @return The {@code Path} representing the storage file location.
     */
    public Path getFilePath() {
        return filePath;
    }

    /**
     * Loads the task list from the storage file.
     *
     * @return A {@code TaskList} containing tasks read from the file.
     * @throws FileContentException      If the file content is improperly formatted.
     * @throws StorageOperationException If there is an error reading from the file.
     */
    public TaskList load() throws FileContentException, StorageOperationException {
        try {
            return TaskListDecoder.decodeTaskList(Files.readAllLines(filePath));
        } catch (FileNotFoundException e) {
            throw new AssertionError("A non-existent file scenario is already handled earlier");
        } catch (IOException e) {
            throw new StorageOperationException(
                    Messages.MESSAGE_READ_FILE_ERROR,
                    String.format("FilePath='%s", filePath)
            );
        } catch (CommandOperationException e) {
            throw new StorageOperationException(
                    MESSAGE_DUPLICATE_TASK_IN_FILE,
                    String.format("FilePath='%s, %s", filePath, e.getDetail()),
                    MESSAGE_DUPLICATE_TASK_IN_FILE_HELP
            );
        }
    }

    /**
     * Saves the given task list to the storage file.
     *
     * @param taskList The {@code TaskList} to save to the file.
     * @throws StorageOperationException If there is an error writing to the file.
     */
    public void save(TaskList taskList) throws StorageOperationException {
        assert taskList != null : "Precondition failed: 'taskList' cannot be null";
        try {
            List<String> encodedTasks = TaskListEncoder.encodeTaskList(taskList);
            Files.write(filePath, encodedTasks);
        } catch (IOException ioe) {
            throw new StorageOperationException(
                    Messages.MESSAGE_WRITE_FILE_ERROR,
                    String.format("FilePath='%s", filePath)
            );
        }
    }
}
