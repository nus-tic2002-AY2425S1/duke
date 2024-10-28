package checkbot.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    Task testTodo = new Todo("Todo description");

    Task testDeadline = new Deadline("Deadline description",
            LocalDateTime.of(2025,10,22,15,0));

    Task testEvent = new Event("Event description",
            LocalDateTime.of(2024,10,22,10,0),
            LocalDateTime.of(2024,10,22,22,0));

    @Test
    public void testGetDescription() {
        assertEquals("Todo description", testTodo.getDescription());
        assertEquals("Deadline description", testDeadline.getDescription());
        assertEquals("Event description", testEvent.getDescription());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals(" ", testTodo.getStatusIcon());
        assertEquals(" ", testDeadline.getStatusIcon());
        assertEquals(" ", testEvent.getStatusIcon());
    }

    @Test
    public void testGetTaskIcon() {
        assertEquals("T", testTodo.getTaskIcon());
        assertEquals("D", testDeadline.getTaskIcon());
        assertEquals("E", testEvent.getTaskIcon());
    }

    @Test
    public void testGetPriorityIcon() {
        assertEquals("   ", testTodo.getPriorityIcon());
        assertEquals("   ", testDeadline.getPriorityIcon());
        assertEquals("   ", testEvent.getPriorityIcon());
    }

    @Test
    public void testGetPriorityString() {
        assertEquals("NOT SET", testTodo.getPriorityString());
        assertEquals("NOT SET", testDeadline.getPriorityString());
        assertEquals("NOT SET", testEvent.getPriorityString());
    }

    @Test
    public void testSetDone() {
        testTodo.setDone(true);
        testDeadline.setDone(true);
        testEvent.setDone(true);

        assertEquals(TaskStatus.DONE, testTodo.status);
        assertEquals(TaskStatus.DONE, testDeadline.status);
        assertEquals(TaskStatus.DONE, testEvent.status);
    }

    @Test
    public void testSetUndone() {
        testTodo.setDone(false);
        testDeadline.setDone(false);
        testEvent.setDone(false);

        assertEquals(TaskStatus.NOT_DONE, testTodo.status);
        assertEquals(TaskStatus.NOT_DONE, testDeadline.status);
        assertEquals(TaskStatus.NOT_DONE, testEvent.status);
    }

    @Test
    public void testSetPriority_high() {
        testTodo.setPriority(TaskPriority.HIGH);
        testDeadline.setPriority(TaskPriority.HIGH);
        testEvent.setPriority(TaskPriority.HIGH);

        assertEquals(TaskPriority.HIGH, testTodo.priority);
        assertEquals(TaskPriority.HIGH, testDeadline.priority);
        assertEquals(TaskPriority.HIGH, testEvent.priority);
        assertEquals("!!!", testTodo.getPriorityIcon());
        assertEquals("!!!", testDeadline.getPriorityIcon());
        assertEquals("!!!", testEvent.getPriorityIcon());
        assertEquals("HIGH", testTodo.getPriorityString());
        assertEquals("HIGH", testDeadline.getPriorityString());
        assertEquals("HIGH", testEvent.getPriorityString());
    }

    @Test
    public void testSetPriority_medium() {
        testTodo.setPriority(TaskPriority.MEDIUM);
        testDeadline.setPriority(TaskPriority.MEDIUM);
        testEvent.setPriority(TaskPriority.MEDIUM);

        assertEquals(TaskPriority.MEDIUM, testTodo.priority);
        assertEquals(TaskPriority.MEDIUM, testDeadline.priority);
        assertEquals(TaskPriority.MEDIUM, testEvent.priority);
        assertEquals(" !!", testTodo.getPriorityIcon());
        assertEquals(" !!", testDeadline.getPriorityIcon());
        assertEquals(" !!", testEvent.getPriorityIcon());
        assertEquals("MEDIUM", testTodo.getPriorityString());
        assertEquals("MEDIUM", testDeadline.getPriorityString());
        assertEquals("MEDIUM", testEvent.getPriorityString());
    }

    @Test
    public void testSetPriority_low() {
        testTodo.setPriority(TaskPriority.LOW);
        testDeadline.setPriority(TaskPriority.LOW);
        testEvent.setPriority(TaskPriority.LOW);

        assertEquals(TaskPriority.LOW, testTodo.priority);
        assertEquals(TaskPriority.LOW, testDeadline.priority);
        assertEquals(TaskPriority.LOW, testEvent.priority);
        assertEquals("  !", testTodo.getPriorityIcon());
        assertEquals("  !", testDeadline.getPriorityIcon());
        assertEquals("  !", testEvent.getPriorityIcon());
        assertEquals("LOW", testTodo.getPriorityString());
        assertEquals("LOW", testDeadline.getPriorityString());
        assertEquals("LOW", testEvent.getPriorityString());
    }

    @Test
    public void testSetPriority_notSet() {
        testTodo.setPriority(TaskPriority.NOT_SET);
        testDeadline.setPriority(TaskPriority.NOT_SET);
        testEvent.setPriority(TaskPriority.NOT_SET);

        assertEquals(TaskPriority.NOT_SET, testTodo.priority);
        assertEquals(TaskPriority.NOT_SET, testDeadline.priority);
        assertEquals(TaskPriority.NOT_SET, testEvent.priority);
        assertEquals("   ", testTodo.getPriorityIcon());
        assertEquals("   ", testDeadline.getPriorityIcon());
        assertEquals("   ", testEvent.getPriorityIcon());
        assertEquals("NOT SET", testTodo.getPriorityString());
        assertEquals("NOT SET", testDeadline.getPriorityString());
        assertEquals("NOT SET", testEvent.getPriorityString());
    }
}
