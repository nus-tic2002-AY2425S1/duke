package Chad.Ui;

import java.util.Scanner;

import Chad.TaskList.Task;
import Chad.TaskList.TaskList;

public class TextUi implements Ui {
    // Attributes for UI
    private final String myline = "_________________________________________________________________";
    private final String logo = " Chad\n";
    private final Scanner scanner;

    public TextUi() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showWelcome() {
        System.out.println("Hello from " + logo + System.lineSeparator() + "What can I do for you?\n");
    }

    @Override
    public String readCommand() {
        return scanner.nextLine(); // Read user input
    }

    @Override
    public void showLine() {
        System.out.println(myline);
    }

    @Override
    public void showError(String errmsg) {
        System.out.println(myline);
        System.out.println(errmsg);
    }

    @Override
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!"); // Show goodbye message
    }

    @Override
    public void showTaskList(TaskList tasks) {
        System.out.println(myline);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getNoOfTask(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTaskById(i).toString());
        }
        System.out.println(myline);
    }

    @Override
    public  void showHelp(String helpContentString) {
        System.out.println(System.lineSeparator());
        System.out.println(helpContentString);
        System.out.println(System.lineSeparator());
    }

    @Override
    public void showDeleteTask(Task task, int noOfTask) {
        displayTaskAction("removed", task, noOfTask);
    }
    
    @Override
    public void showAddTask(Task task, int noOfTask) {
        displayTaskAction("added", task, noOfTask);
    }
    @Override
        public void showMarkTask(Task task) {
            System.out.println(myline);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task.toString());  // Assuming Task has an overridden toString() method
            System.out.println(myline);
        }
    
    @Override
        public void showUnMarkTask(Task task) {
            System.out.println(myline);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task.toString());  // Assuming Task has an overridden toString() method
            System.out.println(myline);
        }
    
    // Helper method to handle the common output formatting
    private void displayTaskAction(String action, Task task, int noOfTask) {
        System.out.println("Got it. I've " + action + " this task: " + task.toString() + 
                           System.lineSeparator() + 
                           "Now you have " + noOfTask + " tasks in the list.");
    }
}