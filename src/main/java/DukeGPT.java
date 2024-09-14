import java.util.Scanner;
import tasks.*;

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
                Task newTask  = CommandFactory.generateTask(userInput);
                printAddedItems(newTask);
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
    public static void printAddedItems(Task newTask){
        task[countTask] = newTask;
        countTask++;
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + countTask + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printList(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < countTask; i++){
            System.out.println("  " + (i+1) +"." + task[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static void markTask(int taskIndex){
        task[taskIndex].markDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task[taskIndex]);
        System.out.println("____________________________________________________________");
    }

    public static void unmarkTask(int taskIndex){
        task[taskIndex].unmarkDone();
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task[taskIndex]);
        System.out.println("____________________________________________________________");
    }

}
