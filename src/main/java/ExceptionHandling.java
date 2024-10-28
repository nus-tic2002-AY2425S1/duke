import java.util.ArrayList;

public class ExceptionHandling {
    //Solution below adapted from https://www.quora.com/What-is-the-function-of-a-isInteger-in-Java
    // Exception: Validates whether the input index for marking or unmarking a task is a valid integer
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    // Remove unnecessary whitespace between words and start / end of the string
    public static String removeSpaces(String input){
        return input.trim().replaceAll("\\s+", " ");
    }
    // Exception: Validates if the input contains only specified keywords or if no name has been provided for the task
    public static boolean isEmptyInput(String input) {
        input = removeSpaces(input);
        boolean taskIsEmpty = false;
        if (input.equalsIgnoreCase("todo") || input.equalsIgnoreCase("deadline") || input.equalsIgnoreCase("event") || input.isEmpty()) {
            taskIsEmpty = true;
        } else {
            String[] splitWord = input.split(" ");
            String keyword = splitWord[0];
            switch (keyword) {
                case "todo" -> {
                    if (removeSpaces(splitWord[1]).isEmpty()) {
                        taskIsEmpty = true;
                    }
                }
                case "deadline" -> {
                    input = removeSpaces(input.replace("deadline", ""));
                    if (input.contains("/by")) {
                        String[] deadlineDetails = input.split("/by");
                        // Check if the task is empty
                        if (deadlineDetails.length == 0 || removeSpaces(deadlineDetails[0]).isEmpty()) {
                            taskIsEmpty = true;
                        }
                    }
                }
                case "event" -> {
                    input = removeSpaces(input.replace("event", ""));
                    if (!input.contains("/from") || !input.contains("/to")) {
                        return false;
                    }
                    String[] eventDetails = input.split("/from");
                    if (eventDetails.length == 0 || removeSpaces(eventDetails[0]).isEmpty()) {
                        taskIsEmpty = true;
                    }
                }
            }
        }
        return taskIsEmpty;
    }
    // Exception: Validates if there are duplicate tasks, including task type, already added to the list
    public static boolean isTaskDuplicated(ArrayList<Task> todoTaskList, String newTask) {
        newTask = removeSpaces(newTask);
        for (Task task : todoTaskList) {
            if(task instanceof ToDo){
                newTask = removeSpaces(newTask.replace("todo", ""));
                if (task.getTaskName().equalsIgnoreCase(newTask)){
                    return true;
                }
            } else if (task instanceof Deadline){
                newTask = removeSpaces(newTask.replace("deadline", ""));
                if (newTask.contains("/by")){
                    String[] deadlineDetails = newTask.split("/by");
                    if (deadlineDetails.length == 2 ){
                        String newDeadlineTask = removeSpaces(deadlineDetails[0]);
                        String deadlineOfTask = removeSpaces(deadlineDetails[1]);
                        if (((Deadline) task).by.equalsIgnoreCase(deadlineOfTask) && task.getTaskName().equalsIgnoreCase(newDeadlineTask)) {
                            return true;
                        }
                    }
                }
            } else if (task instanceof Event){
                newTask = removeSpaces(newTask.replace("event", ""));
                if (newTask.contains("/from") && newTask.contains("/to")){
                    String[] eventDetails = newTask.split("/from");
                    if (eventDetails.length == 2){
                        String newEventTask = removeSpaces(eventDetails[0]);
                        String[] taskDurationDetails = eventDetails[1].split("/to");
                        if (taskDurationDetails.length == 2){
                            String from = removeSpaces(taskDurationDetails[0]);
                            String to = removeSpaces(taskDurationDetails[1]);
                            if (((Event) task).from.equalsIgnoreCase(from) && ((Event) task).to.equalsIgnoreCase(to) && task.getTaskName().equalsIgnoreCase(newEventTask)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    // Exception: Validates if the task has already been marked or unmarked in the list
    public static boolean isTaskMarked(ArrayList<Task> todoTaskList, int index, boolean isDone){
        try{
            return todoTaskList.get(index - 1).isDone == isDone;
        } catch (Exception e){
            return false;
        }
    }
    // Exception: Validates the keyword to categorize the task
    public static boolean isCommandKeywordPresent(String keyword){
        // Iterating each Enum value and check against the input keyword
        for (Keywords keywords : Keywords.values()) {
            if (keywords.getKeyword().equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }
    // Exception: Validates if the provided deadline for the task is missing
    public static boolean isValidDeadlineInput(String[] deadlineDetails) {
        return deadlineDetails.length >= 2 && !removeSpaces(deadlineDetails[1]).isEmpty();
    }
    // Exception: Validates if the event period provided for the task is missing
    public static boolean isValidEventInput(String[] eventDetails) {
        if (eventDetails.length < 2) {
            return false;
        }
        String[] eventOfTask = eventDetails[1].split("/to");
        return eventOfTask.length >= 2 && !removeSpaces(eventOfTask[0]).isEmpty() && !removeSpaces(eventOfTask[1]).isEmpty();
    }
}
