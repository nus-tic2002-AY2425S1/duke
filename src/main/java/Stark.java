import java.util.Scanner;

public class Stark {


    public static void lineBreak(){
        System.out.println("------------------------------------------------------");
    }

    public static void welcomeMsg(){
        lineBreak();
        System.out.println("Hello! I'm Stark \n" +
                           "What can I do for you?");
        lineBreak();
    }

    public static void byeMsg(){
        lineBreak();
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
    }


    public static void main(String[] args) {
        welcomeMsg();

        TaskList taskList = new TaskList();
        Scanner inputQuery = new Scanner(System.in);
        String[] query = inputQuery.nextLine().split(" ");

        while(!query[0].equalsIgnoreCase("bye")){

            lineBreak();

            if(query[0].equalsIgnoreCase("list")){
                taskList.printAllTasks();
                lineBreak();
                query = inputQuery.nextLine().split(" ");
                continue;
            }
            if(query[0].equalsIgnoreCase("mark") || query[0].equalsIgnoreCase("unmark")){

                if(query.length == 2){
                    taskList.statusChange(query[0], Integer.parseInt(query[1]));
                }
                else{
                    System.out.println("Please enter a valid mark or unmark task number");
                }
                lineBreak();
                query = inputQuery.nextLine().split(" ");
                continue;
            }

            taskList.addTask(query[0],String.join(" ", query));
            lineBreak();
            query = inputQuery.nextLine().split(" ");

        }
        byeMsg();

    }

}
