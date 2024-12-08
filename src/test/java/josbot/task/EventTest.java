package josbot.task;

import josbot.JosBotException;
import josbot.parser.DateTimeParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testEventStringConversion() throws JosBotException {
        DateTimeParser dp = new DateTimeParser();
        assertEquals("[E][ ] return file (From: 10 December 2024 at 08:00 pm To: 11 December 2024 at 08:00 pm)",new Event("return file",dp.convertToDateTime("10/12/2024 2000"), dp.convertToDateTime("11/12/2024 2000")).toString());

    }
}
