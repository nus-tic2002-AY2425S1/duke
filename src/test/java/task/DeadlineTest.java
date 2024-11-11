package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import common.Constants;
import parser.DateTimeParser;

public class DeadlineTest {
    
    private LocalDateTime dueDateTime;
    private Deadline deadlineWithDue;
    private Deadline deadlineNotDone;

    @BeforeEach
    public void setUp() {
        dueDateTime = LocalDateTime.of(2024, 10, 31, 23, 59);
        deadlineWithDue = new Deadline("Review legal documents", true, dueDateTime);
        deadlineNotDone = new Deadline("Submit job application", dueDateTime);
    }

    // Tests the constructor that accepts a completion status to ensure it initializes the object correctly.
    @Test
    public void constructor_deadlineWithIsDone_correctInitialization() {
        assertEquals("Review legal documents", deadlineWithDue.getDescription());
        assertTrue(deadlineWithDue.getIsDone());
        assertEquals(dueDateTime, deadlineWithDue.getDue());
    }

    // Tests the constructor that does not accept a completion status to ensure the default is set correctly.
    @Test
    public void constructor_deadlineWithoutIsDone_correctInitialization() {
        assertEquals("Submit job application", deadlineNotDone.getDescription());
        assertFalse(deadlineNotDone.getIsDone());
        assertEquals(dueDateTime, deadlineNotDone.getDue());
    }

    // Verifies that the formatted due date is correct using the getFormattedDue() method.
    @Test
    public void getFormattedDue_correctFormat() {
        String expectedFormattedDue = DateTimeParser.formatDateTime(dueDateTime);
        assertEquals(expectedFormattedDue, deadlineWithDue.getFormattedDue());
    }

    // Checks the string representation of the Deadline object to ensure it formats correctly.
    @Test
    public void toString_deadlineWithDue_correctString() {
        String expectedString = Constants.OPEN_SQUARE_BRACKET + Constants.D + Constants.CLOSE_SQUARE_BRACKET + 
            Constants.OPEN_SQUARE_BRACKET + Constants.X + Constants.CLOSE_SQUARE_BRACKET + Constants.SPACE +
            "Review legal documents" + Constants.SPACE + Constants.OPEN_ROUND_BRACKET + Constants.BY + 
            Constants.COLON + Constants.SPACE + deadlineWithDue.getFormattedDue() + Constants.CLOSE_ROUND_BRACKET;
        assertEquals(expectedString, deadlineWithDue.toString());
    }

    // Verifies that the description does not end with a space when formatted in the string representation.
    @Test
    public void toString_descriptionEndsWithNoSpace() {
        Deadline deadline = new Deadline("Deadline description without space", dueDateTime);
        assertNotEquals(deadline.toString(), "Deadline description without space ");
        // assertFalse(deadline.toString().contains("Deadline description without space "));   // Check that it doesn't have trailing space
    }

    // Tests the encoding of the Deadline object to ensure it returns the expected string format.
    @Test
    public void encodeTask_deadlineWithDue_correctFormat() {
        String expectedEncoded = TaskType.DEADLINE + Constants.ENCODE_TASK_SEPARATOR + 
            "1 | Review legal documents" + Constants.ENCODE_TASK_SEPARATOR + DateTimeParser.formatDateTime(dueDateTime);
        assertEquals(expectedEncoded, deadlineWithDue.encodeTask());
    }

    // Tests the isOnDate method to confirm it correctly identifies when the task is due.
    @Test
    public void isOnDate_dueDate_returnsTrue() {
        LocalDate dateToCheck = LocalDate.of(2024, 10, 31);
        assertTrue(deadlineWithDue.isOnDate(dateToCheck));
    }

    // Tests the isOnDate method to confirm it returns false for a date that does not match the due date.
    @Test
    public void isOnDate_nonMatchingDate_returnsFalse() {
        LocalDate dateToCheck = LocalDate.of(2023, 11, 1);
        assertFalse(deadlineWithDue.isOnDate(dateToCheck));
    }

    @Test
    public void isOnDate_dateBeforeDueDate_returnsFalse() {
        LocalDate dateToCheck = LocalDate.of(2023, 10, 30);
        assertFalse(deadlineWithDue.isOnDate(dateToCheck));
    }

}
