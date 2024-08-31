import java.util.Scanner;

public class Denny {
    public static void main(String[] args) {
        // Greet the user
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Denny");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        //Create Scanner
        Scanner scanner = new Scanner(System.in);
        String userInput;

        while(true) {
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println(" " + userInput);
            System.out.println("____________________________________________________________");
        }

        // Exit message
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        //Close scanner
        scanner.close();
    }
}
