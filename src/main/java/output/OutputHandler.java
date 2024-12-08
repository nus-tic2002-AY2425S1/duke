package output;

import tasks.Task;

import java.util.List;

/**
 * Handles all outputs for DukeGPT Application
 */
public class OutputHandler {
    /**
     * Prints a line separator to the console for better readability
     */
    private static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Print greeting messages
     * @param chatbotName The name of DukeGPT
     */
    public static void printGreetings(String chatbotName){
        printSeparator();
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        printSeparator();
    }

    /**
     * Print goodbye message
     */
    public static void printGoodbye(){
        printSeparator();
        System.out.println(" Bye. Hope to see you again soon!");
        printSeparator();
    }

    /**
     * Print a message of the task that has been added to TaskList
     * @param newTask The new task that has been added
     * @param tasks The list of task objects
     */
    public static void printAddedItems(Task newTask, List<Task> tasks){
        printSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printSeparator();
    }

    /**
     * Print error message to console
     * @param error Error message to be printed
     */
    public static void printError(String error){
        printSeparator();
        System.out.println(error);
        printSeparator();
    }

    /**
     * Prints a message that is indicated as marked done
     * @param taskIndex The index of task
     * @param tasks The list of task objects
     */
    public static void printMarkDone(int taskIndex, List<Task> tasks){
        printSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskIndex));
        printSeparator();
    }

    /**
     * Prints a message that is indicated unmarked as done
     * @param taskIndex The index of task
     * @param tasks The list of task objects
     */
    public static void printUnmarkDone(int taskIndex, List<Task> tasks) {
        printSeparator();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(taskIndex));
        printSeparator();
    }

    /**
     * Prints the list of tasks
     * @param tasks The list of task objects
     */
    public static void printList(List<Task> tasks) {
        printSeparator();
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasks.size(); i++){
            System.out.println("  " + (i+1) +"." + tasks.get(i));
        }
        printSeparator();
    }

    /**
     * Prints a message that a task has been deleted from the TaskList.
     *
     * @param taskIndex The index of task
     * @param tasks The list of task objects
     */
    public static void printDeleteItem(int taskIndex, List<Task> tasks) {
        printSeparator();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(taskIndex));
        System.out.println("Now you have " + (tasks.size()-1) + " tasks in the list.");
        printSeparator();
    }
    /**
     * Prints a message that a task has been updated in the TaskList.
     *
     * @param taskIndex The index of task
     * @param tasks The list of task objects
     */
    public static void printUpdateItem(int taskIndex, List<Task> tasks) {
        printSeparator();
        System.out.println("Noted. I've updated this task:");
        System.out.println("  " + tasks.get(taskIndex));
        printSeparator();
    }

}
