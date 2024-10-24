package wkduke.exception;

public class CommandOperationException extends WKDukeException {
    public CommandOperationException(String message) {
        super(message);
    }

    public CommandOperationException(String message, String detail) {
        super(message, detail);
    }

    public CommandOperationException(String message, String detail, String help) {
        super(message, detail, help);
    }
}
