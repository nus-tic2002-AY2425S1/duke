package storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import task.Task;
import task.TaskList;
import util.TypicalTasks;

public class TaskListEncoderTest {
    
    private TaskList taskList;
    
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void encodeTaskList_emptyTaskList_returnsEmptyList() {
        List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
        assertTrue(encodedTaskList.isEmpty(), "Expected an empty list for an empty task list");
    }

    @Test
    public void encodeTaskList_singleTask_returnsEncodedTask() {
        TypicalTasks typicalTasks = new TypicalTasks();
        Task task = typicalTasks.todo_buyGroceries;
        
        taskList.addTask(task);
        
        List<String> encodedTaskList = TaskListEncoder.encodeTaskList(taskList);
        
        assertEquals(taskList.getSize(), encodedTaskList.size(), "Expected a list with one encoded task");
        assertEquals("T | 0 | Buy groceries", encodedTaskList.get(0), 
            "Expected the correct encoded string for the task");
    }

    @Test
    public void encodeTaskList_multipleTasks_returnsEncodedTasks() {
        TypicalTasks typicalTasks = new TypicalTasks();
        Task task1 = typicalTasks.deadline_submitReport;
        Task task2 = typicalTasks.deadline_resolveCustomerTicket;

        taskList.addTask(task1);
        taskList.addTask(task2);
        
        List<String> result = TaskListEncoder.encodeTaskList(taskList);
        
        assertEquals(taskList.getSize(), result.size(), "Expected a list with two encoded tasks");
        assertEquals("D | 0 | Submit report | Apr 24 2021 1433", result.get(0), 
            "Expected the correct encoded string for the first deadline task");
        assertEquals("D | 1 | Resolve customer ticket | Aug 04 2022 0915", result.get(1), 
            "Expected the correct encoded string for the second deadline task");
    }

}
