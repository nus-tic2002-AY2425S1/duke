import java.util.ArrayList;

public class keywordHandling {
    public static void processListKeyword(ArrayList<task> todoArray, int totalNumberOfTaskObj){
        if (totalNumberOfTaskObj== 0){
            message.printEmptyMessage(true);
        } else {
            task.printTaskList(todoArray);
        }
    }
    public static void processMarkKeyword(ArrayList<task>  todoArray, int totalNumberOfTaskObj, String index, boolean isMark){

        if (totalNumberOfTaskObj == 0){
            message.printEmptyMessage(true);
        }
        // Exception checks:
        // 1. Check if the second input value is a numerical string and 0 (invalid scenario)
        // 2. Check if the input numerical string is out of bound from the task list
        // 3. Check if the task have been marked / unmarked before
        else if (!exceptionHandling.isInteger(index)) {
            message.printInvalidIntegerMessage();
        } else {
            int convertedIndex = Integer.parseInt(index);
            if (convertedIndex > totalNumberOfTaskObj || convertedIndex <= 0) {
                message.printOutOfBoundsMessage();
            } else if (exceptionHandling.isTaskMarked(todoArray, convertedIndex, isMark)){
                message.printMarkedTaskMessage();
            } else {
                task.setTaskMarkStatus(todoArray, convertedIndex, isMark);
            }
        }
    }
}
