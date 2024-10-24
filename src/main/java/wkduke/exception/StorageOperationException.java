package wkduke.exception;

public class StorageOperationException extends WKDukeException {
    public StorageOperationException(String message) {
        super(message);
    }

    public StorageOperationException(String message, String detail) {
        super(message, detail);
    }

    public StorageOperationException(String message, String detail, String help) {
        super(message, detail, help);
    }
}
