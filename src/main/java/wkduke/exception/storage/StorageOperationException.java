package wkduke.exception.storage;

import wkduke.exception.WKDukeException;

/**
 * Represents an exception that is thrown when an error occurs during a storage operation.
 * This exception is used to signal issues such as read/write failures or storage access errors.
 */
public class StorageOperationException extends WKDukeException {
    /**
     * Constructs a StorageOperationException with the specified error message and detailed information.
     *
     * @param message The error message describing the storage operation issue.
     * @param detail  Additional detail about the storage operation issue.
     */
    public StorageOperationException(String message, String detail) {
        super(message, detail);
    }
}
