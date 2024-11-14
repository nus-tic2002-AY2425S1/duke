package wkduke.exception.storage;

/**
 * Represents a runtime exception that is thrown when there is an error with the storage file path.
 * This exception is used to indicate issues such as an invalid file path format.
 */
public class StorageFilePathException extends RuntimeException {
    /**
     * Constructs a StorageFilePathException with the specified error message.
     *
     * @param message The error message describing the file path issue.
     */
    public StorageFilePathException(String message) {
        super(message);
    }
}
