import java.util.Scanner;

public class KLbot {
    public static void main(String[] args) {
        String botName = "KLbot";
        Scanner in = new Scanner(System.in);

        printLine();
        System.out.println(" Hey there! I'm " + botName + ", your cheerful helper buddy! \uD83D\uDE0A");
        System.out.println(" What can I do for you today? (Type 'bye' when you're ready to go!)");
        printLine();

        while (true) {
            String userInput = in.nextLine();
            String userExit="bye";
            if (userInput.equalsIgnoreCase(userExit)) {
                break;
            }
            printLine();
            System.out.println("Got it! You said: \"" + userInput + "\" \uD83D\uDE0A");
            System.out.println("Is there anything else I can help you with? Just let me know!");
            printLine();
        }

        printLine();
        System.out.println("Aww, you're leaving? It was so nice chatting with you! Take care and see you soon! \uD83D\uDC96");
        printLine();

        in.close();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
