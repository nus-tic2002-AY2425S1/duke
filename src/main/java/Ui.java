import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

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

    public void showEnding(){
        String ending =
                """
                        ____________________________________________________________
                        Bye. Back to my wonderland!
                        ____________________________________________________________""";
        System.out.println(ending);
    }

    public void showLoadingError(){
        System.out.println("File cannot be loaded!");
    }

    public String readCommand(){
        Scanner input = new Scanner(System.in);
        //String[] instruction = line.split(" ");
        return input.nextLine();
    }

    public void showError(String message) {
        System.out.println("Oops! An error occured: " + message);
    }

    public void showSize(int size){
        System.out.println("Now you have "+ size +" in the list.");
    }
}
