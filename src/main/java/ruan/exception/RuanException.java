package ruan.exception;
/**
 * Class to catch exceptions for Ruan 
 */
public class RuanException extends Exception {
    private final ErrorType errorType;

    public RuanException(ErrorType errorType) {
        //get error message from ErrorType class
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}
