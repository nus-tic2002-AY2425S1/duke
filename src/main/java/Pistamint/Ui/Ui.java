package Pistamint.Ui;
import Pistamint.General.Task;
import Pistamint.TaskList.TaskList;

import java.util.ArrayList;

public class Ui {
    private static TaskList taskList;
    private static String line="-".repeat(50);
    public static String start="\t"+line;
    public static String end="\n\t"+line;
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }
    /**
     * prints out the welcome message when user first launched the program
     */
    public static void showGreeting(){
        System.out.println(start+"\n\tNi Hao! I'm PistaMint\n\tWhat can I do for you?"+end);
    }
    /**
     * print out the text when user initiate command to close off the program
     */
    public static void showGoodbye() {
        System.out.println(start+"\n\tXie Xie! Hope to see you again soon~"+end);
    }
    /**
     * Prints out the task information when added. If input is "bye" then called for the goodbye message
     * @param task refers to the task that is being added into the list
     * @param input refers to the command that is being used
     */
    public static void showTaskAdded(Task task, String input) {
        if (input.equalsIgnoreCase("bye")) {
            showGoodbye();
            return;
        }
        System.out.println(start+"\n\tGot it! I've added this task ");
        System.out.println("\t" + "[" + task.getSymbol() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("\tNow you have " + taskList.getSize()  + " task(s) in the list.\n\t" + line);

    }

    /**
     * Prints out the task information of the task that is going to be removed
     * @param task refers to the task that is to be removed
     */
    public static void showTaskRemoved(Task task) {
        System.out.println(start + "\n\tNoted! I've removed this task:");
        System.out.println("\t[" + task.getSymbol() + "][" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("\tNow you have " + (taskList.getSize()-1) + " task(s) in the list."+end);
    }

    /**
     * Print out the task information when it is marked as completed
     * @param task refers to the task that is to be marked completed.
     */
    public static void showTaskMarked(Task task) {
        System.out.println(start+"\n\tNice! I've marked this task as done:\n\t[" + task.getStatusIcon() + "] " + task.getDescription()+end);
    }

    /**
     * Print out the task information when it is unmarked, to show that it is not completed
     * @param task refers to the task that is to be unmarked
     */
    public static void showTaskUnMarked(Task task) {
        System.out.println("\t"+line+"\n\tOK, I've marked this task as not done yet!\n\t[" + task.getStatusIcon() + "] " + task.getDescription()+"\n\t"+line);
    }

    /**
     * Print out information of all task that exist in the tasklist.
     */
    public static void printItems() {
        System.out.println(Ui.start);
        ArrayList<Task> tasks=taskList.getTasks();
        for(int i=0;i<tasks.size();i++) {
            System.out.println("\t" + (i + 1) + ".[" + tasks.get(i).getSymbol() + "][" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription());
        }
        System.out.println(Ui.start);
    }

    /**
     * Print out the runtime error exception in a more user-friendly and readable manner.
     * @param e refers to the runtime error message
     */
    public static void runTimeException(String e){
        System.out.println(Ui.start+"\n\tRunTime exception occurred. Error is due to: "+e+Ui.end);
    }
}
