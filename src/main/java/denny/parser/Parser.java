package denny.parser;

import denny.command.*;
import denny.exception.DennyException;

/**
 * Parses user input commands and creates corresponding Command objects.
 * Supports various commands including list, mark, unmark, todo, deadline, event, delete, and bye.
 */
public class Parser {

    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BULK_DELETE, FIND, BYE;

        // Converts a string command to a CommandType, handling case sensitivity
        public static CommandType fromString(String command) throws DennyException {
            try {
                return CommandType.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new DennyException("Unknown command: " + command);
            }
        }
    }

    public Command parse(String fullCommand) throws DennyException {
        String[] parts = fullCommand.split(" ", 2);
        String arguments = parts.length > 1 ? parts[1] : "";

        CommandType commandType = CommandType.fromString(parts[0]);

        switch (commandType) {
            case LIST:
                return new ListCommand();
            case MARK:
                return new MarkCommand(arguments);
            case UNMARK:
                return new UnmarkCommand(arguments);
            case TODO:
                return new AddTodoCommand(arguments);
            case DEADLINE:
                return new AddDeadlineCommand(arguments);
            case EVENT:
                return new AddEventCommand(arguments);
            case DELETE:
                return new DeleteCommand(arguments);
            case BULK_DELETE:
                return new BulkDeleteCommand(arguments);
            case FIND:
                return new FindCommand(arguments);
            case BYE:
                return new ExitCommand();
            default:
                throw new DennyException("Unknown command: " + parts[0]);
        }
    }

}