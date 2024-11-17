package ruan.parser;

import ruan.command.*;
import ruan.exception.*;

/**
 * Represents parser that processes user input and returns the appropriate command
 * Parser interprets the user input and creates the relevant command to execute
 */

public class Parser {

    /**
     * Parses the user input and returns the appropriate command
     * This method interprets the input string to determine which command the user wants to execute
     * @param input The user input as a string
     * @return The command that corresponds to the user's input
     * @throws RuanException If the input does not match any known commands
     */
    public static Command parse(String input) throws RuanException {
        String trimmedInput = input.trim().toLowerCase();

        if (trimmedInput.equals("bye")) {
            return new ExitCommand();
        } else if (trimmedInput.startsWith("todo")) {
            return new AddCommand("todo", trimmedInput.replace("todo", "").trim());
        } else if (trimmedInput.startsWith("deadline")) {
            return new AddCommand("deadline", trimmedInput.replace("deadline", "").trim());
        } else if (trimmedInput.startsWith("event")) {
            return new AddCommand("event", trimmedInput.replace("event", "").trim());
        } else if (trimmedInput.startsWith("delete")) {
            return new DeleteCommand(Integer.parseInt(trimmedInput.split(" ")[1]) - 1);
        } else if (trimmedInput.startsWith("mark")) {
            return new SetDoneCommand(Integer.parseInt(trimmedInput.split(" ")[1]) - 1, true);
        } else if (trimmedInput.startsWith("unmark")) {
            return new SetDoneCommand(Integer.parseInt(trimmedInput.split(" ")[1]) - 1, false);
        } else if (trimmedInput.equals("list")) {
            return new ListCommand();
        } else {
            throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);
        }
    }
}
