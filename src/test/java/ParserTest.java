import jibberJabber.commands.Parser;
import jibberJabber.tasks.TaskFiles;
import jibberJabber.tasks.TaskList;
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
        boolean result = parser.processCommand(todoCommand);
        assertTrue(result, "The return value should return true to continue processing.");
        assertEquals(1, taskList.getTotalTaskCount(), "Number of Task should increase after adding a task.");
        assertEquals("[T][ ] Read a book", taskList.getLastTask().printAddedTask().trim(), "Task name should match the input.");
        String filePath = System.getProperty("user.dir") + File.separator + "data/tasks.txt";
        File taskFile = new File(filePath);
        assertTrue(taskFile.exists(), "Task file should exist.");
        assertEquals(1, taskList.getTotalTaskCount(), "Task file should reflect an empty task list after deletion.");
    }
    // Test: Add event task
    @Test
    void testProcessCommand_addEvent() {
        String eventCommand = "event Read a book /from 5/2/2013 1400 /to 5/2/2013 1430";
        boolean result = parser.processCommand(eventCommand);
        assertTrue(result, "The return value should return true to continue processing.");
        assertEquals(1, taskList.getTotalTaskCount(), "Number of Task should increase after adding a task.");
        assertEquals("[E][ ] Read a book (from: Feb 05 2013, 2:00pm to: Feb 05 2013, 2:30pm)", taskList.getLastTask().printAddedTask().trim(), "Event Task name and description should match the input.");
        String filePath = System.getProperty("user.dir") + File.separator + "data/tasks.txt";
        File taskFile = new File(filePath);
        assertTrue(taskFile.exists(), "Task file should exist.");
        assertEquals(1, taskList.getTotalTaskCount(), "Task file should reflect an empty task list after deletion.");
    }
    // Test: Add deadline task
    @Test
    void testProcessCommand_addDeadline() {
        String deadlineCommand = "deadline Read a book /by 5/2/2013 1430";
        boolean result = parser.processCommand(deadlineCommand);
        assertTrue(result, "The return value should return true to continue processing.");
        assertEquals(1, taskList.getTotalTaskCount(), "Number of Task should increase after adding a task.");
        assertEquals("[D][ ] Read a book (by: Feb 05 2013, 2:30pm)", taskList.getLastTask().printAddedTask().trim(), "Deadline Task name and description should match the input.");
        String filePath = System.getProperty("user.dir") + File.separator + "data/tasks.txt";
        File taskFile = new File(filePath);
        assertTrue(taskFile.exists(), "Task file should exist.");
        assertEquals(1, taskList.getTotalTaskCount(), "Task file should reflect an empty task list after deletion.");
    }
    // Test: Invalid keyword command
    @Test
    void testProcessCommand_invalidCommand() {
        String invalidCommand = "t do";
        boolean result = parser.processCommand(invalidCommand);
        assertTrue(result, "The parser should return true for invalid commands to keep running.");
        assertEquals(0, taskList.getTotalTaskCount(), "Task list should remain empty for invalid commands.");
    }
    // Test: mark task status as done
    @Test
    void testProcessCommand_markTask() {
        parser.processCommand("todo Write tests");
        String markCommand = "mark 1";
        boolean result = parser.processCommand(markCommand);
        assertTrue(result, "The command should return true to continue processing.");
        assertTrue(taskList.getTaskById(0).isDone, "The first task should be marked as done.");
    }
    // Test: Bye command
    @Test
    void testByeCommand() {
        boolean result = parser.processCommand("bye");
        assertFalse(result, "The bye command should return false to indicate program exit.");
    }
    // Test: Delete todos task
    @Test
    void testDeleteCommand() {
        parser.processCommand("todo Read a book");
        assertEquals(1, taskList.getTotalTaskCount(), "Task list should contain one task before deletion.");
        parser.processCommand("delete 1");
        assertEquals(0, taskList.getTotalTaskCount(), "Task list should be empty after deletion.");
        String filePath = System.getProperty("user.dir") + File.separator + "data/tasks.txt";
        File taskFile = new File(filePath);
        assertTrue(taskFile.exists(), "Task file should exist.");
        assertEquals(0, taskList.getTotalTaskCount(), "Task file should reflect an empty task list after deletion.");
    }
}
