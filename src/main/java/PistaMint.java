import java.util.Scanner;

public class PistaMint {
    public static int length = 50;
    public static String line = "-".repeat(length);
    public static String input="";

    public static void greetings() {
        System.out.println("\t"+line + "\n\tNi Hao! I'm PistaMint\n\tWhat can I do for you?\n\t" + line);

    }

    public static void exit() {
        System.out.println("\t"+line+"\n\tXie Xie! Hope to see you again soon~\n\t" + line);
    }

    public static void echo(String input) {
        if(input.equalsIgnoreCase("bye")){
            return;
        }
        System.out.println("\t"+line + "\n\t" + input + "\n\t" + line);
    }

    public static void main(String[] args) {
        greetings();
        while (!input.equalsIgnoreCase("bye")) {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            echo(input);
        }
        exit();
    }
}
