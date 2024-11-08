package exception;

/**
 * A StorageOperationException will be thrown when an error occurs during storage operations, e.g. loading tasks to TaskList, saving tasks to storage, etc.
 * 
 * <p>
 * This class extends {@link JavaroException} to provide specific error handling related to storage operations within the Javaro application.
 * It allows for the inclusion of additional information about the error that occurred during these operations.
 */
public class StorageOperationException extends JavaroException {
    
    /**
     * Constructs a StorageOperationException with the specified error message and additional information.
     * 
     * @param error represents the main error message associated with the exception
     * @param info represents additional details about the error that has occurred
     */
    public StorageOperationException(String error, String info) {
        super(error, info);
    }

}