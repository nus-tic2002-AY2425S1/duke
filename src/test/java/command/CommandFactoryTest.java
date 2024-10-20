package command;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

        @Test
        void validToDo() throws DukeException {
            Task task = CommandFactory.generateTask("todo Buy groceries");
            assertTrue(task instanceof ToDo);
            assertEquals("[T][ ] Buy groceries", task.toString());
        }

        @Test
        void validDeadline() throws DukeException {
            String input = "deadline Submit assignment /by 15/10/2023 2359";
            Task task = CommandFactory.generateTask(input);

            assertTrue(task instanceof Deadline);
            assertEquals("[D][ ] Submit assignment (by: 15 Oct 2023, 11:59 PM)", task.toString());
        }

        @Test
        void validEvent() throws DukeException {
            String input = "event Birthday party /from 20/10/2023 1800 /to 20/10/2023 2100";
            Task task = CommandFactory.generateTask(input);

            assertTrue(task instanceof Event);
            assertEquals("[E][ ] Birthday party (from: 20 Oct 2023, 6:00 PM to: 20 Oct 2023, 9:00 PM)", task.toString());
        }

}