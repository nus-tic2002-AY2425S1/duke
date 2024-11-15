package pistamint.ui;
import pistamint.*;
import pistamint.general.Task;
import pistamint.taskList.TaskList;

import java.util.ArrayList;

public class Ui {
    private static TaskList taskList;
    private static String line = "-".repeat(50);
    public static String start = "\t" + line;
    public static String end = "\n\t" + line;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * This method prints out the welcome message when user first launched the program
     */
    public static void showGreeting() {
        System.out.println(start + "\n\tNi Hao! I'm PistaMint\n\tWhat can I do for you?" + end);
    }

    /**
     * This method print out the text when user initiate command to close off the program
     */
    public static void showGoodbye() {
        System.out.println(start + "\n\tXie Xie! Hope to see you again soon~" + end);
    }

    /**
     * This method prints out the task information when added. If input is "bye" then called for the goodbye message
     *
     * @param task  refers to the task that is being added into the list
     * @param input refers to the command that is being used
     */
    public static void showTaskAdded(Task task, String input) {
        if (input.equalsIgnoreCase("bye")) {
            showGoodbye();
            return;
        }
        System.out.println(start + "\n\tGot it! I've added this task ");
        System.out.println("\t" + "[" + task.getSymbol() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("\tNow you have " + taskList.getSize() + " task(s) in the list.\n\t" + line);

    }

    /**
     * This method prints out the task information when task are updated.
     * @param task refers to the task that is being selected for update
     */
    public static void showTaskUpdated(Task task) {
        System.out.println(start + "\n\tGot it! I've updated this task ");
        System.out.println("\t" + "[" + task.getSymbol() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription() + end);
    }

    /**
     * This method prints out the task information of the task that is going to be removed
     * @param task refers to the task that is to be removed
     */
    public static void showTaskRemoved(Task task) {
        System.out.println(start + "\n\tNoted! I've removed this task:");
        System.out.println("\t[" + task.getSymbol() + "][" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("\tNow you have " + (taskList.getSize() - 1) + " task(s) in the list." + end);
    }

    /**
     * This method print out the task information when it is marked as completed
     * @param task refers to the task that is to be marked completed.
     */
    public static void showTaskMarked(Task task) {
        System.out.println(start + "\n\tNice! I've marked this task as done:\n\t[" + task.getStatusIcon() + "] " + task.getDescription() + end);
    }

    /**
     * This method prints out the task information when it is unmarked, to show that it is not completed
     * @param task refers to the task that is to be unmarked
     */
    public static void showTaskUnMarked(Task task) {
        System.out.println(start + "\n\tOK, I've marked this task as not done yet!\n\t[" + task.getStatusIcon() + "] " + task.getDescription() + end);
    }

    /**
     * This method prints out information of all task that exist in the task list.
     */
    public static void printItems() {
        System.out.println(Ui.start);
        ArrayList<Task> tasks = taskList.getTasks();
        loopArray(tasks);
    }

    /**
     * This method prints out task information on the matching items that user have searched for
     * @param arrayList contains all the task entries that match user searched for
     */
    public static void printMatchingTask(ArrayList<Task> arrayList) {
        if (!arrayList.isEmpty()) {
            System.out.println(Ui.start + "\n\tHere are the matching tasks in your list:");
            loopArray(arrayList);
        } else {
            System.out.println(Ui.start + "\n\tThere is not matching tasks in your list." + end);
        }
    }

    /**
     * This method loops through array to print out items in the ArrayList
     * @param arrayList stores the task in the ArrayList
     */
    private static void loopArray(ArrayList<Task> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println("\t" + (i + 1) + ".[" + arrayList.get(i).getSymbol() + "][" + arrayList.get(i).getStatusIcon() + "] " + arrayList.get(i).getDescription());
        }
        System.out.println(Ui.start);
    }

    /**
     * This method prints out the runtime error exception in a more user-friendly and readable manner.
     * @param e refers to the runtime error message
     */
    public static void runTimeException(String e) {
        System.out.println(Ui.start + "\n\tRunTime exception occurred. Error is due to: " + e + Ui.end);
    }
    /**
     * This method prints out the indexOutOfBound error exception in a more user-friendly and readable manner.
     * Indicating to user the number of items currently in the task list.
     */
    public static void indexOutOfBound() {
        System.out.println(Ui.start + "\n\tItem is out of range. You currently only have " + (taskList.getSize()) + " items." + Ui.end);
    }

    /**
     * This method prints out the numberFormat error exception in a more user-friendly and readable manner.
     * Indicating to user that the format that they have entered is invalid. Prompt them to key in the correct format
     */
    public static void numberFormat(String command) {
        System.out.println(Ui.start + "\n\tYour input is incorrect, please input the command '" + command + "' follow by an Integer\n\teg. " + command + " 1" + Ui.end);
    }

    /**
     * This method prints out the dateTimeException error exception in a more user-friendly and readable manner.
     * Indicating to user that the format that they have entered is invalid. Prompt them to key in the correct format
     */
    public static void dateTimeException() {
        System.out.println(Ui.start + "\n\tThe date format you have keyed in is invalid. Please key in the following format 'yyyy-MM-dd'" + Ui.end);
    }

    /**
     * This method prints out the stringIndexOutOfBound error exception in a more user-friendly and readable manner.
     * Indicating to user that the format that they have entered is invalid. Prompt them to key in the correct format
     */
    public static void stringIndexOutOfBound(String t) {
        System.out.println(Ui.start + "\n\tOOPS!!! The description of " + t + " is incomplete." + Ui.end);
    }

    /**
     * This method prints out the task information when it is cloned, and indicate the current number of items in the task list.
     * @param task refers to the task that is cloned.
     */
    public static void showItemCloned(Task task) {
        System.out.println("\tItem successfully cloned and added to list");
        System.out.println("\t" + "[" + task.getSymbol() + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("\tNow you have " + taskList.getSize() + " task(s) in the list.\n\t" + line);
    }
}
