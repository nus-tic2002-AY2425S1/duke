import java.util.*;
public class Ruan {
   
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        String line = "";
        TaskList taskList = new TaskList();//declare tasklist, call TaskList class
        
        try(Scanner userInput = new Scanner(System.in)){//declare input scanner

            System.out.println("  -----------------------------------");
            System.out.println("  Hello, I'm Ruan\n  What can I do for you?");
            System.out.println("  -----------------------------------");

            while(!line.trim().toLowerCase().equals("bye")){
                line = userInput.nextLine();

                //check for "bye"
                if(line.trim().toLowerCase().equals("bye")){
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }
                
                //handle specific commands
                if (line.toLowerCase().startsWith("mark")) {
                    //action for mark item

                    //explode line command and get task number from last item
                    int taskNo = Integer.parseInt(line.split(" ")[1]) - 1;
                    taskList.markTask(taskNo);
                } else if (line.toLowerCase().startsWith("unmark")) {
                    //action for unmark item

                    //explode line command and get task number from last item
                    int taskNo = Integer.parseInt(line.split(" ")[1]) - 1;
                    taskList.unmarkTask(taskNo);
                }else if (line.trim().toLowerCase().equals("list")) {
                    //handle the "list" command to display tasks
                    taskList.printList();
                }else {
                    //add task to the task list
                    taskList.addTask(line);
                }

            }
        }

    }
}
