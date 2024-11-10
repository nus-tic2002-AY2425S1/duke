package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import commands.Command;
import commands.DeleteCommand;
import commands.MarkCommand;
import commands.ShowCommand;
import commands.UnmarkCommand;
import commands.add.DeadlineCommand;
import commands.add.EventCommand;
import commands.add.TodoCommand;
import common.Messages;
import exception.CommandException;

public class ParserTest {

    @Test
    public void parse_validTodoCommand_createsTodoCommand() throws CommandException {
        Command command = Parser.parse("todo take a walk");
        assertTrue(command instanceof TodoCommand);
        assertEquals("take a walk", ((TodoCommand) command).getDescription());
    }

    @Test
    public void parse_validDeadlineCommand_createsDeadlineCommand() throws CommandException {
        Command command = Parser.parse("deadline email clients for feedback /by 2023-10-01");
        assertTrue(command instanceof DeadlineCommand);
        assertEquals("email clients for feedback", ((DeadlineCommand) command).getDescription());
        assertEquals("2023-10-01", ((DeadlineCommand) command).getBy().toLocalDate().toString());
    }

    @Test
    public void parse_validEventCommand_createsEventCommand() throws CommandException {
        Command command = Parser.parse("event baby shower /from 2023-10-01 1000 /to 2023-10-01 1200");
        assertTrue(command instanceof EventCommand);
        assertEquals("baby shower", ((EventCommand) command).getDescription());
        assertEquals("2023-10-01T10:00", ((EventCommand) command).getStartDateTime().toString());
        assertEquals("2023-10-01T12:00", ((EventCommand) command).getEndDateTime().toString());
    }

    @Test
    public void parse_validMarkCommand_createsMarkCommand() throws CommandException {
        Command command = Parser.parse("mark 1");
        assertTrue(command instanceof MarkCommand);
        assertEquals(1, ((MarkCommand) command).getTaskNumber());
    }

    @Test
    public void parse_validUnmarkCommand_createsUnmarkCommand() throws CommandException {
        Command command = Parser.parse("unmark 2");
        assertTrue(command instanceof UnmarkCommand);
        assertEquals(2, ((UnmarkCommand) command).getTaskNumber());
    }

    @Test
    public void parse_validDeleteCommand_createsDeleteCommand() throws CommandException {
        Command command = Parser.parse("delete 3");
        assertTrue(command instanceof DeleteCommand);
        assertEquals(3, ((DeleteCommand) command).getTaskNumber());
    }

    @Test
    public void parse_invalidCommand_throwsCommandException() {
        CommandException exception = assertThrows(CommandException.class, () -> {
            Parser.parse("invalidCommand");
        });
        assertEquals(Messages.ERROR_INVALID_COMMAND, exception.getMessage());
    }

    @Test
    public void parse_emptyTodoDescription_throwsCommandException() {
        CommandException exception = assertThrows(CommandException.class, () -> {
            Parser.parse("todo ");
        });
        assertEquals(Messages.ERROR_INVALID_COMMAND_FORMAT + "todo.", exception.getMessage());
    }

    @Test
    public void parse_emptyDeadlineCommand_throwsCommandException() {
        CommandException exception = assertThrows(CommandException.class, () -> {
            Parser.parse("deadline ");
        });
        assertEquals(Messages.ERROR_INVALID_COMMAND_FORMAT + "deadline.", exception.getMessage());
    }

    @Test
    public void parse_emptyEventCommand_throwsCommandException() {
        CommandException exception = assertThrows(CommandException.class, () -> {
            Parser.parse("event ");
        });
        assertEquals(Messages.ERROR_INVALID_COMMAND_FORMAT + "event.", exception.getMessage());
    }

    @Test
    public void parse_invalidDeadlineFormat_throwsCommandException() {
        CommandException exception = assertThrows(CommandException.class, () -> {
            Parser.parse("deadline email clients for feedback /by invalidDate");
        });
        assertEquals(Messages.ERROR_INVALID_DATETIME_FORMAT, exception.getMessage());
    }

    @Test
    public void parse_eventEndBeforeStart_throwsCommandException() {
        CommandException exception = assertThrows(CommandException.class, () -> {
            Parser.parse("event baby shower /from 2023-10-01 12:00 /to 2023-10-01 10:00");
        });
        assertEquals(Messages.ERROR_INVALID_DATETIME_FORMAT, exception.getMessage());
    }

    @Test
    public void parse_validShowCommand_createsShowCommand() throws CommandException {
        Command command = Parser.parse("show 2023-10-01");
        assertTrue(command instanceof ShowCommand);
        assertEquals("2023-10-01", ((ShowCommand) command).getDate().toString());
    }

    @Test
    public void parse_invalidShowDate_throwsCommandException() {
        CommandException exception = assertThrows(CommandException.class, () -> {
            Parser.parse("show invalidDate");
        });
        assertEquals(Messages.ERROR_INVALID_DATETIME_FORMAT, exception.getMessage());
    }

    /*
    @Test
    public void prepareTaskNumberForCommand_throwsCommandException() {

        // Test Case: Invalid Task Number (Out of range)
        // CommandException invalidTaskNumber = assertThrows(CommandException.class, 
        //                                              () -> new MarkCommand(99999));
        

        // CommandException commandException = assertThrows(CommandException.class, () -> {
        //     Parser.parse("mark 99999");
        // });
        
        // assertEquals(Messages.ERROR_INVALID_COMMAND_FORMAT, commandException.getMessage());

        
        // Test Case: Invalid Task Number (Out of range)
        CommandException commandException = assertThrows(CommandException.class, () -> {
            Parser.parse("mark 99999"); // Assuming 99999 is out of valid range
        });

        // Check that the exception message matches the expected error message for invalid command format
        assertEquals(Messages.ERROR_INVALID_COMMAND_FORMAT + "mark", commandException.getMessage());
    }
    */

    public void parse_invalidTaskNumber_throwsCommandException(String command) {
        // Test Case: Invalid Task Number Format (not a number)
        CommandException commandException = assertThrows(CommandException.class, () -> {
            Parser.parse(command + " abc"); // Invalid task number format
        });

        // Check that the exception message matches the expected error message
        assertEquals(Messages.ERROR_INVALID_COMMAND_FORMAT + command, commandException.getMessage());
    }

    @Test
    public void parse_markInvalidTaskNumber_throwsCommandException() {
        parse_invalidTaskNumber_throwsCommandException("mark");
    }

    @Test
    public void parse_unmarkInvalidTaskNumber_throwsCommandException() {
        parse_invalidTaskNumber_throwsCommandException("unmark");
    }

    @Test
    public void parse_deleteInvalidTaskNumber_throwsCommandException() {
        parse_invalidTaskNumber_throwsCommandException("delete");
    }
}
