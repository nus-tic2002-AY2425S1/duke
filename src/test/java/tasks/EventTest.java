package tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testConstructor() {
        String description = "Submit assignment";
        LocalDateTime fromTime = LocalDateTime.of(2023, 10, 15, 23, 59);
        LocalDateTime toTime = LocalDateTime.of(2023, 10, 16, 23, 59);

        Event event = new Event(description, fromTime, toTime);

        assertEquals(description, event.getDescription());
        assertEquals(fromTime, event.getFrom());
        assertEquals(toTime, event.getTo());
    }

    @Test
    void testToString() {
        String description = "Submit assignment";
        LocalDateTime fromTime = LocalDateTime.of(2023, 10, 15, 23, 59);
        LocalDateTime toTime = LocalDateTime.of(2023, 10, 16, 23, 59);

        Event event = new Event(description, fromTime, toTime);

        String expectedString = "[E][ ] Submit assignment (from: 15 Oct 2023, 11:59 PM to: 16 Oct 2023, 11:59 PM)";
        assertEquals(expectedString, event.toString());
    }


    @Test
    void testToFileFormat() {
        String description = "Submit assignment";
        LocalDateTime fromTime = LocalDateTime.of(2023, 10, 15, 23, 59);
        LocalDateTime toTime = LocalDateTime.of(2023, 10, 16, 23, 59);

        Event event = new Event(description, fromTime, toTime);

        String expectedFileFormat = "E|0|Submit assignment|15/10/2023 2359|16/10/2023 2359";
        assertEquals(expectedFileFormat, event.toFileFormat());
    }

}