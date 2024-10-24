package wkduke.exception;

public class FileContentException extends WKDukeException {
    public FileContentException(String message) {
        super(message);
    }

    public FileContentException(String message, String detail) {
        super(message, detail);
    }

    public FileContentException(String message, String detail, String help) {
        super(message, detail, help);
    }
}
