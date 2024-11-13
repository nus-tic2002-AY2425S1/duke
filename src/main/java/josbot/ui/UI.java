package josbot.ui;

import josbot.task.Task;
import josbot.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    public void showGreeting(String type){
        switch(type){
            case "Dash":
                System.out.print("____________________________________________________________\n");
                break;
            case "Start":
                showGreeting("Dash");
                System.out.println("Hello I'm JosBot\nWhat can I do for you?");
                showGreeting("Dash");
                break;
            case "End":
                System.out.println("Bye. Hope to see you again soon!");
                System.out.print("____________________________________________________________");
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
            default:
                System.out.println("Unknown Error : "+error_type);
        }
    }

    public void showLoadingError(){
        System.out.println("Error: Failed to load file");
        showGreeting("Dash");
    }

    public void showInvalidCommandError(String message){
        System.out.println("Invalid Command : " + message);
        showGreeting("Dash");
    }

    public void showFileNotFoundError(){
        System.out.println("Error: File are not found!\n");
        showGreeting("Dash");
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
        if(marked == true){
            System.out.println("Nice! I've marked this task as done:");
        }
        else
        {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(t.toString());
    }

    public void showTagMessage(Task t, Boolean tagged){
        if(tagged == true){
            System.out.println("OK, I've tagged this task as follow:");
        }
        else
        {
            System.out.println("OK, I've removed the tag from this task:");
        }
        System.out.println(t.toString());
    }

    public void showDeleteMessage(String message, int count){
        System.out.println("Noted. I've removed this task:");
        System.out.println(message);
        System.out.println("Now you have " + count + " tasks in the list.");
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
