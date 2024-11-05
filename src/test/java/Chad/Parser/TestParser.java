package Chad.Parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import Chad.Command.AddCommand;
import Chad.Command.Command;
import Chad.Command.DeleteCommand;
import Chad.Command.ListByDateCommand;
import Chad.Command.ListCommand;
import Chad.Command.MarkTaskCommand;
import Chad.Command.UnmarkTaskCommand;
import Chad.Exception.ChadException;

public class TestParser {

    @Test
    void testParseTodoCommand() throws ChadException {
        Command cmd = Parser.parse("todo read book");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void testParseDeadlineCommand() throws ChadException {
        Command cmd = Parser.parse("deadline submit report /by 2024-11-03");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void testParseEventCommand() throws ChadException {
        Command cmd = Parser.parse("event meeting /from 2024-11-03 /to 2024-11-04");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    void testParseDeleteCommand() throws ChadException {
        Command cmd = Parser.parse("delete 1");
        assertTrue(cmd instanceof DeleteCommand);
    }

    @Test
    void testParseMarkCommand() throws ChadException {
        Command cmd = Parser.parse("mark 1");
        assertTrue(cmd instanceof MarkTaskCommand);
    }

    @Test
    void testParseUnmarkCommand() throws ChadException {
        Command cmd = Parser.parse("unmark 1");
        assertTrue(cmd instanceof UnmarkTaskCommand);
    }

    @Test
    void testParseListCommandWithParameter() throws ChadException {
        Command cmd = Parser.parse("list 2024-11-03");
        assertTrue(cmd instanceof ListByDateCommand);
    }

    @Test
    void testParseListCommandWithoutParameter() throws ChadException {
        Command cmd = Parser.parse("list");
        assertTrue(cmd instanceof ListCommand);
    }
}