package exception;

/**
 * Represents an exception that occurs due to issues with the content of a file in the Javaro application.
 * The FileContentException class extends the JavaroException to provide specific error handling
 * for issues related to file content, such as invalid formats, missing data, or corruption.
 */
public class FileContentException extends JavaroException {

    public FileContentException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Constructs a FileContentException with the specified error message,
     * additional information, and usage instructions.
     *
     * @param errorMessage   the main error message associated with the exception.
     * @param detailedInfo   additional details about the error.
     * @param expectedFormat usage instructions related to the expected format.
     */
    public FileContentException(String errorMessage, String detailedInfo, String expectedFormat) {
        super(errorMessage, detailedInfo, expectedFormat);
    }
}
