public class WKDuke {
    private static final String borderLine = "\t____________________________________________________________";
    private static final String stringIndent = "\t ";

    public static void main(String[] args) {
        String logo = """
                \t  ___       __   ___  __    ________  ___  ___  ___  __    _______     \s
                \t |\\  \\     |\\  \\|\\  \\|\\  \\ |\\   ___ \\|\\  \\|\\  \\|\\  \\|\\  \\ |\\  ___ \\    \s
                \t \\ \\  \\    \\ \\  \\ \\  \\/  /|\\ \\  \\_|\\ \\ \\  \\\\\\  \\ \\  \\/  /|\\ \\   __/|   \s
                \t  \\ \\  \\  __\\ \\  \\ \\   ___  \\ \\  \\ \\\\ \\ \\  \\\\\\  \\ \\   ___  \\ \\  \\_|/__ \s
                \t   \\ \\  \\|\\__\\_\\  \\ \\  \\\\ \\  \\ \\  \\_\\\\ \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\_|\\ \\\s
                \t    \\ \\____________\\ \\__\\\\ \\__\\ \\_______\\ \\_______\\ \\__\\\\ \\__\\ \\_______\\
                \t     \\|____________|\\|__| \\|__|\\|_______|\\|_______|\\|__| \\|__|\\|_______|
                """;

        System.out.println(borderLine);
        System.out.println(logo);
        System.out.println(stringIndent + "Hello! I'm WKDuke\n" + stringIndent + "What can I do for you?");
        System.out.println(borderLine);
    }
}
