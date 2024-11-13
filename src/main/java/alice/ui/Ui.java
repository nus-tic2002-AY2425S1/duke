package alice.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * <h1>Ui</h1>
 * The Ui class details the output the user
 * may see based on the actions by the user.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui(){
        this(System.in,System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showLine(){
        System.out.println("____________________________________________________________");
    }

    public void showWelcome(){
        String intro =
                "____________________________________________________________\nHello! I'm Alice, here to make you magically organized!\nHow may I help you?\n____________________________________________________________";
        System.out.println(intro);
    }

    public void showHelp(){
        String help = "These are the commands available for you:\nlist\nlist (date)\nfind (word)\nmark (list number)\nunmark (list number)\ntodo (task)\ndeadline (task) /by (date)\nevent (task) /from (date) /to (date)\ndelete (list number)\nbye";
        System.out.println(help);
    }


    public void showEnding(){
        String ending = "Bye. Back to my wonderland!";
        System.out.println(ending);
    }

    public void showLoadingError(){
        System.out.println("File cannot be loaded!");
    }

    public String readCommand(){
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void showError(String message) {
        System.out.println("Oops! An error occurred: " + message);
    }

    /**
     * This method prints out the size of the Task list whenever it is called.
     */
    public void showSize(int size){
        System.out.println("Now you have "+ size +" in the list.");
    }
}
