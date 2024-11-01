package exception;

public class FileContentException extends JavaroException {
    public FileContentException(String error) {
        super(error);
    }

    public FileContentException(String error, String info) {
        super(error, info);
    }

    public FileContentException(String error, String info, String usage) {
        super(error, info, usage);
    }
}
