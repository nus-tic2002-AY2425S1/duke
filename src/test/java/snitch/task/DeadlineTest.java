package snitch.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Deadline class.
 */
public class DeadlineTest {

    @Test
    public void testDeadlineCreationWithValidDate() {
        Deadline deadline = new Deadline("submit report", "2/12/2023 1800");
        assertEquals("[D][ ] submit report (by: Dec 02 2023, 6:00 pm)", deadline.toString());
    }

    @Test
    public void testDeadlineCreationWithPlainText() {
        Deadline deadline = new Deadline("review submission", "next Monday");
        assertEquals("[D][ ] review submission (by: next Monday)", deadline.toString());
    }
}