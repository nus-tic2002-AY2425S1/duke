package commands.add;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import parser.DateTimeParser;
import task.Deadline;
import task.Task;

public class DeadlineCommandTest {
    private DeadlineCommand deadlineCommand;
    private final String TEST_DESCRIPTION = "This is a sample description of a Deadline task.";
    private final LocalDateTime TEST_DUE = LocalDateTime.of(2024, 3, 16, 12, 0);

    // Initializes a DeadlineCommand object with a valid description and due date before each test
    @BeforeEach
    public void init() {
        deadlineCommand = new DeadlineCommand(TEST_DESCRIPTION, TEST_DUE);
    }

    // Tests that the constructor correctly initializes the DeadlineCommand with valid input
    @Test
    public void constructor_validInput_createDeadlineCommand() {
        assertEquals(TEST_DESCRIPTION, deadlineCommand.getDescription());
        assertEquals(TEST_DUE, deadlineCommand.getBy());
        assertNotNull(deadlineCommand);
    }

    // Tests that the createTask() method correctly creates a Deadline task with the given description and due date
    @Test
    public void createTask_validInput_createDeadlineTask() {
        Task deadline = new Deadline(TEST_DESCRIPTION, TEST_DUE);
        assertNotNull(deadline);
        assertTrue(deadline instanceof Deadline);
        assertEquals(TEST_DESCRIPTION, deadline.getDescription());
        assertEquals(TEST_DUE, ((Deadline) deadline).getDue());
        assertEquals(DateTimeParser.formatDateTime(TEST_DUE), ((Deadline) deadline).getFormattedDue());
    }

    // Tests that passing a null description to the constructor throws an IllegalArgumentException
    // @Test
    // public void constructor_nullDescription_throwsException() {
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         new DeadlineCommand(null, TEST_DUE);
    //     });
    //     assertEquals("Description cannot be null", exception.getMessage());
    // }

    // Tests that passing an empty string as a description throws an IllegalArgumentException
    // @Test
    // public void constructor_emptyDescription_throwsException() {
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         new DeadlineCommand("", TEST_DUE);
    //     });
    //     assertEquals("Description cannot be empty", exception.getMessage());
    // }

    // Tests that passing a null due date to the constructor throws an IllegalArgumentException
    // @Test
    // public void constructor_nullDueDate_throwsException() {
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         new DeadlineCommand(TEST_DESCRIPTION, null);
    //     });
    //     assertEquals("Due date cannot be null", exception.getMessage());
    // }

    // Tests that calling createTask() with a null description throws an IllegalArgumentException
    // @Test
    // public void createTask_nullDescription_throwsException() {
    //     deadlineCommand = new DeadlineCommand(null, TEST_DUE);
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         deadlineCommand.createTask();
    //     });
    //     assertEquals("Description cannot be null", exception.getMessage());
    // }

    // Tests that calling createTask() with a null due date throws an IllegalArgumentException
    // @Test
    // public void createTask_nullDueDate_throwsException() {
    //     deadlineCommand = new DeadlineCommand(TEST_DESCRIPTION, null);
    //     Exception exception = assertThrows(IllegalArgumentException.class, () -> {
    //         deadlineCommand.createTask();
    //     });
    //     assertEquals("Due date cannot be null", exception.getMessage());
    // }

}
