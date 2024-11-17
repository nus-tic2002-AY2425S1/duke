package Chad.Parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
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
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void testParseDeadlineCommand() throws ChadException {
        Command cmd = Parser.parse("deadline submit report /by 2024-11-03");
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void testParseEventCommand() throws ChadException {
        Command cmd = Parser.parse("event meeting /from 2024-11-03 /to 2024-11-04");
        assertInstanceOf(AddCommand.class, cmd);
    }

    @Test
    void testParseDeleteCommand() throws ChadException {
        Command cmd = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, cmd);
    }

    @Test
    void testParseMarkCommand() throws ChadException {
        Command cmd = Parser.parse("mark 1");
        assertInstanceOf(MarkTaskCommand.class, cmd);
    }

    @Test
    void testParseUnmarkCommand() throws ChadException {
        Command cmd = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkTaskCommand.class, cmd);
    }

    @Test
    void testParseListCommandWithParameter() throws ChadException {
        Command cmd = Parser.parse("list 2024-11-03");
        assertInstanceOf(ListByDateCommand.class, cmd);
    }

    @Test
    void testParseListCommandWithoutParameter() throws ChadException {
        Command cmd = Parser.parse("list");
        assertInstanceOf(ListCommand.class, cmd);
    }
}
