package Ui;
import General.Task;
import TaskList.TaskList;

import java.util.ArrayList;

public class Ui {
    private static TaskList taskList;
    private static String line="-".repeat(50);
    public static String start="\t"+line;
    public static String end="\n\t"+line;
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }
    public static void showGreeting(){
        System.out.println(start+"\n\tNi Hao! I'm PistaMint\n\tWhat can I do for you?"+end);
    }
    public static void showGoodbye() {
        System.out.println(start+"\n\tXie Xie! Hope to see you again soon~"+end);
    }
    public static void showTaskAdded(Task task, String input) {
        if (input.equalsIgnoreCase("bye")) {
            showGoodbye();
            return;
        }
        System.out.println("\n\tGot it! I've added this task ");
        System.out.println("\t" + "[" + task.getSymbol() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("\tNow you have " + taskList.getSize()  + " task(s) in the list.\n\t" + line);

    }
    public static void showTaskRemoved(Task task) {
        System.out.println(start + "\n\tNoted! I've removed this task:");
        System.out.println("\t[" + task.getSymbol() + "][" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("\tNow you have " + (taskList.getSize()-1) + " task(s) in the list."+end);
    }
    public static void showTaskMarked(Task task) {
        System.out.println(start+"\n\tNice! I've marked this task as done:\n\t[" + task.getStatusIcon() + "] " + task.getDescription()+end);
    }
    public static void showTaskUnMarked(Task task) {
        System.out.println("\t"+line+"\n\tOK, I've marked this task as not done yet!\n\t[" + task.getStatusIcon() + "] " + task.getDescription()+"\n\t"+line);
    }
    public static void printItems() {
        System.out.println(Ui.start);
        ArrayList<Task> tasks=taskList.getTasks();
        for(int i=0;i<tasks.size();i++) {
            System.out.println("\t" + (i + 1) + ".[" + tasks.get(i).getSymbol() + "][" + tasks.get(i).getStatusIcon() + "] " + tasks.get(i).getDescription());
        }
        System.out.println(Ui.start);
    }
}
