package duke.dancepop.exceptions;

import duke.dancepop.Log;

public class ParseInputException extends Throwable {

    // TODO: Come up with own message
    public ParseInputException() {
        Log.printMsg("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
