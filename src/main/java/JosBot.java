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

            if(user_input.equals("list"))
            {
                System.out.println("Here are the tasks in your list:");
                for(int i = 1; i < list_count+1; i++)
                {
                    //System.out.println( i+ ". ["+list[i-1].getStatusIcon()+"] "+list[i-1].getDescription());
                    System.out.println(i+". "+list[i-1].toString());
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
            else if(user_input.startsWith("todo") || user_input.startsWith("deadline") || user_input.startsWith("event")){
                System.out.println("Got it. I've added this task:");
                if(user_input.startsWith("todo"))
                {
                    String todo_txt = user_input.replace("todo ","");
                    Task t = new Todo(todo_txt);
                    list[list_count] = t;
                    list_count++;
                    System.out.println(t.toString());
                }
                else if(user_input.startsWith("deadline")){
                    String deadline_txt = user_input.replace("deadline ","");
                    String[] split_txt = deadline_txt.split("/by",2);
                    deadline_txt = split_txt[0].trim();
                    //task name and deadline day
                    Task t = new Deadline(split_txt[0].trim(), split_txt[1].trim());
                    list[list_count] = t;
                    list_count++;
                    System.out.println(t.toString());
                }
                else if(user_input.startsWith("event")){
                    String event_txt = user_input.replace("event ","");
                    String[] split_txt = event_txt.split("/",3);
                    String event_name = split_txt[0].trim();
                    String event_from  = split_txt[1].replace("from ","").trim();
                    String event_to   = split_txt[2].replace("to ","").trim();
                    Task t = new Event(event_name, event_from, event_to);
                    list[list_count] = t;
                    list_count++;
                    System.out.println(t.toString());
                }

                System.out.println("Now you have "+list_count+" task in the list.");
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
