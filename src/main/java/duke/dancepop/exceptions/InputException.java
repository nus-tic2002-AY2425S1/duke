package duke.dancepop.exceptions;

import java.util.ArrayList;
import java.util.List;

public class InputException extends Exception {

    protected List<String> message;

    public InputException(String message) {
        this.message = new ArrayList<>();
        this.message.add(message);
    }

    public InputException(List<String> message) {
        this.message = message;
    }

    public List<String> getMessages() {
        return message;
    }
}
