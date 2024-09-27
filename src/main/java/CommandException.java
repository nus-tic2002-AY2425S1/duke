// https://stackoverflow.com/questions/8423700/how-to-create-a-custom-exception-type-in-java
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }
}
