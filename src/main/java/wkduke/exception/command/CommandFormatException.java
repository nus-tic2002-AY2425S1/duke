package wkduke.exception.command;

import wkduke.exception.WKDukeException;

/**
 * Represents an exception that is thrown when a command format error occurs.
 * This exception is used to signal issues with the structure or syntax of a user command.
 */
public class CommandFormatException extends WKDukeException {
    /**
     * Constructs a CommandFormatException with the specified error message, detailed information, and help text.
     *
     * @param message The error message describing the command format issue.
     * @param detail  Additional detail about the command format issue.
     * @param help    Suggested help or guidance for resolving the issue.
     */
    public CommandFormatException(String message, String detail, String help) {
        super(message, detail, help);
    }
}
