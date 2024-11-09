package wkduke.storage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import wkduke.exception.FileContentException;
import wkduke.task.Deadline;
import wkduke.task.Event;
import wkduke.task.Task;
import wkduke.task.ToDo;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class TaskListDecoderTest {
    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ValidTests {
        private static Stream<Object[]> validTaskProvider() {
            ToDo todo = new ToDo("Read book");
            Deadline deadline = new Deadline("Submit report",
                    LocalDateTime.of(2024, 11, 5, 23, 59));
            Event event = new Event("Attend workshop",
                    LocalDateTime.of(2024, 11, 5, 9, 0),
                    LocalDateTime.of(2024, 11, 5, 17, 0));
            event.markAsDone();

            return Stream.of(
                    new Object[]{"T | 0 | Read book", todo},
                    new Object[]{"D | 0 | Submit report | 2024-11-05 23:59", deadline},
                    new Object[]{"E | 1 | Attend workshop | 2024-11-05 09:00 | 2024-11-05 17:00", event}
            );
        }

        @Order(1)
        @ParameterizedTest
        @MethodSource("validTaskProvider")
        public void decodeTaskFromString_validTask_returnsCorrectTask(String encodedTask, Task expectedTask) throws FileContentException {
            Task task = TaskListDecoder.decodeTaskFromString(encodedTask);
            assertEquals(expectedTask, task);
        }
    }

    @Order(2)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class InvalidTests {
        private static Stream<String> invalidTaskProvider() {
            return Stream.of(
                    "X | 0 | Unknown task type",    // Invalid task type
                    "D | 0 | Submit report",                // Missing due date for Deadline
                    "E | 0 | Attend workshop | | 17:00",    // Missing start date for Event
                    "E | 0 | Attend workshop | 09:00 | ",   // Missing end date for Event
                    "D | 0 | Submit report | invalid-date"  // Invalid date format
            );
        }

        @Order(1)
        @ParameterizedTest
        @MethodSource("invalidTaskProvider")
        public void decodeTaskFromString_invalidTask_throwsFileContentException(String encodedTask) {
            assertThrows(FileContentException.class, () -> TaskListDecoder.decodeTaskFromString(encodedTask));
        }
    }
}
