package josbot.ui;

import josbot.parser.DateTimeParser;
import josbot.task.Task;
import josbot.task.TaskList;
import java.util.Scanner;

public class UI {

    public void showLine(){
        System.out.println("____________________________________________________________");
    }

    public void showGreeting(String type){
        switch(type){
            case "Start":
                showLine();
                DateTimeParser dt = new DateTimeParser();
                System.out.println( "Good "+dt.getTimeGreeting() +", I'm JosBot\nWhat can I do for you?");
                showLine();
                break;
            case "End":
                System.out.println("Bye. Hope to see you again soon!");
                showLine();
                break;
        }
    }

    public void showError(String error_type){
        switch(error_type){
            case "invalid_command":
                System.out.println("Invalid Command : No such command found!");
                break;
            case "invalid_datetime_format":
                System.out.println("Invalid date & time format! Please use date format (dd/MM/yyyy) and if you want to add time,\nuse the time format (24-hour format, eg. 1800 is 6PM)");
                break;
            case "invalid_tag":
                System.out.println("Invalid tag detected! Please use the correct tag format\nfor tag use 'tag task_list_number tag_description')\n for untag use ('untag task_list_numer')");
                break;
            case "missing_description":
                System.out.println("Missing description detected! Please specify the description of the task");
                break;
            case "missing_mark_number":
                System.out.println("Missing mark number detected! Please specify the task number you want to mark");
                break;
            default:
                System.out.println("Unknown Error : "+error_type);
        }
    }

    public void showLoadingError(){
        System.out.println("Error: Failed to load file");
        showLine();
    }

//    public void showInvalidCommandError(String message){
//        System.out.println("Invalid Command : " + message);
//        showLine();
//    }

    public void showFileNotFoundError(){
        System.out.println("Error: File are not found!\n");
        System.out.println("Creating new file..\n");
        showLine();
    }

    public String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine().trim();
    }

    public void showListMessage(){
        System.out.println("Here are the tasks in your list:");
    }

    public void showReminderMessage(){
        System.out.println("Reminder - Here are the lists of task with deadline \nsorted from the oldest at the top that have not been marked as done:\n");
    }

    public void showAddMessage(){
        System.out.println("Got it. I've added this task:");
    }

    public void showTaskMessage(Task t, TaskList list){
        System.out.println(t.toString());
        System.out.println("Now you have " + list.getTaskCount() + " task in the list.");
    }

    public void showMarkMessage(Task t, Boolean marked){
        if(marked){
            System.out.println("Nice! I've marked this task as done:");
        }
        else
        {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(t.toString());
    }

    public void showTagMessage(Task t, Boolean tagged){
        if(tagged){
            System.out.println("OK, I've tagged this task as follow:");
        }
        else
        {
            System.out.println("OK, I've removed the tag from this task:");
        }
        System.out.println(t.toString());
    }

    public void showDeleteMessage(String message, int count){
        if(message.equals("start"))
        {
            System.out.println("Noted. I've removed this task:");
        }
        else
        {
            System.out.println("Now you have " + count + " tasks in the list.");
        }
    }

    public void showMessage(String message){
        System.out.println(message);
    }

    public void showDateTimeError(){
        System.out.println("Invalid date & time format! Please use date format (dd/MM/yyyy) and if you want to add time,\nuse the time format (24-hour format, eg. 1800 is 6PM)");
    }

    public void showInvalidDateTime(){
        System.out.println("Invalid date & time detected! Please make sure that the date or time you've listed is correct");
    }

    public void showIndexOutofBoundError(){
        System.out.println("The number you've selected is not on the list! Please choose the correct number.");
    }

    public void showNumberFormatError(){
        System.out.println("The command format that you've selected is wrong! Please use number/Integer (e.g <Command> <number/integer>)");
    }

    public void showTaskLists(TaskList tasks, boolean showNumber){

        if(tasks.getTaskCount() == 0)
        {
            System.out.println("No task result found!");
        }
        else
        {
            if(showNumber)
            {
                for (int i = 1; i < tasks.getTaskCount() + 1; i++) {
                    System.out.println(i + ". " + tasks.getTasks().get(i - 1).toString());
                }
            }
            else
            {
                for (int i = 1; i < tasks.getTaskCount() + 1; i++) {
                    System.out.println(tasks.getTasks().get(i - 1).toString());
                }
            }
            System.out.println("\nThere are a total of " + tasks.getTaskCount() + " tasks shown in the above list.");
        }

    }

}
