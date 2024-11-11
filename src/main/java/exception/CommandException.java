// https://stackoverflow.com/questions/8423700/how-to-create-a-custom-exception-type-in-java

package exception;

/**
 * Represents an exception that occurs during the execution of a command in the application.
 *
 * <p>
 * The CommandException class extends the JavaroException to provide specific error handling for command-related issues.
 * This can include invalid commands, command format errors, or other command execution problems.
 * </p>
 */
public class CommandException extends JavaroException {

    /**
     * Constructs a CommandException with the specified error message.
     *
     * @param error represents the error message associated with the exception.
     */
    public CommandException(String error) {
        super(error);
    }

    /**
     * Constructs a CommandException with the specified error message and additional information.
     *
     * @param error represents the error message associated with the exception.
     * @param info  represents the additional information about the error.
     */
    public CommandException(String error, String info) {
        super(error, info);
    }

    /**
     * Constructs a CommandException with the specified error message, additional information, and usage instructions.
     *
     * @param error represents the error message associated with the exception.
     * @param info  represents the additional information about the error.
     * @param usage represents the usage instructions for the command.
     */
    public CommandException(String error, String info, String usage) {
        super(error, info, usage);
    }
}
