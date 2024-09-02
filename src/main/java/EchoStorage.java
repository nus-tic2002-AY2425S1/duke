import java.util.ArrayList;
import java.util.List;

public class EchoStorage {
    private static final List<String> messages = new ArrayList<>();

    public static void add(String message) {
        messages.add(message);
    }

    // Taken from ChatGpt to pass in varargs
    public static void list() {
        Log.printSeqMsg(messages.toArray(new String[0]));
    }
}
