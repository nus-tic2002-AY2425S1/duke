package ruan.parser;

import ruan.command.*;
import ruan.exception.*;
import ruan.constant.Constants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * With the use of ChatGPT to implement the methods
 */

public class ParserTest {

    @Test
    public void parse_validCommand_returnsCorrectCommandForReadBook() throws RuanException {
        Command command = Parser.parse("todo Read book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_validCommand_returnsCorrectCommandForBorrowBook() throws RuanException {
        Command command = Parser.parse("todo Borrow book");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_validCommand_returnsCorrectCommandForReturnBook() throws RuanException {
        Command command = Parser.parse("deadline Return book /by 2024-11-17");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_validCommand_returnsCorrectCommandForSubmitProjectString() throws RuanException {
        Command command = Parser.parse("deadline Submit Project /by Sun 2024-11-17");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_validCommand_returnsCorrectCommandForSubmitProjectDateTime() throws RuanException {
        Command command = Parser.parse("deadline Submit Project /by 2024-11-17 2359");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_validCommand_returnsCorrectCommandForExamString() throws RuanException {
        Command command = Parser.parse("event Exam /from Sat 2024-11-30 /to sat 2024-11-30");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_validCommand_returnsCorrectCommandForExamDateTime() throws RuanException {
        Command command = Parser.parse("event Exam /from 2024-11-30 0900 /to 2024-11-30 1100");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_validCommand_returnsCorrectCommandForExamDate() throws RuanException {
        Command command = Parser.parse("event Exam /from 2024-11-30 /to 2024-11-30");
        assertTrue(command instanceof AddCommand);
    }

    @Test
    public void parse_deleteCommand_returnsDeleteCommand() throws RuanException {
        Command command = Parser.parse("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parse_markCommand_returnsSetDoneCommand() throws RuanException {
        Command command = Parser.parse("mark 2");
        assertTrue(command instanceof SetDoneCommand);
    }

    @Test
    public void parse_unmarkCommand_returnsSetDoneCommand() throws RuanException {
        Command command = Parser.parse("unmark 3");
        assertTrue(command instanceof SetDoneCommand);
    }
    
    @Test
    public void parse_listCommand_returnsListCommand() throws RuanException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }
    
    @Test
    public void parse_invalidCommand_throwsException() {
        assertThrows(RuanException.class, () -> Parser.parse("invalid command"));
    }

    @Test
    public void parse_emptyInput_throwsException() {
        assertThrows(RuanException.class, () -> Parser.parse(" "));
    }

    @Test
    public void parse_exitCommand_returnsExitCommand() throws RuanException {
        Command command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }
}
