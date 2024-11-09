package wkduke.storage;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import wkduke.task.Deadline;
import wkduke.task.Event;
import wkduke.task.TaskList;
import wkduke.task.ToDo;

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
            taskList.addTask(new ToDo("Read book"));
            taskList.addTask(new Deadline("Submit report",
                    LocalDateTime.of(2024, 11, 5, 23, 59)));
            taskList.addTask(new Event("Attend workshop",
                    LocalDateTime.of(2024, 11, 5, 9, 0),
                    LocalDateTime.of(2024, 11, 5, 17, 0)));

            List<String> expectedEncodedTasks = List.of(
                    "T | 0 | Read book",
                    "D | 0 | Submit report | 2024-11-05 23:59",
                    "E | 0 | Attend workshop | 2024-11-05 09:00 | 2024-11-05 17:00"
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
