package exception;

import java.util.ArrayList;
import java.util.Arrays;

public class JavaroException extends Exception {

    protected String info = "";
    protected String usage = "";

    public JavaroException(String error) {
        super(error);
    }

    public JavaroException(String error, String info) {
        super(error);
        this.info = info;
    }

    public JavaroException(String error, String info, String usage) {
        this(error, info);
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