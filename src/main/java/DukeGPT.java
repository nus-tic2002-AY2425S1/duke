import java.util.Scanner;

public class DukeGPT {
    private static String chatbotName = "DukeGPT";
    private static String[] task = new String[100];
    private static int countTask = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printGreetings(chatbotName);
        while (true){
            String userInput = scanner.nextLine();
            if(userInput.equalsIgnoreCase("bye")){
                break;
            }
            else if(userInput.equalsIgnoreCase("list")){
                printList();
            }
            else {
                printAddedItems(userInput);
            }
        }
        printGoodbye();
    }

    public static void printGreetings(String chatbotName){
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static void printGoodbye(){
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void printUserInput(String userInput){
        System.out.println("____________________________________________________________");
        System.out.println(userInput);
        System.out.println("____________________________________________________________");
    }
    public static void printAddedItems(String userInput){
        System.out.println("____________________________________________________________");
        System.out.println("added: " + userInput);
        task[countTask] = userInput;
        countTask++;
        System.out.println("____________________________________________________________");
    }
    public static void printList(){
        System.out.println("____________________________________________________________");
        for(int i = 0; i < countTask; i++){
            System.out.println(i+1 +". " + task[i]);
        }
        System.out.println("____________________________________________________________");
    }

}
