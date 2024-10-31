import java.io.IOException;

public class StorageOperationException extends IOException {
    public StorageOperationException(String message) {
        super(message);
    }
}