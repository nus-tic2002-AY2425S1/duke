package commands.add;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import parser.DateTimeParser;
import task.Event;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class EventCommandTest {
    private EventCommand eventCommand;
    private final String TEST_DESCRIPTION = "This is a sample description of an Event task.";
    private final LocalDateTime TEST_STARTDATETIME =
        LocalDateTime.of(2024, 4, 16, 12, 0);
    private final LocalDateTime TEST_ENDDATETIME = LocalDateTime.of(2024, 5, 16, 12, 0);

    // Initializes an EventCommand with valid description, start date and time, and end date and time
    @BeforeEach
    public void init() {
        eventCommand = new EventCommand(TEST_DESCRIPTION, TEST_STARTDATETIME, TEST_ENDDATETIME);
    }

    // Tests that the constructor correctly initializes the EventCommand with valid parameters
    @Test
    public void constructor_validInput_createsEventCommand() {
        assertNotNull(eventCommand);
        assertEquals(TEST_DESCRIPTION, eventCommand.getDescription());
        assertEquals(TEST_STARTDATETIME, eventCommand.getStartDateTime());
        assertEquals(TEST_ENDDATETIME, eventCommand.getEndDateTime());
    }

    // Tests that the createTask() method correctly creates an Event task with the given description, start date and time, and end date and time
    @Test
    public void createTask_validInput_createsEventCommand() {
        Event event = new Event(TEST_DESCRIPTION, TEST_STARTDATETIME, TEST_ENDDATETIME);
        
        assertNotNull(event);
        assertInstanceOf(Event.class, event);
        
        assertEquals(TEST_DESCRIPTION, event.getDescription());
        assertEquals(TEST_STARTDATETIME, event.getStartDateTime());
        assertEquals(TEST_ENDDATETIME, event.getEndDateTime());
        
        assertEquals(DateTimeParser.formatDateTime(TEST_STARTDATETIME), event.getFormattedStartDateTime());
        assertEquals(DateTimeParser.formatDateTime(TEST_ENDDATETIME), event.getFormattedEndDateTime());
    }
}
