import java.util.Scanner;

public class DukeGPT {
    public static void main(String[] args) {
        String chatbotName = "DukeGPT";
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true){
            String userInput = scanner.nextLine();
            if(userInput.equalsIgnoreCase("bye")){
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
        }
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
