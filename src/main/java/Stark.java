import Storage.SaveToFile;
import TaskManager.TaskList;
import UserUI.StarkException;
import UserUI.Tokenize;

import java.io.IOException;
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

                String[] queryTokens = Tokenize.tokenize(query); // for checking the input are valid

                if (queryTokens[0].equalsIgnoreCase("bye")) {
                    break;
                } else if (queryTokens[0].equalsIgnoreCase("list")) {
                    taskList.printAllTasks();
                } else if (queryTokens[0].equalsIgnoreCase("mark")
                        || queryTokens[0].equalsIgnoreCase("unmark")
                        || queryTokens[0].equalsIgnoreCase("delete")) {
                    taskList.statusChange(queryTokens[0], Integer.parseInt(queryTokens[1]));
                }else {
                    taskList.addTask(queryTokens[0], String.join(" ", query));
                }
            } catch (StarkException.InvalidDescriptionException |
                     StarkException.InvalidTaskException |
                     StarkException.InvalidCommandException |
                     StarkException.InvalidIndexException e){

                System.out.println(e.getMessage());
            }
            lineBreak();
        }
        byeMsg();
        try{
            new SaveToFile(taskList.getTasks(),"src/Data/TaskLists.txt").writeToFile();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

}
