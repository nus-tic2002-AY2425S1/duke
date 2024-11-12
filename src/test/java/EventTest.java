import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;


class EventTest {

    @Test
    void getTaskType() {
        LocalDateTime from = LocalDateTime.of(2024,10,20,12,10);
        LocalDateTime to = LocalDateTime.of(2024,10,20,13,10);
        Event event = new Event("Read a book", from, to);

        assertEquals("E", event.getTaskType(), " Task type for Event should be 'E'");
    }

    @Test
    void testToString() {
        LocalDateTime from = LocalDateTime.of(2024,10,20,12,10);
        LocalDateTime to = LocalDateTime.of(2024,10,20,13,10);
        Event event = new Event("Read a book", from, to);

        // Expected output format for an event
        String expected = "[E][ ] Read a book (from: Oct 20 2024 12:10 to: Oct 20 2024 13:10)";
        assertEquals(expected, event.toString(), "toString should return formatted event string");

        // After marking as done
        event.markAsDone();
        expected = "[E][✔] Read a book (from: Oct 20 2024 12:10 to: Oct 20 2024 13:10)";
        assertEquals(expected, event.toString(), "toString should show event as done after marking");
    }

    @Test
    void toFileString() {
        LocalDateTime from = LocalDateTime.of(2024, 10, 20, 12, 10);
        LocalDateTime to = LocalDateTime.of(2024, 10, 20, 13, 10);
        Event event = new Event("Read a book", from, to);

        // Expected file storage format for an event
        String expected = "E | 0 | Read a book | 20/10/2024 1210 | 20/10/2024 1310";
        assertEquals(expected, event.toFileString(), "toFileString should return formatted string for file storage");

        // After marking as done
        event.markAsDone();
        expected = "E | 1 | Read a book | 20/10/2024 1210 | 20/10/2024 1310";
        assertEquals(expected, event.toFileString(), "toFileString should indicate event completion after marking as done");
    }
}