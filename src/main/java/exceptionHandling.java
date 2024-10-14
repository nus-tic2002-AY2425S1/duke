import java.util.ArrayList;

public class exceptionHandling {
    //Solution below adapted from https://www.quora.com/What-is-the-function-of-a-isInteger-in-Java
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isEmptyInput(String input) {
        return input.trim().isEmpty();
    }
    public static boolean isTaskDuplicated(ArrayList<task> todoArray, String newTask) {
        for (task task : todoArray) {
            if (task != null && task.getTaskName().equals(newTask)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isTaskMarked(ArrayList<task> todoArray, int index, boolean isDone){
        try{
            index --;
            return todoArray.get(index).isDone == isDone;
        } catch (Exception e){
            return false;
        }
    }
}
