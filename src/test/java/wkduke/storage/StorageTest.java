package wkduke.storage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import wkduke.exception.FileContentException;
import wkduke.exception.StorageFilePathException;
import wkduke.exception.StorageOperationException;
import wkduke.task.Deadline;
import wkduke.task.Event;
import wkduke.task.TaskList;
import wkduke.task.ToDo;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static wkduke.util.TestUtil.assertTextFilesEqual;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class StorageTest {
    private static final String VALID_DATA_FILE = "src/test/data/StorageTest/ValidData.txt";
    private static final String INVALID_DATA_FILE = "src/test/data/StorageTest/InvalidData.txt";

    @TempDir
    static Path tempDir;
    private TaskList taskList;
    private Storage storage;

    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ValidTests {
        private static Stream<String> validFilePathsProvider() {
            return Stream.of(
                    "tasks.txt",                    // Root-level file
                    "data/tasks.txt",                       // Single-level folder hierarchy
                    "data/subfolder/tasks.txt",             // Two-level folder hierarchy
                    "data/subfolder/subfolder/tasks.txt"    // Multiple-level folder hierarchy
            ).map(path -> tempDir.resolve(path).toString());
        }

        @Order(1)
        @ParameterizedTest
        @MethodSource("validFilePathsProvider")
        public void constructor_validFilePath_createsStorage(String filePath) {
            assertDoesNotThrow(() -> new Storage(filePath));
        }

        @BeforeEach
        void setup() throws StorageOperationException {
            storage = new Storage(VALID_DATA_FILE);
            taskList = new TaskList();
            taskList.addTask(new ToDo("Read book"));
            taskList.addTask(new Deadline("Submit report", LocalDateTime.of(2024, 11, 5, 23, 59)));
            taskList.addTask(new Event("Attend workshop", LocalDateTime.of(2024, 11, 5, 9, 0), LocalDateTime.of(2024, 11, 5, 17, 0)));
        }

        @Order(2)
        @Test
        void load_validFileContent_returnsTaskList() throws StorageOperationException, FileContentException {
            TaskList loadedTaskList = storage.load();
            assertEquals(taskList, loadedTaskList);
        }

        @Order(3)
        @Test
        void save_validTaskList_writesToFile() throws StorageOperationException, IOException {
            Path filePath = tempDir.resolve("tasks.txt");
            Storage tempStorage = new Storage(filePath.toString());
            tempStorage.save(taskList);
            assertTextFilesEqual(storage.getFilePath(), tempStorage.getFilePath());
        }
    }

    @Order(2)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class InvalidTests {
        private static Stream<String> invalidFilePathsProvider() {
            return Stream.of(
                    "tasks.xlsx",
                    "tasks.csv",
                    "tasks.json",
                    "tasks.sql"
            ).map(path -> tempDir.resolve(path).toString());
        }

        @Order(1)
        @ParameterizedTest
        @MethodSource("invalidFilePathsProvider")
        public void constructor_invalidFilePath_throwsStorageFilePathException(String filePath) {
            assertThrows(StorageFilePathException.class, () -> new Storage(filePath));
        }

        @Order(2)
        @Test
        void load_invalidFileContent_throwsFileContentException() throws StorageOperationException {
            Storage storage = new Storage(INVALID_DATA_FILE);
            assertThrows(FileContentException.class, storage::load);
        }
    }
}
