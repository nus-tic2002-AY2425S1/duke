package duke.dancepop.exceptions;

public class FileException extends Exception {

    protected String message;

    public FileException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
