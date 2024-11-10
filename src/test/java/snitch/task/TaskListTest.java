package snitch.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TaskListTest {

    @Test
    public void addTask_validTask_taskAdded() {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Test Todo");
        taskList.add(todo);
        assertEquals(1, taskList.size());
        assertEquals(todo, taskList.get(0));
    }

    @Test
    public void removeTask_validIndex_taskRemoved() {
        TaskList taskList = new TaskList();
        Task todo = new Todo("Test Todo");
        taskList.add(todo);
        Task removedTask = taskList.remove(0);
        assertEquals(todo, removedTask);
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void getTask_invalidIndex_throwsException() {
        TaskList taskList = new TaskList();
        assertThrows(AssertionError.class, () -> taskList.get(0));
    }
}