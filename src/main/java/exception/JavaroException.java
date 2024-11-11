package exception;

import java.util.ArrayList;
import java.util.Arrays;

import common.Constants;

/**
 * Acts as a base class for custom exceptions in the application.
 * 
 * This class extends the standard Exception class to include additional fields
 * for detailed information and usage instructions related to the error.
 */
public class JavaroException extends Exception {

    protected String info = Constants.EMPTY_STRING;
    protected String usage = Constants.EMPTY_STRING;

    /**
     * Constructs a JavaroException with the specified error message.
     * 
     * @param error represents the main error message associated with the exception.
     */
    public JavaroException(String error) {
        super(error);
    }

    /**
     * Constructs a JavaroException with the specified error message and additional information.
     * 
     * @param error represents the main error message associated with the exception.
     * @param info represents additional details about the error.
     */
    public JavaroException(String error, String info) {
        super(error);
        this.info = info;
    }

    /**
     * Constructs a JavaroException with the specified error message, 
     * additional information and expected usage instructions.
     * 
     * @param error represents the main error message associated with the exception.
     * @param info represents additional details about the error.
     * @param usage usage instructions related to the expected format.
     */
    public JavaroException(String error, String info, String usage) {
        this(error, info);
        this.usage = usage;
    }

    /**
     * Returns a list of messages associated with this exception. 
     * This includes the main error message, additional information 
     * (if provided), and expected usage instructions (if provided).
     * This method constructs a list of messages that can be used to provide 
     * comprehensive feedback to the user regarding the nature of the exception.
     * 
     * @return a list of strings containing the main error message, detailed 
     * information, and expected usage instructions related to this exception.
     */
    public ArrayList<String> getMessageList() {
        ArrayList<String> messages = new ArrayList<>(Arrays.asList(super.getMessage()));

        if (!info.isEmpty()) {
            messages.add(info);
        }
        
        if (!usage.isEmpty()) {
            messages.add(usage);
        }
        
        return messages;
    }
 
}