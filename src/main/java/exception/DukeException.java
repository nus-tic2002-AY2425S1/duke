package exception;

/**
 * Custom exception handling for DukeGPT related errors
 */
public class DukeException extends Exception{
    /**
     * Constructs a new DukeException with specific message.
     * @param message Message details
     */
    public DukeException(String message){
        super(message);
    }

    /**
     * Constructs a new DukeException with specific message and error message
     * @param message Message details
     * @param errorMessage Error messages invoked in the method
     */
    public DukeException(String message, String errorMessage) {
        super(message + ": " + errorMessage);
    }
}
