package exception;

/**
 * Throws exception during the decoding of a task list.
 * 
 * <p>
 * This class extends {@link JavaroException} to provide specific error handling 
 * for issues that arise while decoding task lists within the application. 
 * It allows for the inclusion of detailed error messages, additional information, 
 * and usage instructions related to the decoding process.
 * </p>
 */
public class TaskListDecoderException extends JavaroException {
    /**
     * Constructs a TaskListDecoderException with the specified error message.
     * 
     * @param error represents the main error message associated with the exception. 
     * It provides information about the decoding error that occurred.
     */
    public TaskListDecoderException(String error) {
        super(error);
    }

    /**
     * Constructs a TaskListDecoderException with the specified error message, 
     * additional information, and usage instructions.
     * 
     * @param error represents the main error message associated with the exception. 
     * It provides information about the decoding error that occurred.
     * @param info represents additional details about the error that has occurred.
     * @param usage represents usage instructions on the expected format.
     */
    public TaskListDecoderException(String error, String info, String usage) {
        super(error, info, usage);
    }
}
