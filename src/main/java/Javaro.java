import java.util.Scanner;

public class Javaro {
    
    public static void space(boolean isLine) {
        String space = "    ";

        // If space is to come before a horizontal line, use "    "
        if (isLine) {
            System.out.print(space);
        
        // If space is to come before a line of text, use "     "
        } else {
            System.out.print(space + " ");
        }
    }

    // This function prints a horizontal line
    public static void printLine() {
        space(true);
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    // Chatbot greets the user
    public static void greet() {
        printLine();
        String chatbotName = "Javaro";
        
        space(false);
        System.out.println("Hello, I am " + chatbotName + ".");
        
        space(false);
        System.out.println("What can I do for you?");
        printLine();
    }

    // Chatbot exits
    public static void exit() {
        printLine();
        
        space(false);
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    // This function echos commands entered by the user, and exits when the user types the command bye.
    public static void echo() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (input.equals("bye") == false) {
            printLine();
            space(false);
            System.out.println(input);
            printLine();
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }
}
