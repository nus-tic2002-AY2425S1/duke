// https://stackoverflow.com/questions/8423700/how-to-create-a-custom-exception-type-in-java

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class CommandException extends JavaroException {
    public CommandException(String error) {
        super(error);
    }
    
    public CommandException(String error, String info) {
        super(error, info);
    }

    public CommandException(String error, String info, String usage) {
        super(error, info, usage);
    }
}
