package exception;

public class StorageOperationException extends JavaroException {
    public StorageOperationException(String error) {
        super(error);
    }
    
    public StorageOperationException(String error, String info) {
        super(error, info);
    }

}