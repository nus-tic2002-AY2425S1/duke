package mochi.ui;

public class Ui {
    public static void response(String input) {
        assert input != null && !input.trim().isEmpty() : "Response message cannot be null or empty";
        printDivider();
        System.out.println(input);
        printDivider();
    }

    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }
    public static void ResponseSingleLine(String input) {
        assert input != null && !input.trim().isEmpty() : "Response message cannot be null or empty";
        System.out.println(input);
    }
}
