package wkduke.storage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import wkduke.exception.storage.FileContentException;
import wkduke.storage.decoder.TaskDecoder;
import wkduke.task.*;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class TaskDecoderTest {
    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ValidTests {
        private static Stream<Object[]> validTaskProvider() {
            Todo todoLow = new Todo("Read book", false, TaskPriority.LOW);
            Todo todoHigh = new Todo("Complete assignment", true, TaskPriority.HIGH);
            Deadline deadlineHighDone = new Deadline("Submit report",
                    LocalDateTime.of(2024, 11, 5, 23, 59),
                    true, TaskPriority.HIGH);
            Deadline deadlineLowNotDone = new Deadline("Start project",
                    LocalDateTime.of(2024, 12, 10, 12, 0),
                    false, TaskPriority.LOW);
            Event eventMedium = new Event("Attend workshop",
                    LocalDateTime.of(2024, 11, 5, 9, 0),
                    LocalDateTime.of(2024, 11, 5, 17, 0),
                    false, TaskPriority.MEDIUM);
            Event eventHighDone = new Event("Meeting",
                    LocalDateTime.of(2024, 11, 10, 8, 0),
                    LocalDateTime.of(2024, 11, 10, 18, 0),
                    true, TaskPriority.HIGH);

            return Stream.of(
                    new Object[]{"T | L | 0 | Read book", todoLow},
                    new Object[]{"T | H | 1 | Complete assignment", todoHigh},
                    new Object[]{"D | H | 1 | Submit report | 2024-11-05 23:59", deadlineHighDone},
                    new Object[]{"D | L | 0 | Start project | 2024-12-10 12:00", deadlineLowNotDone},
                    new Object[]{"E | M | 0 | Attend workshop | 2024-11-05 09:00 | 2024-11-05 17:00", eventMedium},
                    new Object[]{"E | H | 1 | Meeting | 2024-11-10 08:00 | 2024-11-10 18:00", eventHighDone}
            );
        }

        @Order(1)
        @ParameterizedTest
        @MethodSource("validTaskProvider")
        public void decodeTask_validEncodedTask_returnsCorrectTask(String encodedTask, Task expectedTask) throws FileContentException {
            Task task = TaskDecoder.decodeTask(encodedTask);
            assertEquals(expectedTask, task);
        }
    }

    @Order(2)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class InvalidTests {
        private static Stream<String> invalidTaskProvider() {
            return Stream.of(
                    "X | 0 | Unknown task type",            // Invalid task type
                    "D | 0 | Submit report",                        // Missing due date for Deadline
                    "E | 0 | Attend workshop | | 17:00",            // Missing start date for Event
                    "E | 0 | Attend workshop | 09:00 | ",           // Missing end date for Event
                    "D | 0 | Submit report | invalid-date",         // Invalid date format
                    "T | X | 1 | Submit report",                    // Missing priority for Todo
                    "D | | 1 | Submit report | 2024-11-05 23:59"    // Missing priority for Deadline
            );
        }

        @Order(1)
        @ParameterizedTest
        @MethodSource("invalidTaskProvider")
        public void decodeTask_invalidEncodedTask_throwsFileContentException(String encodedTask) {
            assertThrows(FileContentException.class, () -> TaskDecoder.decodeTask(encodedTask));
        }
    }
}
