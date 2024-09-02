package duke.dancepop;

public class Log {
    public static void printMsg(String... messages) {
        for (String message :messages) {
            println(message);
        }
        printSeparator();
    }

    public static void printSeqMsg(String... messages) {
        int counter = 1;
        for (String message : messages) {
            println(counter + ". " + message);
            counter++;
        }
        printSeparator();
    }

    public static void printSeparator() {
        println("____________________________________________________________");
    }

    private static void println(String message) {
        System.out.println(message);
    }
}
