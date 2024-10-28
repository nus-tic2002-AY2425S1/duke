package checkbot.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    // instantiate a task
    Task testTask = new Event("Task description",
            "22102024 1000",
            "22102024 2200");

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
