package duke.dancepop.exceptions;

import duke.dancepop.Log;

public class MissingInputException extends Throwable {

    // TODO: Come up with own message
    public MissingInputException(String message) {
        Log.printMsg("OOPS!!! The description of a " + message + " cannot be empty.");
    }
}
