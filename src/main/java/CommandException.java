// https://stackoverflow.com/questions/8423700/how-to-create-a-custom-exception-type-in-java
public class CommandException extends JavaroException {
    public CommandException(String errorMessage) {
        super(errorMessage);
    }

    public CommandException(String errorMessage, String problem) {
        super(errorMessage, problem);
    }

    public CommandException(String errorMessage, String problem, String solution) {
        super(errorMessage, problem, solution);
    }
}
