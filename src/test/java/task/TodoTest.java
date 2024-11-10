package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.Constants;
import util.TypicalTasks;

public class TodoTest {
    
    private TypicalTasks typicalTasks;
    private Todo todoDone;
    private String TODO_DONE_DESCRIPTION = "Do homework";
    private Todo todoNotDone;
    private String TODO_NOTDONE_DESCRIPTION = "Buy groceries";

    @BeforeEach
    public void setUp() {
        typicalTasks = new TypicalTasks();
        todoDone = typicalTasks.todo_doHomework;
        todoNotDone = typicalTasks.todo_buyGroceries;
    }

    // Tests the constructor that accepts a completion status to ensure it initializes the object correctly
    @Test
    public void constructor_todoDone() {
        assertEquals(TODO_DONE_DESCRIPTION, todoDone.getDescription());
        assertTrue(todoDone.getIsDone());
    }

    // Tests the constructor that does not accept a completion status to ensure the default is set correctly
    @Test
    public void constructor_todoNotDone() {
        assertEquals(TODO_NOTDONE_DESCRIPTION, todoNotDone.getDescription());
        assertFalse(todoNotDone.getIsDone());
    }

    // Checks the string representation of the Todo object to ensure it formats correctly, including the task type and completion status
    @Test
    public void toString_correctFormat() {
        String expectedString = "[T][X] " + TODO_DONE_DESCRIPTION; // Assuming X represents done
        assertEquals(expectedString, todoDone.toString());
        
        expectedString = "[T][ ] " + TODO_NOTDONE_DESCRIPTION; // Assuming space represents not done
        assertEquals(expectedString, todoNotDone.toString());
    }

    // Tests the encoding of the Todo object to ensure it returns the expected string format
    @Test
    public void encodeTask_correctFormat() {
        String SEPARATOR = Constants.ENCODE_TASK_SEPARATOR;
        String expectedEncoded = TaskType.TODO + SEPARATOR + "1" + SEPARATOR + TODO_DONE_DESCRIPTION; // Assuming |1| indicates done
        assertEquals(expectedEncoded, todoDone.encodeTask());
        
        expectedEncoded = TaskType.TODO + SEPARATOR + "0" + SEPARATOR + TODO_NOTDONE_DESCRIPTION; // Assuming |0| indicates not done
        assertEquals(expectedEncoded, todoNotDone.encodeTask());
    }

    // Verifies that the isOnDate method always returns false for Todo tasks, as they do not have a specific date
    @Test
    public void isOnDate_returnsFalse() {
        LocalDate date = LocalDate.of(2023, 10, 31);
        assertFalse(todoDone.isOnDate(date)); // Should always return false
        assertFalse(todoNotDone.isOnDate(date)); // Should always return false
    }
}
