package ruan.exception;
/**
 * Represents a custom exception for the Ruan program/application
 * This exception is used to handle various error scenarios defined by the ErrorType enum
 */
public class RuanException extends Exception {
    private final ErrorType errorType;

    /**
     * Constructs a RuanException with the specified ErrorType
     * The error message is derived from the provided ErrorType
     * @param errorType The type of error that caused this exception
     */
    public RuanException(ErrorType errorType) {
        //get error message from ErrorType class
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    /**
     * Gets the ErrorType associated with this exception
     * @return The ErrorType that caused this exception
     */
    public ErrorType getErrorType() {
        return errorType;
    }
}
