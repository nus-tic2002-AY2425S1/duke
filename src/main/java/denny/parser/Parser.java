package denny.parser;

import denny.command.*;
import denny.exception.DennyException;

/**
 * Parses user input commands and creates corresponding Command objects.
 * Supports various commands including list, mark, unmark, todo, deadline, event, delete, and bye.
 */
public class Parser {
    /**
     * Parses a user input string into a Command object.
     * @param fullCommand The complete command string from user input
     * @return A Command object corresponding to the user's input
     * @throws DennyException if the command is invalid or cannot be parsed
     */
    public Command parse(String fullCommand) throws DennyException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(arguments);
            case "unmark":
                return new UnmarkCommand(arguments);
            case "todo":
                return new AddTodoCommand(arguments);
            case "deadline":
                return new AddDeadlineCommand(arguments);
            case "event":
                return new AddEventCommand(arguments);
            case "delete":
                return new DeleteCommand(arguments);
            case "bulk-delete":
                return new BulkDeleteCommand(arguments);
            case "find":
                return new FindCommand(arguments);
            case "bye":
                return new ExitCommand();
            default:
                throw new DennyException("Unknown command: " + commandWord);
        }
    }
}
