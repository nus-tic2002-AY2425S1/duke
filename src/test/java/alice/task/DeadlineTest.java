package alice.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dateTest() throws Exception {
        //ensure date is properly converted
        assertEquals("Nov 30 2024", new Deadline("exam", LocalDate.parse("2024-11-30")).getBy());
    }
}
