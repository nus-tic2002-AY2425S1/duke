package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.Messages;
import exception.CommandException;
import exception.FileContentException;
import exception.StorageOperationException;
import exception.TaskListDecoderException;
import task.TaskList;
import util.TypicalTasks;

public class StorageTest {
    
    private final String FILE_STORAGE_PATH = "./data/tasks.txt";
    
    private Storage storage;

    @BeforeEach
    public void setUp() throws StorageOperationException, IOException {
        // Set the system property for testing
        System.setProperty("storage.file", FILE_STORAGE_PATH);
        storage = new Storage();
    }

    // https://howtodoinjava.com/junit5/after-each-annotation-example/
    // https://stackoverflow.com/questions/27599965/java-better-way-to-delete-file-if-exists
    @AfterEach
    public void cleanUp() throws IOException {
        // Clean up the test files and directories
        String tasksFilePath = FILE_STORAGE_PATH;
        String dataFolderPath = Paths.get(tasksFilePath).getParent().toString();
        Files.deleteIfExists(Paths.get(FILE_STORAGE_PATH));
        Files.deleteIfExists(Paths.get(dataFolderPath));
    }

    @Test
    public void constructor_checkDataFileExists() {
        File tasksFile = storage.getFile();
        assertTrue(tasksFile.exists(), "Tasks file should exist after the storage has been initialized");
    }

    @Test
    public void constructor_checkDataFolderExists() {
        // https://www.geeksforgeeks.org/get-the-files-parent-directory-in-java/
        File dataFolder = storage.getFile().getParentFile();
        assertTrue(dataFolder.exists(), "Data folder should exist after the storage has been initialized");
    }

    @Test
    public void checkDataFolderExists_createDataFolder() throws IOException, StorageOperationException {
        File dataFolder = storage.getFile().getParentFile();
        
        // Delete the data folder if it exists
        if (dataFolder.exists()) {
            // Delete all files in the directory
            File[] files = dataFolder.listFiles();
            if (files.length > 0) {
                for (File file : files) {
                    Files.delete(file.toPath());
                }
            }
        } else {
            // Create the data folder if it doesn't exist
            dataFolder.mkdirs();
        }
        
        storage.checkDataFolderExists();
        assertTrue(dataFolder.exists(), "Data folder should exist after the execution of the checkDataFolderExists method");
    }

    @Test
    public void checkTaskFileExists_createsTaskFile() throws IOException, StorageOperationException {
        // Ensure the task file does not exist
        File tasksFile = storage.getFile();
        Files.deleteIfExists(tasksFile.toPath());

        storage.checkTaskFileExists();
        assertTrue(tasksFile.exists(), "Task file should be created");
    }

    @Test
    public void getFilePath_returnsCorrectPath() {
        Path expectedPath = Paths.get(FILE_STORAGE_PATH);
        assertEquals(expectedPath, storage.getFilePath(), "File path should match the expected path");
    }

    @Test
    public void getFile_returnsCorrectFile() {
        File expectedFile = new File(FILE_STORAGE_PATH);
        assertEquals(expectedFile, storage.getFile(), "File object should match the expected file");
    }

    // https://stackoverflow.com/questions/29878237/java-how-to-clear-a-text-file-without-deleting-it
    private void clearFileContent(File file) throws IOException {
        try (FileWriter fw = new FileWriter(file, false)) {
            // The FileWriter with 'false' in the second argument will clear the file contents
        } catch (IOException e) {
            throw new IOException(Messages.ERROR_READ_FILE, e);
        }
    }
    
    // Ensure that when an empty file is read, the method behaves correctly by returning an empty list.
    @Test
    public void getAllLines_returnsEmptyListWhenFileIsEmpty() throws IOException {
        clearFileContent(storage.getFile());
        List<String> lines = storage.getAllLines();

        // Checks that the lines variable is not null. 
        // If there are no lines, the test will fail at this point, indicating that the method getAllLines() did not return a valid list.
        // Without this, calling lines.isEmpty() would result in a NullPointerException
        assertNotNull(lines);

        // Checks whether the lines list is empty. 
        // The isEmpty() method of the List interface returns true if the list contains no elements.
        // If the list is not empty (i.e., it contains one or more lines), the assertion will fail, and the provided message will help diagnose the failure. 
        // The message indicates that the expectation was for the method to return an empty list when the file is empty.
        assertTrue(lines.isEmpty(), "Should return an empty list for an empty file");
    }

    @Test
    public void writeToFile_writeTasksFromTasksList() throws StorageOperationException, IOException {
        TaskList taskList = new TaskList();
        TypicalTasks typicalTasks = new TypicalTasks();
        taskList = typicalTasks.initDefaultTaskList();

        storage.saveTasks(taskList);

        List<String> lines = storage.getAllLines();
        assertEquals(taskList.getSize(), lines.size(), "Task list should contain the same number of tasks in the tasks file");
        
    }

    @Test
    public void loadTasks_readTasksFromFile() throws IOException, FileContentException, TaskListDecoderException, CommandException, StorageOperationException {
        TypicalTasks typicalTasks = new TypicalTasks();
        TaskList sampleTaskList = new TaskList();
        sampleTaskList.addTask(typicalTasks.todo_doHomework);
        sampleTaskList.addTask(typicalTasks.deadline_submitReport);
        sampleTaskList.addTask(typicalTasks.event_projectLaunch);

        // Prepare the task file with sample data
        storage.saveTasks(sampleTaskList);

        TaskList taskList = storage.loadTasks();
        assertNotNull(taskList, "Loaded tasks should not be null");
        assertEquals(sampleTaskList.getSize(), taskList.getSize(), "Task list should contain the same number of tasks in the tasks file");
    }

}
