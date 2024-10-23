import java.util.ArrayList;
import java.util.Scanner;

public class JibberJabber {
    public static void main(String[] args) {
        // Welcome message
        Message.printWelcomeMessage("Jibber Jabber");

        ArrayList<Task> todoTaskList = new ArrayList<>();

        // (Wk 3) Level-1 capture task to complete from user input
        Scanner scanner = new Scanner(System.in);
        String todoTask;

        while(true){
            int totalNumberOfTaskObj =  Task.getTotalNumberOfTodoTask();
            todoTask = scanner.nextLine().trim();
            // Check for empty input string and bypass it
            if(ExceptionHandling.isEmptyInput(todoTask)){
                Message.printEmptyMessage(false);
                continue;
            }

            String[] splitTodoTask = todoTask.split(" ");
            String splitWord = splitTodoTask[0].toLowerCase();

            switch (splitWord){
                case "bye":
                    Message.printEndingMessage();
                    return;
                // (Wk 3) Level-2 Check for "list" key word, then print task list
                case "list":
                    KeywordHandling.processListKeyword(todoTaskList,totalNumberOfTaskObj);
                    continue;
                // (Wk 4) Level-3 Mark / Unmark tasks
                case "mark":
                    KeywordHandling.processMarkKeyword(todoTaskList, totalNumberOfTaskObj,  splitTodoTask[1], true);
                    continue;
                case "unmark":
                    KeywordHandling.processMarkKeyword(todoTaskList, totalNumberOfTaskObj,  splitTodoTask[1], false);
                    continue;
                default:
                    // Check if input value has task command - todos / events / deadlines
                    if (ExceptionHandling.isCommandKeywordPresent(splitWord)) {
                        if (ExceptionHandling.isTaskDuplicated(todoTaskList ,todoTask)) {
                            // Checks for duplicated tasks being added
                            Message.printDuplicateMessage();
                        } else {
                            Task.addTask(todoTaskList, todoTask, splitWord);
                        }
                    } else {
                        Message.printMissingCommandKeywordMessage();
                    }
            }
        }
    }
}
