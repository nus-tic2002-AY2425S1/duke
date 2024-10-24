import java.util.ArrayList;
import java.util.Scanner;

public class JibberJabber {
    public static void main(String[] args) {
        Message.printWelcomeMessage("Jibber Jabber");
        ArrayList<Task> todoTaskList = new ArrayList<>();
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
            KeywordHandling keywordHandling = new KeywordHandling();
            // Check if input value has task command - todos / events / deadlines
            if (ExceptionHandling.isCommandKeywordPresent(splitWord)){
                switch (splitWord){
                    case "bye":
                        Message.printEndingMessage();
                        return;
                    case "list":
                        keywordHandling.processListKeyword(todoTaskList,totalNumberOfTaskObj);
                        continue;
                    case "mark":
                        keywordHandling.processMarkKeyword(todoTaskList, totalNumberOfTaskObj,  splitTodoTask[1], true);
                        continue;
                    case "unmark":
                        keywordHandling.processMarkKeyword(todoTaskList, totalNumberOfTaskObj,  splitTodoTask[1], false);
                        continue;
                    case "delete":
                        keywordHandling.processRemoveKeyword(todoTaskList, totalNumberOfTaskObj, splitTodoTask[1]);
                        continue;
                    default:
                        if (ExceptionHandling.isTaskDuplicated(todoTaskList ,todoTask)) {
                            // Checks for duplicated tasks being added
                            Message.printDuplicateMessage();
                        } else {
                            Task.addTask(todoTaskList, todoTask, splitWord);
                        }
                }
            } else {
                Message.printMissingCommandKeywordMessage();
            }
        }
    }
}
