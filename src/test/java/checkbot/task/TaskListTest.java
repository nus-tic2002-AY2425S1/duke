package checkbot.task;

import checkbot.exception.CommandNotFoundException;
import checkbot.exception.InvalidInputException;
import checkbot.utils.Messages;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void testAddTodo() {
        Todo todo = TaskList.addTodo("Todo description");
        assertEquals("Todo description", todo.getDescription());
        assertEquals(TaskStatus.NOT_DONE, todo.status);
        assertEquals(TaskPriority.NOT_SET, todo.priority);
    }

    @Test
    public void testAddDeadline() {
        try {
            Deadline deadline = TaskList.addDeadline("Deadline description /by 28/10/2024 2340");
            assertEquals("Deadline description", deadline.getDescription());
            assertEquals(LocalDateTime.of(2024,10,28,23,40), deadline.dueDateTime);
            assertEquals(TaskStatus.NOT_DONE, deadline.status);
            assertEquals(TaskPriority.NOT_SET, deadline.priority);
        } catch (Exception e) {
            System.out.println("Exception Caught");
        }
    }

    @Test
    public void testAddDeadline_emptyInput() {
        Throwable exception = assertThrows(InvalidInputException.class, () -> TaskList.addDeadline("/by 28/10/2024 2340"));
        assertEquals(exception.getMessage(),Messages.EMPTY_DESCRIPTION);
    }

    @Test
    public void testAddDeadline_emptyTime() {
        Throwable exception = assertThrows(InvalidInputException.class, () -> TaskList.addDeadline("Deadline description /by"));
        assertEquals(exception.getMessage(),Messages.EMPTY_TIME);
    }

    @Test
    public void testAddDeadline_commandNotFound() {
        Throwable exception = assertThrows(CommandNotFoundException.class, () -> TaskList.addDeadline("Deadline description by 28/10/2024 2340"));
        assertEquals(exception.getMessage(),Messages.DEADLINE_ERROR);
    }

    @Test
    public void testAddDeadline_invalidDate_numberFormat() {
        assertThrows(NumberFormatException.class, () -> TaskList.addDeadline("Deadline description /by 14oct"));
        assertThrows(NumberFormatException.class, () -> TaskList.addDeadline("Deadline description /by 14oct2024"));
        assertThrows(NumberFormatException.class, () -> TaskList.addDeadline("Deadline description /by 14-10-2024"));
    }

    @Test
    public void testAddDeadline_invalidDate_idxOOB() {
        assertThrows(IndexOutOfBoundsException.class, () -> TaskList.addDeadline("Deadline description /by 14102024"));
        assertThrows(IndexOutOfBoundsException.class, () -> TaskList.addDeadline("Deadline description /by 14 oct"));
        assertThrows(IndexOutOfBoundsException.class, () -> TaskList.addDeadline("Deadline description /by 14 october 2024"));
    }

    @Test
    public void testAddDeadline_dateTimeException() {
        assertThrows(DateTimeException.class, () -> TaskList.addDeadline("Deadline description /by 12/25/2025"));
        assertThrows(DateTimeException.class, () -> TaskList.addDeadline("Deadline description /by 25/12/2025 2400"));
        assertThrows(DateTimeException.class, () -> TaskList.addDeadline("Deadline description /by 31/2/2024 1500"));
    }
}
