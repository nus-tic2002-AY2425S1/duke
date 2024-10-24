public class InvalidTaskFormatException extends WKDukeException {
    public InvalidTaskFormatException(String message) {
        super(message);
    }

    public InvalidTaskFormatException(String message, String detail) {
        this(message);
        this.detail = detail;
    }

    public InvalidTaskFormatException(String message, String detail, String help) {
        this(message, detail);
        this.help = help;
    }
}
