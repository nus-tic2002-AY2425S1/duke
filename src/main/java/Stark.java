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
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
    }


    public static void main(String[] args) {
        welcomeMsg();

        TaskList taskList = new TaskList();
        Scanner inputQuery = new Scanner(System.in);


        while(true){
            String query = inputQuery.nextLine().trim();
            lineBreak();
            try {
                Tokenize.tokenize(query);
                String[] queryTokens = query.split(" ");
                if (queryTokens[0].equalsIgnoreCase("bye")) {
                    break;
                } else if (queryTokens[0].equalsIgnoreCase("list")) {
                    taskList.printAllTasks();
                } else if (queryTokens[0].equalsIgnoreCase("mark") || queryTokens[0].equalsIgnoreCase("unmark")) {
                    taskList.statusChange(queryTokens[0], Integer.parseInt(queryTokens[1]));
                } else {
                    taskList.addTask(queryTokens[0], String.join(" ", query));
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            lineBreak();
        }
        byeMsg();

    }

}
