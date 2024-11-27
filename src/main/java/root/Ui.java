package root;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        String logo = "\t _____   _____    _   _____ \n"
                    + "\t|_   _| |  _  \\  | | /  ___| \n"
                    + "\t  | |   | |_| |  | | | |___ \n"
                    + "\t  | |   |  _  /  | | \\___  \\\n"
                    + "\t _| |_  | | \\ \\  | |  ___| | \n"
                    + "\t|_____| |_|  \\_\\ |_| |_____/ \n";
        //System.out.println("\t____________________________________________________________");
        showLine();
        System.out.println("\tHello, I'm Iris \n " + logo);
        System.out.println("\tWhat can I do for you?\n");
        showLine(); 
                          // "\t____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks.");
    }

    public void showError(String message) {
        System.out.println("\t" + message);
    }

    public void showLine() {
        System.out.println("\t____________________________________________________________\n");
    }

    public void showGoodbye() {
        System.out.println("\t____________________________________________________________\n" + 
                           "\tBye. Hope to see you again soon!\r\n" + 
                           "\t____________________________________________________________");
    }

    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
