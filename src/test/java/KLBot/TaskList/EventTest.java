package KLBot.TaskList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testGetDescription() {
        String description = "Project meeting";
        String start = "2023-10-15 10:00";
        String end = "2023-10-15 12:00";
        Event event = new Event(description, start, end);

        String expectedDescription = description + " (from: Oct 15 2023 10:00 to: Oct 15 2023 12:00)";

        assertEquals(expectedDescription, event.getDescription());
    }

    @Test
    public void testToString() {
        String description = "Project meeting";
        String start = "2023-10-15 10:00";
        String end = "2023-10-15 12:00";
        Event event = new Event(description, start, end);

        String expectedToString = "[E][ ] " + description + " (from: Oct 15 2023 10:00 to: Oct 15 2023 12:00)";

        assertEquals(expectedToString, event.toString());
    }
}
