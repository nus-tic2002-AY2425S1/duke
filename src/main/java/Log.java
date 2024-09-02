public class Log {
    public static void printMsg(String... messages) {
        for (String message :messages) {
            println(message);
        }
        printSeperator();
    }

    public static void printSeperator() {
        println("____________________________________________________________");
    }

    private static void println(String message) {
        System.out.println(message);
    }
}
