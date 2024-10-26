package StarkChatbot.UserUI;

import java.io.IOException;

public class StarkException {

    public static class InvalidDescriptionException extends IllegalArgumentException {
        public InvalidDescriptionException(String message) {
            super(message);
        }
    }

    public static class InvalidTaskException extends NumberFormatException {
        public InvalidTaskException(String message) {
            super(message);
        }
    }
    public static class InvalidCommandException extends IllegalArgumentException {
        public InvalidCommandException(String message) {
            super(message);
        }
    }

    public static class InvalidIndexException extends IndexOutOfBoundsException {
        public InvalidIndexException(String message) {
            super(message);
        }
    }

    public static class WriteToFileException extends IOException {
        public WriteToFileException(String message) {
            super(message);
        }
    }

    public static class FileNotFoundException extends Exception {
        public FileNotFoundException(String message) {
            super(message);
        }
    }
}
