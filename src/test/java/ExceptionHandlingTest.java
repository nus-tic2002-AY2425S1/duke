import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import jibberJabber.tasks.TaskList;
import jibberJabber.tasks.taskType.ToDo;
import jibberJabber.commands.ExceptionHandling;
import jibberJabber.tasks.Task;
import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionHandlingTest {
    private ArrayList<Task> todoTaskList;
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        todoTaskList = new ArrayList<>();
    }
    // Test: Validate integer for array indices
    @Test
    void testIsIntegerValid() {
        assertTrue(ExceptionHandling.isInteger("123"), "Valid integer string should return true.");
        assertFalse(ExceptionHandling.isInteger("abc"), "Non-integer string should return false.");
        assertFalse(ExceptionHandling.isInteger("123abc"), "Mixed string should return false.");
    }
    // Test: Remove white spaces that disrupt the format of the task
    @Test
    void testRemoveSpaces() {
        assertEquals("Test Test", ExceptionHandling.removeSpaces("  Test   Test  "), "Extra spaces should be removed.");
        assertEquals("Test", ExceptionHandling.removeSpaces("Test"), "String with no spaces should remain unchanged.");
    }
    // Test: Validate if a given command contains task description after keyword command
    @Test
    void testIsEmptyTaskDescription() {
        assertTrue(ExceptionHandling.isEmptyInput("todo"), "Input with only 'todo' should be empty.");
        assertFalse(ExceptionHandling.isEmptyInput("todo read a book"), "Valid 'todo' keyword command should have valid task description.");
        assertTrue(ExceptionHandling.isEmptyInput("event /from /to"), "Input with only 'event' should be empty.");
        assertFalse(ExceptionHandling.isEmptyInput("event Celebrate Birthday /from 1/1/2023 /to 2/1/2023"), "Valid 'event' keyword command should have valid task description.");
        assertTrue(ExceptionHandling.isEmptyInput("deadline /by"), "Input with only 'deadline' should be empty.");
        assertFalse(ExceptionHandling.isEmptyInput("deadline return book /by 1/1/2023"), "Valid 'deadline' keyword command should have valid task description.");
    }
    // Test: Validate if a new task is present in existing list
    @Test
    void testIsTaskDuplicated() {
        todoTaskList.add(new ToDo("Read a book"));
        String duplicateTodo = "todo Read a book";
        assertTrue(ExceptionHandling.isTaskDuplicated(todoTaskList, duplicateTodo), "Duplicate 'todo' task should return true.");
        String nonDuplicateTodo = "todo Buy Grocery";
        assertFalse(ExceptionHandling.isTaskDuplicated(todoTaskList, nonDuplicateTodo), "Non-duplicate 'todo' task should return false.");
        String duplicateDeadline = "deadline Buy Grocery /by 1/12/2023 2359";
        assertFalse(ExceptionHandling.isTaskDuplicated(todoTaskList, duplicateDeadline), "Duplication does not consider task type. Non-duplicate 'todo' task should return false.");
    }
    // Test: Validates if there is deadline (date time) provided
    @Test
    void testIsValidDeadlineInput() {
        assertTrue(ExceptionHandling.isValidDeadlineInput(new String[]{"Submit assignment", "1/12/2023 2359"}), "Valid deadline input should return true.");
        assertFalse(ExceptionHandling.isValidDeadlineInput(new String[]{"Submit assignment"}), "Missing deadline (date time) should return false.");
    }
    // Test: Validates if there is event period (date time) provided
    @Test
    void testIsValidEventInput() {
        assertTrue(ExceptionHandling.isValidEventInput(new String[]{"Celebrate Birthday", "1/1/2023 2359 /to 2/1/2023 2359"}), "Valid event input should return true.");
        assertFalse(ExceptionHandling.isValidEventInput(new String[]{"Celebrate Birthday", "1/1/2023 2359"}), "Missing event details should return false.");
        assertFalse(ExceptionHandling.isValidEventInput(new String[]{"Celebrate Birthday", "1/1/2023 2359 /to "}), "Missing event details should return false.");
    }
    // Test: Validate if the date is formatted
    @Test
    void testIsInvalidDate() {
        assertFalse(ExceptionHandling.isInvalidDate("1/12/2023"), "Valid date should return false.");
        assertTrue(ExceptionHandling.isInvalidDate("1-12-2023 2359"), "Invalid date format should return true.");
        assertTrue(ExceptionHandling.isInvalidDate(" "), "Invalid date format should return true.");
        assertTrue(ExceptionHandling.isInvalidDate("2023-12-01  "), "Invalid date format should return true.");
        assertTrue(ExceptionHandling.isInvalidDate("6th June "), "Invalid date format should return true.");
    }
    // Test: Validate if the keyword is present under keyword enum
    @Test
    void testIsInvalidKeywordCommand() {
        assertFalse(ExceptionHandling.isInvalidKeywordCommand("todo"), "Valid keyword should return false.");
        assertFalse(ExceptionHandling.isInvalidKeywordCommand("EvEnt"), "Valid keyword should return false.");
        assertFalse(ExceptionHandling.isInvalidKeywordCommand("DEADLINE"), "Valid keyword should return false.");
        assertTrue(ExceptionHandling.isInvalidKeywordCommand("ToDos"), "Invalid keyword should return true.");
        assertTrue(ExceptionHandling.isInvalidKeywordCommand("dead line"), "Invalid keyword should return true.");
    }
}
