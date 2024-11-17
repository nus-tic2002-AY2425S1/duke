package KLBot.TaskList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    public void testMarkAsCompleted() {
        Task task = new ToDo("Finish homework");

        assertFalse(task.getStatusIcon().equals("X"));

        task.markAsCompleted();

        assertTrue(task.getStatusIcon().equals("X"));
    }

    @Test
    public void testFromFileFormat() {
        String taskDataToDo = "T | 0 | Finish homework";
        String taskDataDeadline = "D | 1 | Return book | 2023-10-15";
        String taskDataEvent = "E | 0 | Project meeting | 2023-10-15T10:00 | 2023-10-15T12:00";

        Task toDoTask = Task.fromFileFormat(taskDataToDo);
        assertNotNull(toDoTask);
        assertEquals("Finish homework", toDoTask.getDescription());
        assertFalse(toDoTask.getStatusIcon().equals("X"));

        Task deadlineTask = Task.fromFileFormat(taskDataDeadline);
        assertNotNull(deadlineTask);
        assertEquals("Return book (by: Oct 15 2023)", deadlineTask.getDescription());
        assertTrue(deadlineTask.getStatusIcon().equals("X")); // Completed task

        Task eventTask = Task.fromFileFormat(taskDataEvent);
        assertNotNull(eventTask);
        assertEquals("Project meeting (from: Oct 15 2023 10:00 to: Oct 15 2023 12:00)", eventTask.getDescription());

        Event event = (Event) eventTask; // Casting to Event
        assertEquals("2023-10-15T10:00", event.fromDateTime.toString());
        assertEquals("2023-10-15T12:00", event.toDateTime.toString());
    }
}
