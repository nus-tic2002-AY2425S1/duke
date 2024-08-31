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

        //Array to store up to 100 tasks
        String[] tasks = new String[100];
        int taskCount = 0;

        //User input loop processing

        while(true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                //List tasks
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                //Add task to array
                tasks[taskCount] = userInput;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + userInput);
                System.out.println("____________________________________________________________");
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
