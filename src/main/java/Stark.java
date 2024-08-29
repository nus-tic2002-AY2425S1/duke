import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stark {

    private static List<String> tasks = new ArrayList<String>();

    public static void linBreak(){
        System.out.println("------------------------------------------------------");
    }

    public static void welcomeMsg(){
        linBreak();
        System.out.println("Hello! I'm Stark \n" +
                           "What can I do for you?");
        linBreak();
    }

    public static void byeMsg(){
        linBreak();
        System.out.println("Bye. Hope to see you again soon!");
        linBreak();
    }

    public static void printTasks(){
        int counter = 0;
        linBreak();
        for(String task : tasks){
            counter++;
            System.out.println(counter + ". " +task);
        }
        linBreak();
    }

    public static void addTask(String query){
        tasks.add(query);
        linBreak();
        System.out.println("added: " + query);
        linBreak();
    }

    public static void main(String[] args) {
        welcomeMsg();
        Scanner inputQuery = new Scanner(System.in);
        String query = inputQuery.nextLine();
        while(true){
            if(query.equalsIgnoreCase("bye")){
                byeMsg();
                break;
            }
            if(query.equalsIgnoreCase("list")){
                printTasks();
                query = inputQuery.nextLine();
                continue;
            }

            addTask(query);
            query = inputQuery.nextLine();
        }
    }

}
