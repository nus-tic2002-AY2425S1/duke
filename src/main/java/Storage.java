import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.ArrayList;

// Reference: https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java

// deals with loading tasks from the file and saving tasks in the file
public class Storage {

    // Default file path used if the user doesn't provide the file name.
    private static final String DEFAULT_STORAGE_FILEPATH = "./data/tasks.txt";
    protected final Path filePath;

    public Storage() throws StorageOperationException {
        Path filePath = Paths.get(DEFAULT_STORAGE_FILEPATH);
        this.filePath = filePath;
        checkDataFolderExists();
        checkTaskFileExists();
    }

    public Path getFilePath() {
        return filePath;
    }

    public File getFile() {
        return getFilePath().toFile();
    }

    // Data file is expected to be in folder './data/'. Handles missing data folder by creating it
    public void checkDataFolderExists() throws StorageOperationException {
        // https://stackoverflow.com/questions/15571496/how-to-check-if-a-folder-exists
        // Check if "data" directory exists in current folder 
        Path dataFolderPath = getFilePath().getParent();
        // System.out.println(dataFolderPath);
        File dataFolder = new File(dataFolderPath.toString());
        boolean isDataFolderExists = dataFolder.exists() && dataFolder.isDirectory();
        // boolean isDataFolderExists = Files.exists(dataFolderPath) && Files.isDirectory(dataFolderPath);

        // Create the directory if it does not exist
        if (!isDataFolderExists) {
            // https://tutorialspoint.com/java/java_directories.htm
            // The mkdir() method creates a directory, returning true on success and false on failure. Failure indicates that the path specified in the File object already exists, or that the directory cannot be created because the entire path does not exist yet.
            boolean isDataFolderCreated = dataFolder.mkdir();
            if (isDataFolderCreated) {
                System.out.println("Data directory created");
            } else {
                throw new StorageOperationException(Messages.ERROR_CREATE_FOLDER);
            }
        }
    }

    // Handles missing data file by creating it
    public void checkTaskFileExists() throws StorageOperationException {
        File taskFile = getFile();
        // Note: It is intentional that I do not check that the file ends with txt. The file.exists() method checks for the existence of the exact file specified by the full path, including the filename and its extension. It is looking for a file named "tasks.txt" specifically.
        boolean isTaskFileExists = taskFile.exists();
        
        if (!isTaskFileExists) {
            try {
                boolean isFileCreated = taskFile.createNewFile();
                if (!isFileCreated) {
                    throw new StorageOperationException(Messages.ERROR_CREATE_FILE);
                }
            } catch (IOException e) {
                throw new StorageOperationException(Messages.ERROR_IO_CREATE_FILE);
            } catch (SecurityException e) {
                throw new StorageOperationException(Messages.ERROR_SECURITY_CREATE_FILE);
            }
        }
    }

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

    // public static decodeTaskList

    // Write all lines in tasks.txt into TaskList
    // TODO: Handle the situation of the data file being corrupted (i.e., content not in the expected format).
    public TaskList loadTasks() throws IOException, FileContentException, TaskListDecoderException {
        TaskList taskList = new TaskList();
        List<String> lines = null;
        try {
            lines = getAllLines();
            // System.out.println("lines from getAllLines() is " + lines.toString());
            taskList = TaskListDecoder.decodeTaskList(lines);
        } catch (IOException e) {
            throw new IOException(Messages.ERROR_READ_FILE);
        } 
        return taskList;

        // try {
        //     return TaskListDecoder.decodeTaskList(Files.readAllLines(filePath));
        // } catch (FileNotFoundException e) {
        //     throw new AssertionError("A non-existent file scenario is already handled earlier");
        // } catch (IOException e) {
        //     throw new StorageOperationException(
        //             Messages.MESSAGE_READ_FILE_ERROR,
        //             String.format("FilePath='%s", filePath)
        //     );
        // }
    }

}
