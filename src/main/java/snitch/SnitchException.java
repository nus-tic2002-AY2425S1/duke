package snitch;

/**
 * Represents exceptions specific to the Snitch chatbot.
 * Used to handle errors related to user input and system operations.
 */
public class SnitchException extends Exception {

    /**
     * Constructs a new SnitchException with the specified error message.
     *
     * @param message The detailed error message.
     */
    public SnitchException(String message) {
        super(message);
    }
}