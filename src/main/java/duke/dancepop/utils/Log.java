package duke.dancepop.utils;

/**
 * Utility class for printing formatted messages to the console.
 */
public class Log {

    /**
     * Prints a message or multiple messages to the console, each separated by a line.
     * Each message is printed between separators.
     *
     * @param messages One or more messages to print.
     */
    public static void printMsg(String... messages) {
        printSeparator();
        for (String message : messages) {
            println(message);
        }
        printSeparator();
    }

    /**
     * Prints a sequenced list of messages with a header.
     * Each message is prefixed with its sequence number and is printed between separators.
     *
     * @param header   The header message displayed at the top of the list.
     * @param messages The messages to print, each prefixed with a sequence number.
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

    /**
     * Prints a separator line to the console.
     * Used to visually separate messages for better readability.
     */
    public static void printSeparator() {
        println("____________________________________________________________");
    }

    /**
     * Prints a single message to the console with a tab for indentation.
     * This method is used internally by other methods to ensure consistent formatting.
     *
     * @param message The message to print.
     */
    private static void println(String message) {
        System.out.println("\t" + message);
    }
}
