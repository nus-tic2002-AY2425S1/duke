package josbot.task;

import josbot.JosBotException;
import josbot.parser.DateTimeParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testDeadlineStringConversion() throws JosBotException {
        DateTimeParser dp = new DateTimeParser();
        assertEquals("[D][ ] return file (by: 10 December 2024)",new Deadline("return file",dp.convertDateTime("10/12/2024 2000")).toString());
    }


}
