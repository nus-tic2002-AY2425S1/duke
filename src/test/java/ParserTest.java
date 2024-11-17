import jibberJabber.commands.KeywordHandling;
import jibberJabber.commands.Parser;
import jibberJabber.tasks.TaskFiles;
import jibberJabber.tasks.TaskList;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private Parser parser;
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
        String relativePath = System.getProperty("user.dir") + File.separator + "data/tasks.txt";
        TaskFiles taskFiles = new TaskFiles(relativePath);
        parser = new Parser(taskList, taskFiles);
    }

    // Test: Add todos task
    @Test
    void testProcessCommand_addTodo() {
        String todoCommand = "todo Read a book";
        boolean result = parser.hasProcessCommandSucceed(todoCommand);
        assertTrue(result, "The return value should return true to continue processing.");
        assertEquals(1, taskList.getTotalTaskCount(), "Number of Task should increase after adding a task.");
        assertEquals("[T][ ] Read a book", taskList.getLastTask().printAddedTask().trim(), "Task name should match the input.");
    }
    // Test: Add event task
    @Test
    void testProcessCommand_addEvent() {
        String eventCommand = "event Read a book /from 5/2/2013 /to 5/2/2013";
        boolean result = parser.hasProcessCommandSucceed(eventCommand);
        assertTrue(result, "The return value should return true to continue processing.");
        assertEquals(1, taskList.getTotalTaskCount(), "Number of Task should increase after adding a task.");
        assertEquals("[E][ ] Read a book (from: Feb 05 2013 to: Feb 05 2013)", taskList.getLastTask().printAddedTask().trim(), "Event Task name and description should match the input.");
    }
    // Test: Add deadline task
    @Test
    void testProcessCommand_addDeadline() {
        String deadlineCommand = "deadline Read a book /by 5/2/2013";
        boolean result = parser.hasProcessCommandSucceed(deadlineCommand);
        assertTrue(result, "The return value should return true to continue processing.");
        assertEquals(1, taskList.getTotalTaskCount(), "Number of Task should increase after adding a task.");
        assertEquals("[D][ ] Read a book (by: Feb 05 2013)", taskList.getLastTask().printAddedTask().trim(), "Deadline Task name and description should match the input.");
    }
    // Test: Invalid keyword command
    @Test
    void testProcessCommand_invalidCommand() {
        String invalidCommand = "t do";
        boolean result = parser.hasProcessCommandSucceed(invalidCommand);
        assertTrue(result, "The parser should return true for invalid commands to keep running.");
        assertEquals(0, taskList.getTotalTaskCount(), "Task list should remain empty for invalid commands.");
    }
    // Test: mark task status as done
    @Test
    void testProcessCommand_markTask() {
        parser.hasProcessCommandSucceed("todo Write tests");
        String markCommand = "mark 1";
        boolean result = parser.hasProcessCommandSucceed(markCommand);
        assertTrue(result, "The command should return true to continue processing.");
        assertTrue(taskList.getTaskById(0).isDone, "The first task should be marked as done.");
    }
    // Test: Bye command
    @Test
    void testProcessCommand_bye() {
        boolean result = parser.hasProcessCommandSucceed("bye");
        assertFalse(result, "The bye command should return false to indicate program exit.");
    }
    // Test: Delete todos task
    @Test
    void testProcessCommand_deleteTask() {
        parser.hasProcessCommandSucceed("todo Read a book");
        assertEquals(1, taskList.getTotalTaskCount(), "Task list should contain one task before deletion.");
        parser.hasProcessCommandSucceed("delete 1");
        assertEquals(0, taskList.getTotalTaskCount(), "Task list should be empty after deletion.");
    }
    // Test: Complete task within request period
    @Test
    void testProcessCommand_findUndoneTaskWithinDateRange() {
        parser.hasProcessCommandSucceed("event Read a book /from 1/1/2013 /to 1/3/2013");
        parser.hasProcessCommandSucceed( "deadline Read a book /by 5/2/2013");
        parser.hasProcessCommandSucceed("complete tasks between 1/1/2013 /to 1/3/2013");
        LocalDate startDate = KeywordHandling.formatDateInputsAsLocalDate("1/1/2013", "d/M/yyyy");
        LocalDate endDate = KeywordHandling.formatDateInputsAsLocalDate("1/3/2013", "d/M/yyyy");
        TaskList tasksWithinPeriod = taskList.getTasksWithinPeriod(startDate, endDate);
        assertEquals(2, tasksWithinPeriod.getTotalTaskCount(), "Task list should contain two tasks within the period.");
        parser.hasProcessCommandSucceed("delete 1");
        parser.hasProcessCommandSucceed( "deadline Read a book /by 1/12/2013");
        parser.hasProcessCommandSucceed("complete tasks between 1/1/2013 /to 1/3/2013");
        tasksWithinPeriod = taskList.getTasksWithinPeriod(startDate, endDate);
        assertEquals(1, tasksWithinPeriod.getTotalTaskCount(), "Task list should contain one task within the period after deletion.");
    }
    @Test
    void testProcessCommand_findUndoneTaskWithinDateRange_nullReturn() {
        parser.hasProcessCommandSucceed("event Read a book /from 1/1/2013 /to 1/3/2013");
        parser.hasProcessCommandSucceed( "deadline Read a book /by 5/2/2013");
        parser.hasProcessCommandSucceed("complete tasks between 1/1/2014 /to 1/3/2014");
        LocalDate startDate = KeywordHandling.formatDateInputsAsLocalDate("1/1/2014", "d/M/yyyy");
        LocalDate endDate = KeywordHandling.formatDateInputsAsLocalDate("1/3/2014","d/M/yyyy" );
        TaskList tasksWithinPeriod = taskList.getTasksWithinPeriod(startDate, endDate);
        assertEquals(0, tasksWithinPeriod.getTotalTaskCount(), "Task list should be empty with no tasks found");
    }
    @Test
    void testProcessCommand_findUndoneTaskWithMidRangeDates() {
        parser.hasProcessCommandSucceed("event Read a book /from 1/1/2013 /to 1/3/2013");
        parser.hasProcessCommandSucceed( "deadline Read a book /by 5/2/2013");
        parser.hasProcessCommandSucceed("complete tasks between 1/1/2013 /to 6/2/2013");
        LocalDate startDate = KeywordHandling.formatDateInputsAsLocalDate("1/2/2013", "d/M/yyyy");
        LocalDate endDate = KeywordHandling.formatDateInputsAsLocalDate("1/3/2013", "d/M/yyyy");
        TaskList tasksWithinPeriod = taskList.getTasksWithinPeriod(startDate, endDate);
        assertEquals(1, tasksWithinPeriod.getTotalTaskCount(), "Task list should contain one tasks within the period.");
    }
    // Test: Find task
    @Test
    void testProcessCommand_findKeywordMatch() {
        parser.hasProcessCommandSucceed("todo Read a book");
        parser.hasProcessCommandSucceed("todo Return books");
        parser.hasProcessCommandSucceed("todo Lend fairytale BoOk");
        parser.hasProcessCommandSucceed("find book");
        assertEquals(3, taskList.getTotalTaskCount(), "Task list should contain three task.");
    }
    // Test: Find task with dates
    @Test
    void testProcessCommand_findKeywordMatchWithDates() {
        parser.hasProcessCommandSucceed("event Read a book /from 1/1/2013 /to 1/3/2013");
        parser.hasProcessCommandSucceed("deadline Read a book /by 1/1/2013");
        parser.hasProcessCommandSucceed("todo Lend fairytale BoOk");
        parser.hasProcessCommandSucceed("find Jan 01 2013");
        TaskList foundTasks = taskList.getTasksWithMatchingKeyword("Jan 01 2013", taskList);
        assertEquals(2, foundTasks.getTotalTaskCount(), "Task list should contain two task.");
    }
    @Test
    void testProcessCommand_findKeywordMatch_nullReturn() {
        parser.hasProcessCommandSucceed("todo Read a book");
        parser.hasProcessCommandSucceed("todo Return books");
        parser.hasProcessCommandSucceed("todo Lend fairytale BoOk");
        parser.hasProcessCommandSucceed("find ticket");
        TaskList foundTasks = taskList.getTasksWithMatchingKeyword("ticket", taskList);
        assertEquals(0, foundTasks.getTotalTaskCount(), "Task list should be empty after finding a task.");
    }
}
