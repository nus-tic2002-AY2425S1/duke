package wkduke.exception;

/**
 * Represents a custom exception used in the WKDuke application for handling various error cases.
 * This exception includes additional fields for detailed error information and helpful guidance.
 */
public class WKDukeException extends Exception {
    protected String detail = null;
    protected String help = null;

    /**
     * Constructs a WKDukeException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public WKDukeException(String message) {
        super(message);
    }

    /**
     * Constructs a WKDukeException with the specified error message and detail.
     *
     * @param message The error message describing the exception.
     * @param detail  Additional detail about the exception.
     */
    public WKDukeException(String message, String detail) {
        this(message);
        this.detail = detail;
    }

    /**
     * Constructs a WKDukeException with the specified error message, detail, and help text.
     *
     * @param message The error message describing the exception.
     * @param detail  Additional detail about the exception.
     * @param help    Suggested help or guidance for resolving the issue.
     */
    public WKDukeException(String message, String detail, String help) {
        this(message, detail);
        this.help = help;
    }

    /**
     * Retrieves detailed information about the exception.
     *
     * @return The detail information as a {@code String}.
     */
    public String getDetail() {
        return detail;
    }

    /**
     * Sets detailed information about the exception.
     *
     * @param detail The detail information to set.
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * Retrieves helpful guidance or suggestions for resolving the exception.
     *
     * @return The help information as a {@code String}.
     */
    public String getHelp() {
        return help;
    }
}
