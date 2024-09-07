import java.util.Scanner;

public class DukeGPT {
    private static String chatbotName = "DukeGPT";
    private static Task[] task = new Task[100];
    private static int countTask = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printGreetings(chatbotName);
        while (true){
            String userInput = scanner.nextLine();
            if(userInput.equalsIgnoreCase("bye")){
                break;
            }
            else if (userInput.startsWith("mark")){
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                markTask(taskIndex);
            }
            else if(userInput.startsWith("unmark")){
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                unmarkTask(taskIndex);
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
        task[countTask] = new Task(userInput);
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

    public static void markTask(int taskIndex){
        task[taskIndex].markDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task[taskIndex]);
        System.out.println("____________________________________________________________");
    }

    public static void unmarkTask(int taskIndex){
        task[taskIndex].unmarkDone();
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task[taskIndex]);
        System.out.println("____________________________________________________________");
    }

}
