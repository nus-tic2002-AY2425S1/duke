package denny.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Test task");
    }

    @Test
    void testTaskCreation() {
        assertEquals("Test task", task.getDescription());
        assertFalse(task.isDone());
        assertNotNull(task.getCreatedAt());
    }
}
