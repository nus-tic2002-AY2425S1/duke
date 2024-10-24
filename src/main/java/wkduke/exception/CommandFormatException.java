package wkduke.exception;

public class CommandFormatException extends WKDukeException {
    public CommandFormatException(String message) {
        super(message);
    }

    public CommandFormatException(String message, String detail) {
        super(message, detail);
    }

    public CommandFormatException(String message, String detail, String help) {
        super(message, detail, help);
    }
}
