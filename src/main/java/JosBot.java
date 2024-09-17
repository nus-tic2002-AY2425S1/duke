import java.util.Scanner;

public class JosBot {

    public static void printDash(){
        System.out.print("____________________________________________________________\n");
    }

    public static void main(String[] args) {

        String start_txt = "____________________________________________________________\n"
                +"Hello I'm JosBot\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(start_txt);
        Task[] list = new Task[100];
        int list_count = 0;
        Scanner in = new Scanner(System.in);
        String user_input = in.nextLine();;

        while(!user_input.equals("bye"))
        {
            printDash();
           // Scanner in = new Scanner(System.in);

            if(user_input.equals("list"))
            {
                for(int i = 1; i < list_count+1; i++)
                {
                    System.out.println( i+ ". ["+list[i-1].getStatusIcon()+"] "+list[i-1].getDescription());
                }
            }
            else if(user_input.contains("mark"))
            {
                String last_word = user_input.substring(user_input.length()-1);
                Integer last_digit = Integer.valueOf(last_word) - 1;

                if(user_input.startsWith("mark"))
                {
                    System.out.println("Nice! I've marked this task as done:");
                    list[last_digit].markAsDone();
                }
                else if(user_input.startsWith("unmark"))
                {
                    System.out.println("OK, I've marked this task as not done yet:");
                    list[last_digit].markAsNotDone();
                }
                System.out.println("["+list[last_digit].getStatusIcon()+"] "+list[last_digit].getDescription());
            }
            else
            {
                System.out.println("added: "+user_input);
                Task t = new Task(user_input);
                list[list_count] = t;
                list_count++;
            }
            printDash();
            user_input = in.nextLine();
        }

        String end_txt = "Bye. Hope to see you again soon!\n";
        printDash();
        System.out.println(end_txt);
    }
}
