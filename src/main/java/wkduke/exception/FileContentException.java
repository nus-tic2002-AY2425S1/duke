package wkduke.exception;

/**
 * Represents an exception that is thrown when an error is encountered with the content of a file.
 * This exception is used to signal issues such as invalid data format or unexpected content in files.
 */
public class FileContentException extends WKDukeException {
    /**
     * Constructs a FileContentException with the specified error message.
     *
     * @param message The error message describing the file content issue.
     */
    public FileContentException(String message) {
        super(message);
    }

    /**
     * Constructs a FileContentException with the specified error message and detailed information.
     *
     * @param message The error message describing the file content issue.
     * @param detail  Additional detail about the file content issue.
     */
    public FileContentException(String message, String detail) {
        super(message, detail);
    }

    /**
     * Constructs a FileContentException with the specified error message, detailed information, and help text.
     *
     * @param message The error message describing the file content issue.
     * @param detail  Additional detail about the file content issue.
     * @param help    Suggested help or guidance for resolving the issue.
     */
    public FileContentException(String message, String detail, String help) {
        super(message, detail, help);
    }
}
