package exception;

public class DateTimeParserException extends JavaroException {
    public DateTimeParserException(String error) {
        super(error);
    }

    public DateTimeParserException(String error, String info) {
        super(error, info);
    }

    public DateTimeParserException(String error, String info, String usage) {
        super(error, info, usage);
    }
}