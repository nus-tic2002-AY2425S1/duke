package checkbot.task;

import checkbot.exception.CommandNotFoundException;
import checkbot.exception.InvalidInputException;
import checkbot.utils.Messages;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {
    @Test
    public void testAddTodo() {
        try {
            Todo todo = TaskList.addTodo("Todo description");
            assertEquals("Todo description", todo.getDescription());
            assertEquals(TaskStatus.NOT_DONE, todo.status);
            assertEquals(TaskPriority.NOT_SET, todo.priority);
        } catch (Exception e) {
            System.out.println("Exception Caught");
        }
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
        Throwable exception = assertThrows(InvalidInputException.class, () ->
                TaskList.addDeadline("/by 28/10/2024 2340"));
        assertEquals(exception.getMessage(),Messages.EMPTY_DESCRIPTION);
    }

    @Test
    public void testAddDeadline_emptyTime() {
        Throwable exception = assertThrows(InvalidInputException.class, () ->
                TaskList.addDeadline("Deadline description /by"));
        assertEquals(exception.getMessage(),Messages.EMPTY_TIME);
    }

    @Test
    public void testAddDeadline_commandNotFound() {
        Throwable exception = assertThrows(CommandNotFoundException.class, () ->
                TaskList.addDeadline("Deadline description by 28/10/2024 2340"));
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
        assertThrows(IndexOutOfBoundsException.class, () ->
                TaskList.addDeadline("Deadline description /by 14102024"));
        assertThrows(IndexOutOfBoundsException.class, () ->
                TaskList.addDeadline("Deadline description /by 14 oct"));
        assertThrows(IndexOutOfBoundsException.class, () ->
                TaskList.addDeadline("Deadline description /by 14 october 2024"));
    }

    @Test
    public void testAddDeadline_dateTimeException() {
        assertThrows(DateTimeException.class, () -> TaskList.addDeadline("Deadline description /by 12/25/2025"));
        assertThrows(DateTimeException.class, () -> TaskList.addDeadline("Deadline description /by 25/12/2025 2400"));
        assertThrows(DateTimeException.class, () -> TaskList.addDeadline("Deadline description /by 31/2/2024 1500"));
    }

    @Test
    public void testAddEvent() {
        try {
            Event event = TaskList.addEvent("Event description /from 28/10/2024 2340 /to 29/10/2024 0500");
            assertEquals("Event description", event.getDescription());
            assertEquals(LocalDateTime.of(2024,10,28,23,40), event.startDateTime);
            assertEquals(LocalDateTime.of(2024,10,29,5,0), event.endDateTime);
            assertEquals(TaskStatus.NOT_DONE, event.status);
            assertEquals(TaskPriority.NOT_SET, event.priority);
        } catch (Exception e) {
            System.out.println("Exception Caught");
        }
    }

    @Test
    public void testAddEvent_emptyInput() {
        Throwable exception = assertThrows(InvalidInputException.class, () ->
                TaskList.addEvent("/from 28/10/2024 2340 /to 29/10/2024 0500"));
        assertEquals(exception.getMessage(),Messages.EMPTY_DESCRIPTION);
    }

    @Test
    public void testAddEvent_emptyTime() {
        Throwable exception = assertThrows(InvalidInputException.class, () ->
                TaskList.addEvent("Event description /from /to"));
        assertEquals(exception.getMessage(),Messages.EMPTY_TIME);
    }

    @Test
    public void testAddEvent_commandNotFound() {
        Throwable exception = assertThrows(CommandNotFoundException.class, () ->
                TaskList.addEvent("Event description from 28/10/2024 2340 to 29/10/2024 0500"));
        assertEquals(exception.getMessage(),Messages.EVENT_ERROR);
    }

    @Test
    public void testAddEvent_invalidDate_numberFormat() {
        assertThrows(NumberFormatException.class, () ->
                TaskList.addEvent("Event description /from 14oct /to 28oct"));
        assertThrows(NumberFormatException.class, () ->
                TaskList.addEvent("Event description /from 14oct2024 /to 28oct2024"));
        assertThrows(NumberFormatException.class, () ->
                TaskList.addEvent("Event description /from 14-10-2024 /to 28-10-2024"));
    }

    @Test
    public void testAddEvent_invalidDate_idxOOB() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                TaskList.addEvent("Event description /from 14102024 /to 28102024"));
        assertThrows(IndexOutOfBoundsException.class, () ->
                TaskList.addEvent("Event description /from 14 oct /to 28 oct"));
        assertThrows(IndexOutOfBoundsException.class, () ->
                TaskList.addEvent("Event description /from 14 october 2024 /to 28 october 2024"));
    }

    @Test
    public void testAddEvent_dateTimeException() {
        assertThrows(DateTimeException.class, () ->
                TaskList.addEvent("Event description /from 12/25/2025 /to 12/28/2025"));
        assertThrows(DateTimeException.class, () ->
                TaskList.addEvent("Event description /from 25/12/2025 2400 /to 28/12/2025 0100"));
        assertThrows(DateTimeException.class, () ->
                TaskList.addEvent("Event description /from 31/2/2024 1500 /to 31/4/2024 1000"));
    }
}
