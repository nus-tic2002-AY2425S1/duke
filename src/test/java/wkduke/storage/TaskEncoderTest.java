package wkduke.storage;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import wkduke.storage.encoder.TaskEncoder;
import wkduke.task.Deadline;
import wkduke.task.Event;
import wkduke.task.Task;
import wkduke.task.TaskPriority;
import wkduke.task.Todo;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class TaskEncoderTest {
    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ValidTests {

        private static Stream<Object[]> validTaskProvider() {
            Todo todo1 = new Todo("Read book", false, TaskPriority.LOW);
            Todo todo2 = new Todo("Complete assignment", true, TaskPriority.HIGH);
            Deadline deadline1 = new Deadline("Submit report", LocalDateTime.of(2024, 11, 5, 23, 59), true, TaskPriority.HIGH);
            Deadline deadline2 = new Deadline("Start project", LocalDateTime.of(2024, 12, 10, 12, 0), false, TaskPriority.LOW);
            Event event1 = new Event("Attend workshop", LocalDateTime.of(2024, 11, 5, 9, 0), LocalDateTime.of(2024, 11, 5, 17, 0), false, TaskPriority.MEDIUM);
            Event event2 = new Event("Meeting", LocalDateTime.of(2024, 11, 10, 8, 0), LocalDateTime.of(2024, 11, 10, 18, 0), true, TaskPriority.HIGH);
            return Stream.of(
                    new Object[]{todo1, "T | L | 0 | Read book"},
                    new Object[]{todo2, "T | H | 1 | Complete assignment"},
                    new Object[]{deadline1, "D | H | 1 | Submit report | 2024-11-05 23:59"},
                    new Object[]{deadline2, "D | L | 0 | Start project | 2024-12-10 12:00"},
                    new Object[]{event1, "E | M | 0 | Attend workshop | 2024-11-05 09:00 | 2024-11-05 17:00"},
                    new Object[]{event2, "E | H | 1 | Meeting | 2024-11-10 08:00 | 2024-11-10 18:00"}
            );
        }

        @Order(1)
        @ParameterizedTest
        @MethodSource("validTaskProvider")
        public void encodeTask_validTask_returnsEncodedString(Task task, String expectedEncodedString) {
            String encodedTask = TaskEncoder.encodeTask(task);
            assertEquals(expectedEncodedString, encodedTask);
        }
    }
}
