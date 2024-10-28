package checkbot.task;

import checkbot.exception.EmptyInputException;
import checkbot.exception.EmptyTimeException;
import checkbot.utils.Messages;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    Task testEvent = new Event("Event description",
            LocalDateTime.of(2024,10,22,10,0),
            LocalDateTime.of(2024,10,22,22,0));

    Task testDeadline = new Deadline("Deadline description",
            LocalDateTime.of(2025,10,22,15,0));

    @Test
    public void testAddEvent() {
        try {
            TaskList.addTask("Event Event description /from 22102024 1000 /to 22102024 2200");
        } catch (EmptyInputException e) {
            System.out.println(Messages.EMPTY_DESCRIPTION);
        } catch (EmptyTimeException e) {
            System.out.println(Messages.EMPTY_TIME);
        }
        assertEquals(testEvent,TaskList.tasks.get(0));
    }

    @Test
    public void testAddDeadline() {
        try {
            TaskList.addTask("Deadline Deadline description /by 22102025 1500");
        } catch (EmptyInputException e) {
            System.out.println(Messages.EMPTY_DESCRIPTION);
        } catch (EmptyTimeException e) {
            System.out.println(Messages.EMPTY_TIME);
        }
        assertEquals(testDeadline,TaskList.tasks.get(1));
    }
}
