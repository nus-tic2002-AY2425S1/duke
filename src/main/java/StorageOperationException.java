import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class StorageOperationException extends JavaroException {
    public StorageOperationException(String error) {
        super(error);
    }
    
    public StorageOperationException(String error, String info) {
        super(error, info);
    }

}