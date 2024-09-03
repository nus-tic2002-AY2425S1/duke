import java.util.*;
public class Ruan {

    public static void printList(String[] taskList){
        int taskNo = 1;
        for (String task : taskList) {
            if(task == null){
                return;//exit this function and continue
            }

            //print only when there is value
            System.out.println("  "+taskNo+". "+task);
        }
    }
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        String line = "";
        String[] taskList = new String[100];
        int listCount = 0;
        try(Scanner userInput = new Scanner(System.in)){

            System.out.println("  -----------------------------------");
            System.out.println("  Hello, I'm Ruan\n  What can I do for you?");
            System.out.println("  -----------------------------------");

            while(!line.trim().toLowerCase().equals("bye")){
                line = userInput.nextLine();
                String lineOut = line;

                //check for "bye"
                if(line.trim().toLowerCase().equals("bye")){
                    lineOut = "Bye. Hope to see you again soon!";
                }
                
                //add to list when not "bye" & "list"
                if(!line.trim().toLowerCase().equals("bye") && !line.trim().toLowerCase().equals("list")){
                    taskList[listCount] = lineOut;
                    lineOut = "added: "+lineOut;
                    listCount++;
                }

                //print results
                System.out.println("  -----------------------------------");
                if(line.trim().toLowerCase().equals("list")){
                    printList(taskList);
                }else{
                    System.out.println("  "+lineOut);
                }
                System.out.println("  -----------------------------------");
                
            }
        }

    }
}
