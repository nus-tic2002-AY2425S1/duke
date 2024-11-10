package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import common.Messages;
import exception.CommandException;

public class DateTimeParserTest {
    
    @Test
    public void parseOutputDateTime_validInput_returnsLocalDateTime() throws CommandException {
        LocalDateTime expected = LocalDateTime.of(2023, 10, 1, 18, 0);
        LocalDateTime actual = DateTimeParser.parseOutputDateTime("Oct 01 2023 1800");
        assertEquals(expected, actual);
    }

    @Test
    public void parseOutputDateTime_invalidFormat_throwsCommandException() {
        CommandException commandException = assertThrows(CommandException.class, () -> {
            DateTimeParser.parseOutputDateTime("01-10-2023 1800");
        });
        assertEquals(Messages.ERROR_INVALID_DATETIME_FORMAT, commandException.getMessage());
    }

    @Test
    public void parseInputDeadlineDateTime_validInput_returnsLocalDateTime() throws CommandException {
        LocalDateTime expected = LocalDateTime.of(2023, 10, 1, 0, 0); // Default time is midnight
        LocalDateTime actual = DateTimeParser.parseInputDeadlineDateTime("2023-10-01");
        assertEquals(expected, actual);
    }

    @Test
    public void parseInputDeadlineDateTime_validInputWithTime_returnsLocalDateTime() throws CommandException {
        LocalDateTime expected = LocalDateTime.of(2023, 10, 1, 18, 0);
        LocalDateTime actual = DateTimeParser.parseInputDeadlineDateTime("2023-10-01 1800");
        assertEquals(expected, actual);
    }

    @Test
    public void parseInputDeadlineDateTime_invalidFormat_throwsCommandException() {
        CommandException commandException = assertThrows(CommandException.class, () -> {
            DateTimeParser.parseInputDeadlineDateTime("2023/10/01");
        });
        assertEquals(Messages.ERROR_INVALID_DATETIME_FORMAT, commandException.getMessage());
    }

    @Test
    public void parseInputEventDateTime_validInput_returnsLocalDateTime() throws CommandException {
        LocalDateTime expected = LocalDateTime.of(2023, 10, 1, 18, 0);
        LocalDateTime actual = DateTimeParser.parseInputEventDateTime("2023-10-01 1800");
        assertEquals(expected, actual);
    }

    @Test
    public void parseInputEventDateTime_invalidFormat_throwsCommandException() {
        CommandException commandException = assertThrows(CommandException.class, () -> {
            DateTimeParser.parseInputEventDateTime("01-10-2023 1800");
        });
        assertEquals(Messages.ERROR_INVALID_DATETIME_FORMAT, commandException.getMessage());
    }

    @Test
    public void parseInputShowDate_validInput_returnsLocalDate() throws CommandException {
        LocalDate expected = LocalDate.of(2023, 10, 1);
        LocalDate actual = DateTimeParser.parseInputShowDate("2023-10-01");
        assertEquals(expected, actual);
    }

    @Test
    public void parseInputShowDate_invalidFormat_throwsCommandException() {
        CommandException commandException = assertThrows(CommandException.class, () -> {
            DateTimeParser.parseInputShowDate("01-10-2023");
        });
        assertEquals(Messages.ERROR_INVALID_DATETIME_FORMAT, commandException.getMessage());
    }

    @Test
    public void formatDateTime_validInput_returnsFormattedString() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 1, 18, 0);
        String expected = "Oct 01 2023 1800";
        String actual = DateTimeParser.formatDateTime(dateTime);
        assertEquals(expected, actual);
    }

}
