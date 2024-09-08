import java.util.Scanner;

public class Snitch {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Snitch!\n" + "What can I do for you?\n" );

        // Set up for user input
        Scanner scanner = new Scanner(System.in);

        // keep accepting user input
        while (true) {
            // Get input from the user
            String userInput = scanner.nextLine();

            // Check if the user types "bye"
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;  // Exit the loop and terminate
            }

            // Echo the user's input
            System.out.println(userInput);
        }

        // Close the scanner
        scanner.close();
    }

    }

