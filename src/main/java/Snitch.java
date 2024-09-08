import java.util.Scanner;
import java.util.ArrayList;

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

        // Create a list to store the user inputs
        ArrayList<String> tasks = new ArrayList<>();
        // keep accepting user input
        while (true) {
            // Get input from the user
            String userInput = scanner.nextLine();

            // Check if the user types "bye"
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;  // Exit the loop and terminate
            }
            // Check if the user types "list"
            if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                // else add the user input to the list of tasks
                tasks.add(userInput);
                System.out.println("added: " + userInput);
            }
        }
        // Close the scanner
        scanner.close();
    }

    }

