// https://stackoverflow.com/questions/8423700/how-to-create-a-custom-exception-type-in-java

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class CommandException extends JavaroException {
    private final String info;
    private final String usage;

    public CommandException(String error) {
        super(error);
        this.info = "";
        this.usage = "";
    }

    public CommandException(String error, String info, String usage) {
        super(error);
        this.info = info;
        this.usage = usage;
    }

    public ArrayList<String> getMessageList() {
        ArrayList<String> messages = new ArrayList<>(Arrays.asList(super.getMessage()));
        if (!info.isEmpty()) {
            messages.add(info);
        }
        if (!usage.isEmpty()) {
            messages.add(usage);
        }
        return messages;
    }
}
