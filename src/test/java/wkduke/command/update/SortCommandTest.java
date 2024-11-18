package wkduke.command.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wkduke.exception.command.CommandOperationException;
import wkduke.exception.storage.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.Deadline;
import wkduke.task.Event;
import wkduke.task.Task;
import wkduke.task.TaskList;
import wkduke.task.TaskPriority;
import wkduke.task.Todo;
import wkduke.ui.Ui;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Solution below inspired by https://www.vogella.com/tutorials/Mockito/article.html
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class SortCommandTest {
    @Mock
    private Ui ui;
    @Mock
    private Storage storage;

    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ValidTests {
        private TaskList taskList;
        private Todo todo1, todo2;
        private Deadline deadline1, deadline2;
        private Event event1, event2;

        @BeforeEach
        void setup() {
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

        @Order(5)
        @Test
        void sortByDateTime_ascendingOrder() throws StorageOperationException, CommandOperationException {
            SortCommand command = new SortByDateTimeCommand(SortOrder.ASCENDING);
            command.execute(taskList, ui, storage);

            List<Task> expectedOrder = List.of(event1, deadline1, event2, deadline2, todo1, todo2);
            assertEquals(expectedOrder, taskList.getTasks());
        }

        @Order(6)
        @Test
        void sortByDateTime_descendingOrder() throws StorageOperationException, CommandOperationException {
            SortCommand command = new SortByDateTimeCommand(SortOrder.DESCENDING);
            command.execute(taskList, ui, storage);

            List<Task> expectedOrder = List.of(deadline2, event2, deadline1, event1, todo1, todo2);
            assertEquals(expectedOrder, taskList.getTasks());
        }

        @Order(3)
        @Test
        void sortByPriority_ascendingOrder() throws StorageOperationException, CommandOperationException {
            SortCommand command = new SortByPriorityCommand(SortOrder.ASCENDING);
            command.execute(taskList, ui, storage);

            List<Task> expectedOrder = List.of(todo1, deadline2, event1, todo2, deadline1, event2);
            assertEquals(expectedOrder, taskList.getTasks());
        }

        @Order(4)
        @Test
        void sortByPriority_descendingOrder() throws StorageOperationException, CommandOperationException {
            SortCommand command = new SortByPriorityCommand(SortOrder.DESCENDING);
            command.execute(taskList, ui, storage);

            List<Task> expectedOrder = List.of(todo2, deadline1, event2, event1, todo1, deadline2);
            assertEquals(expectedOrder, taskList.getTasks());
        }

        @Order(1)
        @Test
        void sortByType_ascendingOrder() throws StorageOperationException, CommandOperationException {
            SortCommand command = new SortByTaskTypeCommand(SortOrder.ASCENDING);
            command.execute(taskList, ui, storage);

            List<Task> expectedOrder = List.of(todo1, todo2, deadline1, deadline2, event1, event2);
            assertEquals(expectedOrder, taskList.getTasks());
        }

        @Order(2)
        @Test
        void sortByType_descendingOrder() throws StorageOperationException, CommandOperationException {
            SortCommand command = new SortByTaskTypeCommand(SortOrder.DESCENDING);
            command.execute(taskList, ui, storage);

            List<Task> expectedOrder = List.of(event1, event2, deadline1, deadline2, todo1, todo2);
            assertEquals(expectedOrder, taskList.getTasks());
        }
    }

}
