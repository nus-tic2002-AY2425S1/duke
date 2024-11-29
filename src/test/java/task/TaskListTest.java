package task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;

import util.TypicalTasks;

public class TaskListTest {
    
    private final TypicalTasks typicalTasks = new TypicalTasks();
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
    public void isEmpty_emptyTaskList_zeroTasks() {
        assertEquals(0, emptyTaskList.getSize(), "Empty task list should contain 0 tasks.");
    }

    @Test
    public void addTask_nullTask_noChangeInSize() {
        Task task = null;
        int numberOfTasksBeforeAddition = defaultTaskList.getSize();
        defaultTaskList.addTask(task);
        int numberOfTasksAfterAddition = defaultTaskList.getSize();
        assertEquals(numberOfTasksBeforeAddition, numberOfTasksAfterAddition,
            "Size should not change after adding a null task");
    }

    public void addTask_success(Task task) {
        assertNotNull(task);
        int numberOfTasksBeforeAddition = defaultTaskList.getSize();
        defaultTaskList.addTask(task);
        int numberOfTasksAfterAddition = defaultTaskList.getSize();
        assertEquals(numberOfTasksBeforeAddition, numberOfTasksAfterAddition - 1,
            "Task list should contain one more task after adding a new task");
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
        assertTrue(defaultTaskList.hasTask(task));
        int numberOfTasksBeforeRemoval = defaultTaskList.getSize();
        assertTrue(defaultTaskList.removeTask(task), "Task should be successfully removed");
        int numberOfTasksAfterRemoval = defaultTaskList.getSize();
        assertEquals(numberOfTasksBeforeRemoval - 1, numberOfTasksAfterRemoval, 
            "Task list should contain one less task after removing a task");
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
    public void removeTask_nonExistentTask_returnsFalse() {
        assertFalse(defaultTaskList.removeTask(todo_washDishes), 
            "Removing a non-existent task should return false.");
    }

    public void markTask_success(Task task) {
        assertNotNull(task);
        if (task.getIsDone()) {
            assertFalse(defaultTaskList.markTask(task));
        } else {
            assertTrue(defaultTaskList.markTask(task));
        }
    }

    @Test
    public void markTask_todo_success() {
        assertTrue(todo_doHomework.getIsDone());
        markTask_success(todo_doHomework);

        assertFalse(todo_buyGroceries.getIsDone());
        markTask_success(todo_buyGroceries);
    }

    @Test
    public void markTask_deadline_success() {
        assertFalse(deadline_submitReport.getIsDone());
        markTask_success(deadline_submitReport);
        
        assertTrue(deadline_resolveCustomerTicket.getIsDone());
        markTask_success(deadline_resolveCustomerTicket);

    }

    @Test
    public void markTask_event_success() {
        assertTrue(event_projectLaunch.getIsDone());
        markTask_success(event_projectLaunch);
        
        assertFalse(event_birthday.getIsDone());
        markTask_success(event_birthday);
    }

    public void unmarkTask_success(Task task) {
        assertNotNull(task);
        if (!task.getIsDone()) {
            assertFalse(defaultTaskList.unmarkTask(task));
        } else {
            assertTrue(defaultTaskList.unmarkTask(task));
        }
    }

    @Test
    public void unmarkTask_todo_success() {
        assertTrue(todo_doHomework.getIsDone());
        unmarkTask_success(todo_doHomework);

        assertFalse(todo_buyGroceries.getIsDone());
        unmarkTask_success(todo_buyGroceries);
    }

    @Test
    public void unmarkTask_deadline_success() {
        assertFalse(deadline_submitReport.getIsDone());
        unmarkTask_success(deadline_submitReport);

        assertTrue(deadline_resolveCustomerTicket.getIsDone());
        unmarkTask_success(deadline_resolveCustomerTicket);
    }

    @Test
    public void unmarkTask_event_success() {
        assertTrue(event_projectLaunch.getIsDone());
        unmarkTask_success(event_projectLaunch);

        assertFalse(event_birthday.getIsDone());
        unmarkTask_success(event_birthday);
    }

    @Test
    public void getTaskWord_noTasks_returnsSingular() {
        assertTrue(emptyTaskList.isEmpty(), "Task list has no tasks");
        assertEquals(" tasks", emptyTaskList.getTaskWord());
    }

    @Test
    public void getTaskWord_singleTask_returnsSingular() {
        defaultTaskList = new TaskList();
        defaultTaskList.addTask(todo_doHomework);
        assertEquals(defaultTaskList.getSize(), 1);
        assertEquals(" task", defaultTaskList.getTaskWord());
    }

    @Test
    public void getTaskWord_multipleTasks_returnsPlural() {
        assertTrue(defaultTaskList.getSize() > 1, "Task list has more than one task");
        assertEquals(" tasks", defaultTaskList.getTaskWord());
    }

    @Test
    public void getScheduledTasksOnDate_emptyList_returnsEmptyList() {
        LocalDate date = LocalDate.of(2023, 10, 31);
        TaskList tasksOnDate = defaultTaskList.getScheduledTasks(date);
        assertTrue(tasksOnDate.isEmpty());
    }

    @Test
    public void getScheduledTasksOnDate_withTasks_returnsCorrectTasks() {
        LocalDate date = LocalDate.of(2024, 11, 9);

        TaskList tasksOnDate = defaultTaskList.getScheduledTasks(date);

        assertEquals(2, tasksOnDate.getSize());
        assertTrue(tasksOnDate.getTaskList().contains(event_projectLaunch));
        assertTrue(tasksOnDate.getTaskList().contains(event_birthday));
        assertFalse(tasksOnDate.getTaskList().contains(deadline_submitReport));
        assertFalse(tasksOnDate.getTaskList().contains(todo_doHomework));
    }

}
