package duke.dancepop.utils;

public class Log {

    /**
     * @param messages CLI print all variadic Strings
     */
    public static void printMsg(String... messages) {
        printSeparator();
        for (String message : messages) {
            println(message);
        }
        printSeparator();
    }

    /**
     * @param header CLI header
     * @param messages CLI print all variadic Strings with sequencing
     */
    public static void printSeqMsg(String header, String... messages) {
        printSeparator();
        println(header);
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
        System.out.println("\t" + message);
    }
}
