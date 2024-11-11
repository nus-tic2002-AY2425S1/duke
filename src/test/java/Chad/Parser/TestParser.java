package Chad.Parser;

import Chad.Command.*;
import Chad.Exception.ChadException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

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
