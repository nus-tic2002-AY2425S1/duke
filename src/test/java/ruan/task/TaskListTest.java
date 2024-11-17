package ruan.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ruan.exception.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * With the use of ChatGPT to come out of test cases
 */

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void addTask_validTask_taskAdded() {
        Task task = new Todo("Write code");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals("[T][ ] Write code", taskList.getTaskDescription(0));
    }

    @Test
    public void deleteTask_validIndex_taskDeleted() throws RuanException {
        Task task = new Todo("Write code");
        taskList.addTask(task);
        taskList.deleteTask(0);
        assertEquals(0, taskList.size());
    }

    @Test
    public void deleteTask_invalidIndex_throwsException() {
        assertThrows(RuanException.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void setDone_markTask_taskMarkedAsDone() throws RuanException {
        Task task = new Todo("Write code");
        taskList.addTask(task);
        taskList.setDone(0, true);

        assertEquals("[T][X] Write code", taskList.getTaskDescription(0));
    }

    @Test
    public void setDone_unmarkTask_taskMarkedAsNotDone() throws RuanException {
        Task task = new Todo("Write code");
        taskList.addTask(task);
        taskList.setDone(0, true);
        taskList.setDone(0, false);

        assertEquals("[T][ ] Write code", taskList.getTaskDescription(0));
    }

    @Test
    public void setDone_invalidIndex_throwsException() {
        assertThrows(RuanException.class, () -> taskList.setDone(0, true));
    }

    @Test
    public void getTaskDescription_validIndex_returnsDescription() {
        Task task = new Todo("Write code");
        taskList.addTask(task);

        assertEquals("[T][ ] Write code", taskList.getTaskDescription(0));
    }

    @Test
    public void getTaskDescription_invalidIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.getTaskDescription(0));
    }

    @Test
    public void addTask_duplicateTask_taskNotAdded() {
        Task task = new Todo("Write code");
        taskList.addTask(task);
        assertEquals(1, taskList.size());
        
        taskList.addTask(task);
        assertEquals(1, taskList.size());
    }

    @Test
    public void findTasks_existingKeyword_returnsMatchingTasks() throws RuanException {
        Task task1 = new Todo("Write code");
        Task task2 = new Todo("Read book");
        Task task3 = new Todo("Write tests");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        ArrayList<Task> matchingTasks = taskList.findTasks("Write");
        assertEquals(2, matchingTasks.size());
        assertEquals("[T][ ] Write code", matchingTasks.get(0).toString());
        assertEquals("[T][ ] Write tests", matchingTasks.get(1).toString());
    }

    @Test
    public void findTasks_nonExistingKeyword_returnsEmptyList() throws RuanException {
        Task task1 = new Todo("Write code");
        Task task2 = new Todo("Read book");
        taskList.addTask(task1);
        taskList.addTask(task2);

        ArrayList<Task> matchingTasks = taskList.findTasks("Nonexistent");
        assertTrue(matchingTasks.isEmpty());
    }

    @Test
    public void getTasksCompletedInPastWeek_noTasksCompleted_returnsZero() {
        assertEquals(0, taskList.getTasksCompletedInPastWeek());
    }

    @Test
    public void getTotalCompletedTasks_noTasksCompleted_returnsZero() {
        assertEquals(0, taskList.getTotalCompletedTasks());
    }

    @Test
    public void getTotalPendingTasks_noTasksAdded_returnsZero() {
        assertEquals(0, taskList.getTotalPendingTasks());
    }

    @Test
    public void getTotalPendingTasks_tasksAdded_returnsCorrectCount() throws RuanException {
        Task task1 = new Todo("Write code");
        Task task2 = new Todo("Read book");
        taskList.addTask(task1);
        taskList.addTask(task2);
        assertEquals(2, taskList.getTotalPendingTasks());
    }
}
