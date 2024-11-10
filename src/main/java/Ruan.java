import java.util.*;

public class Ruan {
    
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "";
        TaskList taskList = new TaskList();
        
        try(Scanner userInput = new Scanner(System.in)){

            System.out.println("  -----------------------------------");
            System.out.println("  Hello, I'm Ruan\n  What can I do for you?");
            System.out.println("  -----------------------------------");
            

            while(!line.trim().toLowerCase().equals("bye")){
                line = userInput.nextLine();

                //trim all command to have more accurate readings
                String trimedLine = line.trim().toLowerCase();
                
                try{

                    if(trimedLine.equals("bye")){
                        //print bye message and exit when bye command is input
                        System.out.println("Bye. Hope to see you again soon!");
                        return;
                    }
                    
                    /**
                     * To handle specific commands
                     */
                    if (trimedLine.startsWith("mark")) {

                        //get index of task
                        int taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                        //mark item
                        taskList.markTask(taskIndex);

                    } else if (trimedLine.startsWith("unmark")) {

                        //get index of task
                        int taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                        taskList.unmarkTask(taskIndex);

                    } else if (line.toLowerCase().startsWith("delete")) {

                        //get index of task
                        int taskIndex = Integer.parseInt(line.split(" ")[1]) - 1;
                        taskList.deleteTask(taskIndex);

                    } else if (trimedLine.equals("list")) {

                        //print task list when list command is input
                        taskList.printList();

                    } else if (trimedLine.startsWith("todo")) {
                        
                        //add todo task
                        taskList.addTodo(line.replace("todo ", ""));

                    } else if (trimedLine.startsWith("deadline")) {

                        String[] datas = line.replace("deadline ", "").split(" /by ");

                        if(datas.length < 2){
                            throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);
                        }
                        
                        //add deadline task
                        taskList.addDeadline(datas[0], datas[1]);

                    } else if (trimedLine.startsWith("event")) {

                        String[] datas = line.replace("event ", "").split(" /from | /to ");

                        if(datas.length < 3){
                            throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);
                        }

                        //add event task
                        taskList.addEvent(datas[0], datas[1], datas[2]);

                    }else {

                        throw new RuanException(ErrorType.UNKNOWN_DESCRIPTION);

                    }

                } catch (RuanException e) {

                    String[] lineOut = new String[100];
                    lineOut[0] = e.getMessage();
                    //send string array to taskList class to be printed
                    taskList.print(lineOut);

                }
            }
        }
    }
}
