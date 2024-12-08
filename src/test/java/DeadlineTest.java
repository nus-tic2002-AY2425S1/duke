import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void getTaskType() {
        LocalDateTime by = LocalDateTime.of(2024, 10, 20, 12, 10);
        Deadline deadline = new Deadline("Read a book", by);

        assertEquals("D", deadline.getTaskType(), "Task type for Deadline should be 'D'");
    }

    @Test
    void testToString() {
        LocalDateTime by = LocalDateTime.of(2024, 10, 20, 12, 10);
        Deadline deadline = new Deadline("Read a book", by);

        // Expected output format for a deadline
        String expected = "[D][ ] Read a book (by: Oct 20 2024 12:10)";
        assertEquals(expected, deadline.toString(), "toString should return formatted deadline string");

        // After marking as done
        deadline.markAsDone();
        expected = "[D][✔] Read a book (by: Oct 20 2024 12:10)";
        assertEquals(expected, deadline.toString(), "toString should show deadline as done after marking");
    }

    @Test
    void toFileString() {
        LocalDateTime by = LocalDateTime.of(2024, 10, 20, 12, 10);
        Deadline deadline = new Deadline("Read a book", by);

        // Expected file storage format for a deadline
        String expected = "D | 0 | Read a book | 20/10/2024 1210";
        assertEquals(expected, deadline.toFileString(), "toFileString should return formatted string for file storage");

        // After marking as done
        deadline.markAsDone();
        expected = "D | 1 | Read a book | 20/10/2024 1210";
        assertEquals(expected, deadline.toFileString(), "toFileString should indicate deadline completion after marking as done");
    }
}