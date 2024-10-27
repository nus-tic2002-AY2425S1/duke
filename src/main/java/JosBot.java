import java.util.Scanner;

public class JosBot {

    public static void printInfo(String type){
        switch(type){
            case "Dash":
                System.out.print("____________________________________________________________\n");
                break;
            case "Start":
                printInfo("Dash");
                System.out.println("Hello I'm JosBot\nWhat can I do for you?");
                printInfo("Dash");
                break;
            case "End":
                printInfo("Dash");
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
        }

    }

    public static void main(String[] args) {

        printInfo("Start");
        Task[] list = new Task[100];
        int list_count = 0;
        Scanner in = new Scanner(System.in);
        String user_input = in.nextLine().trim();


        while(!user_input.equals("bye"))
        {
            try {
                printInfo("Dash");

                if (user_input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < list_count + 1; i++) {
                        System.out.println(i + ". " + list[i - 1].toString());
                    }
                } else if (user_input.startsWith("mark") || user_input.startsWith("unmark")) {
                   //to check if current list is not empty
                    if(list_count > 0)
                    {
                        Integer last_digit = 0;
                        if (user_input.startsWith("mark")) {
                            last_digit = Integer.valueOf(user_input.substring(4).trim()) - 1;
                            System.out.println("Nice! I've marked this task as done:");
                            list[last_digit].markAsDone();
                        } else if (user_input.startsWith("unmark")) {
                            last_digit = Integer.valueOf(user_input.substring(6).trim()) - 1;
                            System.out.println("OK, I've marked this task as not done yet:");
                            list[last_digit].markAsNotDone();
                        }
                        System.out.println("[" + list[last_digit].getStatusIcon() + "] " + list[last_digit].getDescription());
                    }
                    else
                    {
                        //print error
                        throw new JosBotException("missing_list");
                    }
                } else if (user_input.startsWith("todo") || user_input.startsWith("deadline") || user_input.startsWith("event")) {
                    //to get the first word
                    String[] fw = user_input.split(" ");
                    if(fw.length > 1)
                    {
                        System.out.println("Got it. I've added this task:");
                        if (user_input.startsWith("todo")) {
                            String todo_txt = user_input.replace("todo ", "");
                            Task t = new Todo(todo_txt);
                            list[list_count] = t;
                            list_count++;
                            System.out.println(t.toString());
                        } else if (user_input.startsWith("deadline")) {
                            String deadline_txt = user_input.replace("deadline ", "");
                            String[] split_txt = deadline_txt.split("/by", 2);
                            deadline_txt = split_txt[0].trim();
                            //task name and deadline day
                            Task t = new Deadline(split_txt[0].trim(), split_txt[1].trim());
                            list[list_count] = t;
                            list_count++;
                            System.out.println(t.toString());
                        } else if (user_input.startsWith("event")) {
                            String event_txt = user_input.replace("event ", "");
                            String[] split_txt = event_txt.split("/", 3);
                            String event_name = split_txt[0].trim();
                            String event_from = split_txt[1].replace("from ", "").trim();
                            String event_to = split_txt[2].replace("to ", "").trim();
                            Task t = new Event(event_name, event_from, event_to);
                            list[list_count] = t;
                            list_count++;
                            System.out.println(t.toString());
                        }

                        System.out.println("Now you have " + list_count + " task in the list.");
                    }
                    else
                    {
                        throw new JosBotException("missing_description");
                    }

                } else {
                    throw new JosBotException("missing_task");
                }
                //end of try
            } catch (JosBotException e)
            {
                String error_message = "";

                switch (e.getMessage()){
                    case "missing_task":
                        error_message = "No Task Found!";
                        break;
                    case "missing_list":
                        error_message = "There is no available task currently! Please try to add a task before marking them";
                        break;
                    case "missing_description":
                        error_message = "The description of the task cannot be empty! Please add a description to your task";
                        break;
                    default:
                        error_message = "unknown error";
                }

                System.out.println("Error : " + error_message);

            }

            printInfo("Dash");
            user_input = in.nextLine().trim();
        }
        printInfo("End");

    }
}
