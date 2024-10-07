package output;

import tasks.Task;

import java.util.List;

public class OutputHandler {

    private static void printSeparator() {
        System.out.println("____________________________________________________________");
    }
    public static void printGreetings(String chatbotName){
        printSeparator();
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        printSeparator();
    }
    public static void printGoodbye(){
        printSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeparator();
    }

    public static void printAddedItems(Task newTask, List<Task> tasks){
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printSeparator();
    }

    public static void printError(String error){
        printSeparator();
        System.out.println(error);
        printSeparator();
    }

    public static void printMarkDone(int taskIndex, List<Task> tasks){
        printSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskIndex));
        printSeparator();
    }

    public static void printUnmarkDone(int taskIndex, List<Task> tasks) {
        printSeparator();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(taskIndex));
        printSeparator();
    }

    public static void printList(List<Task> tasks) {
        printSeparator();
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++){
            System.out.println("  " + (i+1) +"." + tasks.get(i));
        }
        printSeparator();
    }

    public static void printDeleteItem(int taskIndex, List<Task> tasks) {
        printSeparator();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(taskIndex));
        System.out.println("Now you have " + (tasks.size()-1) + " tasks in the list.");
        printSeparator();
    }
}
