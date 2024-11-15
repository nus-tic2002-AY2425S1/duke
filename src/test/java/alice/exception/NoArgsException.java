package alice.exception;

/**
 * <h1>No Arguments Exception</h1>
 * The NoArgsException class are executed when there are not
 * enough arguments for the creation of a specified task.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class NoArgsException extends Exception{
    private final String error;

    public NoArgsException(String error){
        this.error = error;
    }

    public String getMessage(){
        return "No "+ error +" in the alice.command!";
    }
}
