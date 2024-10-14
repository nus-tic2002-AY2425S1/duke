import java.util.ArrayList;
import java.util.Scanner;

public class jibberJabber {
    public static void addNewTask(ArrayList<task> todoArray, String todoTask){
        if (exceptionHandling.isTaskDuplicated(todoArray, todoTask)){
            message.printDuplicateMessage();
        } else {
            task inputTask =  new task(todoTask);
            todoArray.add(inputTask) ;
            task.printAddedTask(inputTask);
        }
    }
    public static void main(String[] args) {
        // Welcome message
        message.printWelcomeMessage("Jibber Jabber");

        ArrayList<task> todoArray = new ArrayList<>();

        // (Wk 3) Level-1 capture task to complete from user input
        Scanner scanner = new Scanner(System.in);
        String todoTask;

        while(true){
            int totalNumberOfTaskObj =  task.getTotalNumberOfTodoTask();
            todoTask = scanner.nextLine();
            // Check for empty input string
            if(exceptionHandling.isEmptyInput(todoTask)){
                message.printEmptyMessage(false);
                continue;
            }

            String[] splitTodoTask = todoTask.split(" ");
            String splitWord = splitTodoTask[0].toLowerCase();

            switch (splitWord){
                case "bye":
                    message.printEndingMessage();
                    return;
                // (Wk 3) Level-2 Check for "list" key word, then print task list
                case "list":
                    keywordHandling.processListKeyword(todoArray,totalNumberOfTaskObj);
                    continue;
                case "mark":
                    keywordHandling.processMarkKeyword(todoArray, totalNumberOfTaskObj, splitTodoTask[1], true);
                    continue;
                case "unmark":
                    keywordHandling.processMarkKeyword(todoArray, totalNumberOfTaskObj, splitTodoTask[1], false);
                    continue;
            }

            addNewTask(todoArray, todoTask);
        }

    }
}
