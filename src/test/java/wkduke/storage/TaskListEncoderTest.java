package wkduke.storage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import wkduke.task.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class TaskListEncoderTest {
    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ValidTests {
        private static Stream<Object[]> validTaskListProvider() {
            // First test case: Mixed TaskList
            TaskList taskList = new TaskList();
            taskList.addTask(new Todo("Read book", false, TaskPriority.LOW));
            taskList.addTask(new Todo("Complete assignment", true, TaskPriority.HIGH));
            taskList.addTask(new Deadline("Submit report",
                    LocalDateTime.of(2024, 11, 5, 23, 59),
                    true, TaskPriority.HIGH)
            );
            taskList.addTask(new Deadline("Start project",
                    LocalDateTime.of(2024, 12, 10, 12, 0),
                    false, TaskPriority.LOW)
            );
            taskList.addTask(new Event("Attend workshop",
                    LocalDateTime.of(2024, 11, 5, 9, 0),
                    LocalDateTime.of(2024, 11, 5, 17, 0),
                    false, TaskPriority.MEDIUM)
            );
            taskList.addTask(new Event("Meeting",
                    LocalDateTime.of(2024, 11, 10, 8, 0),
                    LocalDateTime.of(2024, 11, 10, 18, 0),
                    true, TaskPriority.HIGH)
            );

            List<String> expectedEncodedTasks = List.of(
                    "T | L | 0 | Read book",
                    "T | H | 1 | Complete assignment",
                    "D | H | 1 | Submit report | 2024-11-05 23:59",
                    "D | L | 0 | Start project | 2024-12-10 12:00",
                    "E | M | 0 | Attend workshop | 2024-11-05 09:00 | 2024-11-05 17:00",
                    "E | H | 1 | Meeting | 2024-11-10 08:00 | 2024-11-10 18:00"
            );

            // Second test case: Empty TaskList
            TaskList emptyTaskList = new TaskList();
            List<String> expectedEmptyEncodedTasks = List.of();

            return Stream.of(
                    new Object[]{taskList, expectedEncodedTasks},
                    new Object[]{emptyTaskList, expectedEmptyEncodedTasks}
            );
        }

        @Order(1)
        @ParameterizedTest
        @MethodSource("validTaskListProvider")
        public void encodeTask_validTaskList_returnsEncodedString(TaskList taskList, List<String> expectedEncodedTasks) {
            List<String> encodedTasks = TaskListEncoder.encodeTaskList(taskList);
            assertEquals(expectedEncodedTasks, encodedTasks);
        }
    }
}
