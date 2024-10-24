package wkduke.exception;

public class TaskFormatException extends WKDukeException {
    public TaskFormatException(String message) {
        super(message);
    }

    public TaskFormatException(String message, String detail) {
        super(message, detail);
    }

    public TaskFormatException(String message, String detail, String help) {
        super(message, detail, help);
    }
}
