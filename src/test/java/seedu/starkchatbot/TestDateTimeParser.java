package seedu.starkchatbot;

import org.junit.jupiter.api.Test;
import starkchatbot.taskmanager.DateTimeParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDateTimeParser {

    @Test
    public void testDateParser1() {
        String parsedDateTime = DateTimeParser.parseDateTime("2023-05-15 1700");
        assertEquals("May 15 2023 05:00 pm", parsedDateTime);
    }

    @Test
    public void testDateParser2() {
        String parsedDateTime = DateTimeParser.parseDateTime("2000-10-01");
        assertEquals("October 01 2000", parsedDateTime);
    }

}
