package checkbot.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    // instantiate a task
    Task testTask = new Event("Event description",
            LocalDateTime.of(2024,10,22,10,0),
            LocalDateTime.of(2024,10,22,22,0));

    @Test
    public void testGetDescription() {
        assertEquals("Task description", testTask.getDescription());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", testTask.getStatusIcon());
    }

    @Test
    public void testGetTaskIcon() {
        assertEquals("E", testTask.getTaskIcon());
    }
}
