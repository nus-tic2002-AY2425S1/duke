package denny.exception;

/**
 * Custom exception class for handling application-specific errors.
 */
public class DennyException extends Exception {
    /**
     * Creates a new DennyException with the specified error message.
     * @param message The error message
     */
    public DennyException(String message) {
        super(message);
    }
}
