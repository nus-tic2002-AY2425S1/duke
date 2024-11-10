package snitch.task;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class DeadlineTest {

    @Test
    public void constructor_validDate_success() {
        Deadline deadline = new Deadline("Submit Assignment", "2/12/2023 1800");
        assertEquals("Submit Assignment", deadline.getDescription());
        assertEquals("2/12/2023 1800", deadline.getBy()); // Ensure the format here matches getBy()
    }

    @Test
    public void constructor_invalidDateFormat_throwsException() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("Submit Assignment", "invalid-date");
        });
    }

    @Test
    public void toString_correctFormat() {
        Deadline deadline = new Deadline("Submit Assignment", "2/12/2023 1800");
        String expectedOutput = "[D][ ] Submit Assignment (by: Dec 02 2023, 6:00 pm)";
        assertEquals(expectedOutput, deadline.toString());
    }
}