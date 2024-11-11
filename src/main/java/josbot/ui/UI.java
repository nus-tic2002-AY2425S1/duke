package josbot.ui;

import josbot.task.Task;
import josbot.task.TaskList;

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
                showGreeting("Dash");
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
        }
    }

    public void showError(String error_type){
        switch(error_type){
            case "invalid_command":
                System.out.println("Invalid Command : No such command found!");
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

    public void showDeleteMessage(String message, int count){
        System.out.println("Noted. I've removed this task:");
        System.out.println(message);
        System.out.println("Now you have " + count + " tasks in the list.");
    }


}
