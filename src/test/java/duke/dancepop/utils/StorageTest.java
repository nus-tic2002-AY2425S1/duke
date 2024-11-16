package duke.dancepop.utils;

import duke.dancepop.entities.Deadline;
import duke.dancepop.entities.Event;
import duke.dancepop.entities.Task;
import duke.dancepop.entities.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {

    private static final String TEST_FILE_PATH = "volume/";
    private static final String TEST_FILE_NAME = "test_data.csv";
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(2024, 1, 1, 10, 0);

    static Stream<Task> provideTasks() {
        return Stream.of(
                new Todo("Test Todo"),
                new Deadline("Test Deadline", DATE_TIME),
                new Event("Test Event", DATE_TIME, DATE_TIME.plusHours(2))
        );
    }

    @BeforeEach
    void setup() {
        TaskList.clear();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Path.of(TEST_FILE_PATH, TEST_FILE_NAME));
        TaskList.clear();
    }

    @ParameterizedTest
    @MethodSource("provideTasks")
    void Given_Task_When_SaveAndLoad_Should_AddTaskDataCorrectly(Task task) {
        TaskList.addAndPrint(task);
        Storage.saveToFile(TEST_FILE_NAME);

        TaskList.clear();
        Storage.loadFromFile(TEST_FILE_NAME);

        assertEquals(1, TaskList.getTasks().size());
        Task loadedTask = TaskList.get(0);
        assertEquals(task.getClass(), loadedTask.getClass());
        assertEquals(task.getDescription(), loadedTask.getDescription());
        assertEquals(task.getIsDone(), loadedTask.getIsDone());


        if (task instanceof Deadline deadline) {
            assertEquals(deadline.getDeadline(), ((Deadline) loadedTask).getDeadline());
        } else if (task instanceof Event event) {
            assertEquals(event.getStart(), ((Event) loadedTask).getStart());
            assertEquals(event.getEnd(), ((Event) loadedTask).getEnd());
        }
    }

    @Test
    void Given_MultipleTasks_When_SaveAndLoad_Should_AddAllTasksCorrectly() {
        Task todo = new Todo("Sample Todo");
        Task deadline = new Deadline("Sample Deadline", DATE_TIME);
        Task event = new Event("Sample Event", DATE_TIME, DATE_TIME.plusHours(3));

        TaskList.add(todo);
        TaskList.add(deadline);
        TaskList.add(event);
        Storage.saveToFile(TEST_FILE_NAME);

        TaskList.clear();
        Storage.loadFromFile(TEST_FILE_NAME);

        assertEquals(TaskList.getTasks().size(), 3);
        assertEquals(todo.toString(), TaskList.get(0).toString());
        assertEquals(deadline.toString(), TaskList.get(1).toString());
        assertEquals(event.toString(), TaskList.get(2).toString());
    }

    @Test
    void Given_InvalidFileName_When_LoadFromFile_Should_NotPopulateTaskList() {
        assertDoesNotThrow(() -> Storage.loadFromFile("non_existent_file.csv"));
        assertTrue(TaskList.getTasks().isEmpty());
    }

    @Test
    void Given_InvalidFormatInFile_When_LoadFromFile_Should_NotPopulateTaskList() throws IOException {
        Path testFilePath = Path.of(TEST_FILE_PATH, TEST_FILE_NAME);
        Files.writeString(testFilePath, "Invalid|Format|Line");
        Storage.loadFromFile(TEST_FILE_NAME);

        assertTrue(TaskList.getTasks().isEmpty());
    }

    @Test
    void Given_EmptyTaskList_When_SaveToFile_Should_HaveEmptyFile() throws IOException {
        Storage.saveToFile(TEST_FILE_NAME);

        Path testFilePath = Path.of(TEST_FILE_PATH, TEST_FILE_NAME);
        assertTrue(Files.exists(testFilePath));
        assertTrue(Files.readAllLines(testFilePath).isEmpty());
    }
}
