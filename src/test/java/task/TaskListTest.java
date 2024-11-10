package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.TypicalTasks;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskListTest {
    
    private TypicalTasks typicalTasks = new TypicalTasks();
    private Todo todo_doHomework;
    private Todo todo_buyGroceries;
    private Todo todo_washDishes;

    private Deadline deadline_submitReport;
    private Deadline deadline_resolveCustomerTicket;
    // private Deadline deadline_finalizeGuestList;

    private Event event_projectLaunch;
    private Event event_birthday;
    // private Event event_anniversary;

    private TaskList emptyTaskList;
    private TaskList defaultTaskList;

    @BeforeEach
    public void setUp() {

        defaultTaskList = typicalTasks.initDefaultTaskList();

        todo_doHomework = typicalTasks.todo_doHomework;
        todo_buyGroceries = typicalTasks.todo_buyGroceries;
        todo_washDishes = typicalTasks.todo_washDishes;
        deadline_submitReport = typicalTasks.deadline_submitReport;
        deadline_resolveCustomerTicket = typicalTasks.deadline_resolveCustomerTicket;
        event_projectLaunch = typicalTasks.event_projectLaunch;
        event_birthday = typicalTasks.event_birthday;

        emptyTaskList = new TaskList();
        
    }

    @Test
    public void isEmpty_success() {
        assertEquals(0, emptyTaskList.getSize(), "Empty task list should contain 0 tasks.");
    }

    public void addTask_success(Task task) {
        assertNotNull(task);
        int numberOfTasksBeforeAddition = defaultTaskList.getSize();
        defaultTaskList.addTask(task);
        int numberOfTasksAfterAddition = defaultTaskList.getSize();
        assertEquals(numberOfTasksBeforeAddition, numberOfTasksAfterAddition - 1, "Task list should contain one more task after adding a new task");
    }

    @Test
    public void addTask_null_fail() {
        Task task = null;
        int numberOfTasksBeforeAddition = defaultTaskList.getSize();
        defaultTaskList.addTask(task);
        int numberOfTasksAfterAddition = defaultTaskList.getSize();
        assertEquals(numberOfTasksBeforeAddition, numberOfTasksAfterAddition, "Size should not change after adding a null task");
    }

    @Test
    public void addTask_todo_success() {
        addTask_success(todo_buyGroceries);
    }

    @Test
    public void addTask_deadline_success() {
        addTask_success(deadline_resolveCustomerTicket);
    }

    @Test
    public void addTask_event_success() {
        addTask_success(event_birthday);
    }

    public void removeTask_success(Task task) {
        assertNotNull(defaultTaskList);
        assertTrue(defaultTaskList.contains(task));
        int numberOfTasksBeforeRemoval = defaultTaskList.getSize();
        assertTrue(defaultTaskList.removeTask(task), "Task should be successfully removed");
        int numberOfTasksAfterRemoval = defaultTaskList.getSize();
        assertEquals(numberOfTasksBeforeRemoval - 1, numberOfTasksAfterRemoval, "Task list should contain one less task after removing a task");
    }

    @Test
    public void removeTask_todo_success() {
        removeTask_success(todo_buyGroceries);
    }

    @Test
    public void removeTask_deadline_success() {
        removeTask_success(deadline_resolveCustomerTicket);
    }

    @Test
    public void removeTask_event_success() {
        removeTask_success(event_birthday);
    }

    @Test
    public void removeTask_event_nonExistentTask() {
        assertFalse(defaultTaskList.removeTask(todo_washDishes), 
            "Removing a non-existent task should return false.");
    }

    public void markTask_success(Task task, int taskIndex) {
        assertNotNull(task);
        if (task.getIsDone()) {
            assertFalse(defaultTaskList.markTask(taskIndex));
        } else {
            assertTrue(defaultTaskList.markTask(taskIndex));
        }
    }

    @Test
    public void markTask_todo() {
        assertTrue(todo_doHomework.getIsDone());
        markTask_success(todo_doHomework, 0);

        assertFalse(todo_buyGroceries.getIsDone());
        markTask_success(todo_buyGroceries, 1);
    }

    @Test
    public void markTask_deadline() {
        assertFalse(deadline_submitReport.getIsDone());
        markTask_success(deadline_submitReport, 2);
        
        assertTrue(deadline_resolveCustomerTicket.getIsDone());
        markTask_success(deadline_resolveCustomerTicket, 3);

    }

    @Test
    public void markTask_event() {
        assertTrue(event_projectLaunch.getIsDone());
        markTask_success(event_projectLaunch, 4);
        
        assertFalse(event_birthday.getIsDone());
        markTask_success(event_birthday, 5);
    }

    public void unmarkTask_success(Task task, int taskIndex) {
        assertNotNull(task);
        if (task.getIsDone() == false) {
            assertFalse(defaultTaskList.unmarkTask(taskIndex));
        } else {
            assertTrue(defaultTaskList.unmarkTask(taskIndex));
        }
    }

    @Test
    public void unmarkTask_todo() {
        assertTrue(todo_doHomework.getIsDone());
        unmarkTask_success(todo_doHomework, 0);

        assertFalse(todo_buyGroceries.getIsDone());
        unmarkTask_success(todo_buyGroceries, 1);
    }

    @Test
    public void unmarkTask_deadline() {
        assertFalse(deadline_submitReport.getIsDone());
        unmarkTask_success(deadline_submitReport, 2);

        assertTrue(deadline_resolveCustomerTicket.getIsDone());
        unmarkTask_success(deadline_resolveCustomerTicket, 3);
    }

    @Test
    public void unmarkTask_event() {
        assertTrue(event_projectLaunch.getIsDone());
        unmarkTask_success(event_projectLaunch, 4);

        assertFalse(event_birthday.getIsDone());
        unmarkTask_success(event_birthday, 5);
    }

    @Test
    public void getTaskWord_noTasks() {
        assertTrue(emptyTaskList.isEmpty(), "Task list has no tasks");
        assertEquals(" task", emptyTaskList.getTaskWord());
    }

    @Test
    public void getTaskWord_singleTask() {
        defaultTaskList = new TaskList();
        defaultTaskList.addTask(todo_doHomework);
        assertEquals(defaultTaskList.getSize(), 1);
        assertEquals(" task", defaultTaskList.getTaskWord());
    }

    @Test
    public void getTaskWord_multipleTasks() {
        assertTrue(defaultTaskList.getSize() > 1, "Task list has more than one task");
        assertEquals(" tasks", defaultTaskList.getTaskWord());
    }

    @Test
    public void testGetAllTasksOnDate_EmptyList() {
        LocalDate date = LocalDate.of(2023, 10, 31);
        List<Task> tasksOnDate = defaultTaskList.getAllTasksOnDate(date);
        assertTrue(tasksOnDate.isEmpty());
    }

    @Test
    public void testGetAllTasksOnDate_WithTasks() {
        LocalDate date = LocalDate.of(2024, 11, 9);

        List<Task> tasksOnDate = defaultTaskList.getAllTasksOnDate(date);

        assertEquals(2, tasksOnDate.size());
        assertTrue(tasksOnDate.contains(event_projectLaunch));
        assertTrue(tasksOnDate.contains(event_birthday));
        assertFalse(tasksOnDate.contains(deadline_submitReport));
        assertFalse(tasksOnDate.contains(todo_doHomework));
    }


}
