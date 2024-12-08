package denny.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {
    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task("Sample task");
    }

    @Test
    void testMarkAsDone() {
        // Test the initial state
        assertEquals(false, task.isDone());

        // Mark the task as done
        task.markAsDone();
        assertEquals(true, task.isDone());
    }

    @Test
    void testToString() {
        // Test the default toString output
        assertEquals("[ ] Sample task", task.toString());

        // Mark the task as done and test toString output again
        task.markAsDone();
        assertEquals("[X] Sample task", task.toString());
    }
}
