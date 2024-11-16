package wkduke.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import wkduke.command.update.SortOrder;
import wkduke.exception.command.CommandOperationException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class TaskListTest {
    private TaskList taskList;
    private Todo todo1, todo2;
    private Deadline deadline1, deadline2;
    private Event event1, event2;

    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ValidSortTests {
        private List<Task> getExpectedSortByDateTime(SortOrder sortOrder) {
            return (sortOrder == SortOrder.ASCENDING)
                    ? List.of(event1, deadline1, event2, deadline2, todo1, todo2)
                    : List.of(deadline2, event2, deadline1, event1, todo1, todo2);
        }

        private List<Task> getExpectedSortByPriority(SortOrder sortOrder) {
            return (sortOrder == SortOrder.ASCENDING)
                    ? List.of(todo1, deadline2, event1, todo2, deadline1, event2)
                    : List.of(todo2, deadline1, event2, event1, todo1, deadline2);
        }

        private List<Task> getExpectedSortByType(SortOrder sortOrder) {
            return (sortOrder == SortOrder.ASCENDING)
                    ? List.of(todo1, todo2, deadline1, deadline2, event1, event2)
                    : List.of(event1, event2, deadline1, deadline2, todo1, todo2);
        }

        @Order(1)
        @Test
        void sortTaskByType_ascendingOrder_tasksSortedByTypeAscending() {
            taskList.sortTaskByType(SortOrder.ASCENDING);
            List<Task> sortedTasks = taskList.getAllTask();
            assertEquals(getExpectedSortByType(SortOrder.ASCENDING), sortedTasks);
        }

        @Order(2)
        @Test
        void sortTaskByType_descendingOrder_tasksSortedByTypeDescending() {
            taskList.sortTaskByType(SortOrder.DESCENDING);
            List<Task> sortedTasks = taskList.getAllTask();
            assertEquals(getExpectedSortByType(SortOrder.DESCENDING), sortedTasks);
        }

        @Order(3)
        @Test
        void sortTaskByPriority_ascendingOrder_tasksSortedByPriorityAscending() {
            taskList.sortTaskByPriority(SortOrder.ASCENDING);
            List<Task> sortedTasks = taskList.getAllTask();
            assertEquals(getExpectedSortByPriority(SortOrder.ASCENDING), sortedTasks);
        }

        @Order(4)
        @Test
        void sortTaskByPriority_descendingOrder_tasksSortedByPriorityDescending() {
            taskList.sortTaskByPriority(SortOrder.DESCENDING);
            List<Task> sortedTasks = taskList.getAllTask();
            assertEquals(getExpectedSortByPriority(SortOrder.DESCENDING), sortedTasks);
        }

        @Order(5)
        @Test
        void sortTaskByDateTime_ascendingOrder_tasksSortedByDateTimeAscending() {
            taskList.sortTaskByDateTime(SortOrder.ASCENDING);
            List<Task> sortedTasks = taskList.getAllTask();
            assertEquals(getExpectedSortByDateTime(SortOrder.ASCENDING), sortedTasks);
        }

        @Order(6)
        @Test
        void sortTaskByDateTime_descendingOrder_tasksSortedByDateTimeDescending() {
            taskList.sortTaskByDateTime(SortOrder.DESCENDING);
            List<Task> sortedTasks = taskList.getAllTask();
            assertEquals(getExpectedSortByDateTime(SortOrder.DESCENDING), sortedTasks);
        }
    }

    @BeforeEach
    void setup() throws CommandOperationException {
        taskList = new TaskList();
        todo1 = new Todo("Read book", false, TaskPriority.LOW);
        todo2 = new Todo("Complete assignment", true, TaskPriority.HIGH);
        deadline1 = new Deadline("Submit report",
                LocalDateTime.of(2024, 11, 5, 23, 59),
                true, TaskPriority.HIGH);
        deadline2 = new Deadline("Start project",
                LocalDateTime.of(2024, 12, 10, 12, 0),
                false, TaskPriority.LOW);
        event1 = new Event("Attend workshop",
                LocalDateTime.of(2024, 11, 5, 9, 0),
                LocalDateTime.of(2024, 11, 5, 17, 0),
                false, TaskPriority.MEDIUM);
        event2 = new Event("Meeting",
                LocalDateTime.of(2024, 11, 10, 8, 0),
                LocalDateTime.of(2024, 11, 10, 18, 0),
                true, TaskPriority.HIGH);
        taskList.addTask(todo1);
        taskList.addTask(todo2);
        taskList.addTask(deadline1);
        taskList.addTask(deadline2);
        taskList.addTask(event1);
        taskList.addTask(event2);
    }
}
