package commands.add;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import parser.DateTimeParser;
import task.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

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
        assertEquals(TEST_DUE, deadlineCommand.getDueDate());
        assertNotNull(deadlineCommand);
    }

    // Tests that the createTask() method correctly creates a Deadline task with the given description and due date
    @Test
    public void createTask_validInput_createDeadlineTask() {
        Deadline deadline = new Deadline(TEST_DESCRIPTION, TEST_DUE);
        assertNotNull(deadline);
        assertInstanceOf(Deadline.class, deadline);
        assertEquals(TEST_DESCRIPTION, deadline.getDescription());
        assertEquals(TEST_DUE, deadline.getDue());
        assertEquals(DateTimeParser.formatDateTime(TEST_DUE), deadline.getFormattedDue());
    }

}
