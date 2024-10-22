import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StorageFile {
    protected static final String DEFAULT_STORAGE_FILEPATH = "./data/tasks.txt";
    private final Path filePath;

    public StorageFile()
            throws InvalidStorageFilePathException, StorageOperationException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public StorageFile(String filePathString) throws InvalidStorageFilePathException, StorageOperationException {
        filePath = Paths.get(filePathString);
        Path folderPath = filePath.getParent();

        if (!isValidPath(filePath)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }

        try {
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new StorageOperationException("Error creating the folder or file: " + filePath);
        }
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    public List<Task> load() throws StorageOperationException {
        try {
            return TaskListDecoder.decodeTaskList(Files.readAllLines(filePath));
        } catch (FileNotFoundException e) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        } catch (IOException e) {
            throw new StorageOperationException("Error writing to file: " + filePath);
        } catch (IllegalValueException e) {
            throw new StorageOperationException("File contains illegal data values; data type constraints not met");
        }
    }

    public void save(List<Task> taskList) throws StorageOperationException {
        try {
            List<String> encodedAddressBook = TaskListEncoder.encodeTaskList(taskList);
            Files.write(filePath, encodedAddressBook);
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + filePath);
        }
    }

}
