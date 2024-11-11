package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.Constants;
import parser.DateTimeParser;

public class EventTest {
    
    private Event eventWithDone;
    private Event eventNotDone;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private final String AGM = "Annual General Meeting";
    private final String TL = "Team Lunch";
    private final String SPACE = Constants.SPACE;
    private final String SEPARATOR = Constants.ENCODE_TASK_SEPARATOR;

    @BeforeEach
    public void setUp() {
        startDateTime = LocalDateTime.of(2024, 12, 31, 14, 0);
        endDateTime = LocalDateTime.of(2024, 12, 31, 16, 0);
        eventWithDone = new Event(AGM, true, startDateTime, endDateTime);
        eventNotDone = new Event(TL, startDateTime, endDateTime);
    }

    // Ensures that the constructor initializes the Event object with a completion status correctly.
    @Test
    public void constructor_eventWithDone_correctInitialization() {
        assertEquals(AGM, eventWithDone.getDescription());
        assertTrue(eventWithDone.getIsDone());
        assertEquals(startDateTime, eventWithDone.getStartDateTime());
        assertEquals(endDateTime, eventWithDone.getEndDateTime());
    }

    // Ensures that the default constructor (without setting completion status) is initialized correctly
    @Test
    public void constructor_eventNotDone_correctInitialization() {
        assertEquals(TL, eventNotDone.getDescription());
        assertFalse(eventNotDone.getIsDone()); // Default isDone should be false
        assertEquals(startDateTime, eventNotDone.getStartDateTime());
        assertEquals(endDateTime, eventNotDone.getEndDateTime());
    }

    // Verifies that the getFormattedStartDateTime() method returns the correct formatted start date and time
    @Test
    public void getFormattedStartDateTime_correctFormat() {
        String expectedFormattedStart = DateTimeParser.formatDateTime(startDateTime);
        assertEquals(expectedFormattedStart, eventWithDone.getFormattedStartDateTime());
    }

    // Verifies that the getFormattedEndDateTime() method returns the correct formatted end date and time
    @Test
    public void getFormattedEndDateTime_correctFormat() {
        String expectedFormattedEnd = DateTimeParser.formatDateTime(endDateTime);
        assertEquals(expectedFormattedEnd, eventWithDone.getFormattedEndDateTime());
    }

    // Checks the string representation of the Event object to ensure it formats correctly.
    @Test
    public void toString_eventWithDone_correctFormat() {
        final String OPEN_ROUND_BRACKET = Constants.OPEN_ROUND_BRACKET;
        final String OPEN_SQUARE_BRACKET = Constants.OPEN_SQUARE_BRACKET;
        final String CLOSE_SQUARE_BRACKET = Constants.CLOSE_SQUARE_BRACKET;

        String expectedString = OPEN_SQUARE_BRACKET + Constants.E + CLOSE_SQUARE_BRACKET + 
            OPEN_SQUARE_BRACKET + Constants.X + CLOSE_SQUARE_BRACKET + SPACE + 
            AGM + SPACE + OPEN_ROUND_BRACKET + Constants.FROM + Constants.COLON + SPACE + 
            eventWithDone.getFormattedStartDateTime() + SPACE + Constants.TO + Constants.COLON + SPACE +
            eventWithDone.getFormattedEndDateTime() + Constants.CLOSE_ROUND_BRACKET;
        assertEquals(expectedString, eventWithDone.toString());
    }

    // Tests the encoding of the Event object to ensure it returns the expected string format.
    @Test
    public void encodeTask_eventWithDone_correctFormat() {
        String expectedEncoded = TaskType.EVENT + SEPARATOR + "1" + SEPARATOR + AGM + SEPARATOR + 
            eventWithDone.getFormattedStartDateTime() + SEPARATOR + eventWithDone.getFormattedEndDateTime();
        assertEquals(expectedEncoded, eventWithDone.encodeTask());
    }

    // Tests the isOnDate method to confirm that it correctly identifies when the event starts on a specific date
    @Test
    public void isOnDate_eventStartsOnDate_returnsTrue() {
        LocalDate dateToCheck = LocalDate.of(2024, 12, 31);
        assertTrue(eventWithDone.isOnDate(dateToCheck));
    }

    // Tests the isOnDate method to confirm that it correctly identifies when the event ends on a specific date
    @Test
    public void isOnDate_eventEndsOnDate_returnsTrue() {
        LocalDate dateToCheck = LocalDate.of(2024, 12, 31);
        assertTrue(eventWithDone.isOnDate(dateToCheck));
    }

    // Tests the isOnDate method to confirm that it returns false for a date that does not match either the start or end date
    @Test
    public void isOnDate_dateNotMatching_returnsFalse() {
        LocalDate dateToCheck = LocalDate.of(2024, 11, 1);
        assertFalse(eventWithDone.isOnDate(dateToCheck));
    }

    // Verifies that the description does not end with a space when formatted in the string representation
    @Test
    public void toString_descriptionEndsWithNoSpace() {
        Event event = new Event("Event without space", startDateTime, endDateTime);
        assertNotEquals(event.toString(), "Event without space ");
    }

}
