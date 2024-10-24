import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    protected static final String DEFAULT_STORAGE_FILEPATH = "./data/tasks.txt";
    private final Path filePath;

    public Storage() throws StorageOperationException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    public Storage(String filePathString) throws StorageOperationException {
        filePath = Paths.get(filePathString);
        Path folderPath = filePath.getParent();

        if (!isValidPath(filePath)) {
            throw new StorageFilePathException(Messages.MESSAGE_FILE_PATH_ERROR);
        }
        try {
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new StorageOperationException(
                    Messages.MESSAGE_CREATE_FILE_ERROR,
                    String.format("FilePath='%s", filePathString)
            );
        }
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

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
        }
    }

    public void save(TaskList taskList) throws StorageOperationException {
        try {
            List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
            Files.write(filePath, encodedTaskList);
        } catch (IOException ioe) {
            throw new StorageOperationException(
                    Messages.MESSAGE_WRITE_FILE_ERROR,
                    String.format("FilePath='%s", filePath)
            );
        }
    }

}
