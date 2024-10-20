package tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {
    @Test
    void testConstructor() {
        String description = "Submit assignment";
        LocalDateTime deadlineTime = LocalDateTime.of(2023, 10, 15, 23, 59);

        Deadline deadline = new Deadline(description, deadlineTime);

        assertEquals(description, deadline.getDescription());
        assertEquals(deadlineTime, deadline.getBy());
    }

    @Test
    void testToString() {
        String description = "Submit assignment";
        LocalDateTime deadlineTime = LocalDateTime.of(2023, 10, 15, 23, 59);
        Deadline deadline = new Deadline(description, deadlineTime);

        String expectedString = "[D][ ] Submit assignment (by: 15 Oct 2023, 11:59 PM)";
        assertEquals(expectedString, deadline.toString());
    }


    @Test
    void testToFileFormat() {
        String description = "Submit assignment";
        LocalDateTime deadlineTime = LocalDateTime.of(2023, 10, 15, 23, 59);
        Deadline deadline = new Deadline(description, deadlineTime);

        String expectedFileFormat = "D|0|Submit assignment|15/10/2023 2359";
        assertEquals(expectedFileFormat, deadline.toFileFormat());
    }
}