import java.util.Scanner;

public class JosBot {
    public static void main(String[] args) {
        String user_input = "";
        String start_txt = "____________________________________________________________\n"
                +"Hello I'm JosBot\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(start_txt);

        while(!user_input.equals("bye"))
        {

            Scanner in = new Scanner(System.in);
            user_input = in.nextLine();
            String mid_txt = "____________________________________________________________\n"
                    +user_input+"\n"
                    + "____________________________________________________________\n";
            System.out.println(mid_txt);
        }

        String end_txt = "Bye. Hope to see you again soon!\n"
                +"____________________________________________________________\n";
        System.out.println(end_txt);
    }
}
