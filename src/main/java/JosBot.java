import java.util.Scanner;

public class JosBot {
    public static void main(String[] args) {
        String user_input = "";
        String start_txt = "____________________________________________________________\n"
                +"Hello I'm JosBot\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(start_txt);
        String[] list = new String[100];
        int list_count = 0;

        while(!user_input.equals("bye"))
        {
            Scanner in = new Scanner(System.in);
            user_input = in.nextLine();
            if(user_input.equals("list"))
            {
                System.out.println("____________________________________________________________\n");

                for(int i = 1; i < list_count+1; i++)
                {
                    System.out.println( i+ ". "+list[i-1]);
                }
                System.out.println("____________________________________________________________\n");
            }
            else
            {
                String mid_txt = "____________________________________________________________\n"
                        +"added: "+user_input+"\n"
                        + "____________________________________________________________\n";
                System.out.println(mid_txt);
                list[list_count] = user_input;
                list_count++;
            }

        }

        String end_txt = "Bye. Hope to see you again soon!\n"
                +"____________________________________________________________\n";
        System.out.println(end_txt);
    }
}
