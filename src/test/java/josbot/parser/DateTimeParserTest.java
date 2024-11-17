package josbot.parser;

import josbot.JosBotException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeParserTest {

    static final DateTimeFormatter FORMATTER_DISPLAY_DATETIME = DateTimeFormatter.ofPattern("dd MMMM yyyy 'at' hh:mm a");

    @Test
    public void testDateTimeParserStringConversion() throws JosBotException {

        assertEquals(LocalDateTime.now().format(FORMATTER_DISPLAY_DATETIME), DateTimeParser.convertToString(LocalDateTime.now(),"view"));
    }

}
