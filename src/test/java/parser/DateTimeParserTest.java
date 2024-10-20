package parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import exception.DukeException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
public class DateTimeParserTest {

    @Test
    void successDateTimeParsing() throws DukeException{
        String validDateTimeString = "10/10/2024 1800";
        LocalDateTime expected = LocalDateTime.of(2024,10,10,18,0);
        LocalDateTime actual = DateTimeParser.parseDateTime(validDateTimeString);
        assertEquals(expected,actual);
    }

    @Test
    void failDateTimeParsing() throws DukeException{
        String invalidDateTimeString = "invalid string";
        assertThrows(DukeException.class, () -> {
            DateTimeParser.parseDateTime(invalidDateTimeString);
        });
    }

    @Test
    void successFormatDateTime(){
        LocalDateTime validDateTime = LocalDateTime.of(2024,10,10,18,0);
        String expected = "10 Oct 2024, 6:00 PM";
        String actual = DateTimeParser.formatDateTime(validDateTime);
        assertEquals(expected,actual);
    }

    @Test
    void successFormatFile(){
        LocalDateTime validDateTime = LocalDateTime.of(2024,10,10,18,0);
        String expected = "10/10/2024 1800";
        String actual = DateTimeParser.formatToFile(validDateTime);
        assertEquals(expected,actual);
    }
}