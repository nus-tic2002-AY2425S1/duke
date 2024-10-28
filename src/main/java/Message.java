public class Message {
    public static void printHorizontalLines(){
        System.out.println("____________________________________________________________");
    }
    private static void printSingleMessage(String textMessage){
        printHorizontalLines();
        System.out.println(textMessage);
        printHorizontalLines();
    }
    public static void printWelcomeMessage(String systemName) {
        printHorizontalLines();
        System.out.println("Hello! I'm " + systemName);
        System.out.println("What can I do for you?");
        printHorizontalLines();
    }
    public static void printEndingMessage(){
        printSingleMessage("Bye. Hope to see you again soon!");
    }
    public static void printEmptyMessage(boolean isList){
        if(isList){
            printSingleMessage("The list is empty.");
        } else {
            printSingleMessage("The task is empty.");
        }
    }
    public static void printInvalidIntegerMessage(){
        printSingleMessage("Mark item needs to be a valid number.");
    }
    public static void printOutOfBoundsMessage(){
        printSingleMessage("Index is out of bounds.");
    }
    public static void printDuplicateMessage(){
        printSingleMessage("Task exist in list.");
    }
    public static void printTaskStatusMessage(boolean isDone, Task currentTask){
        String message;
        if(isDone){
            message = "Nice! I've marked this task as done";
        } else {
            message = "OK, I've marked this task as not done yet";
        }
        printHorizontalLines();
        System.out.println(message + ":");
        System.out.println("\t[" + currentTask.getStatusIcon() + "] " + currentTask.getTaskName());
        printHorizontalLines();
    }
    public static void printMarkedTaskMessage(){
        printSingleMessage("Task have been marked / unmarked");
    }
    public static void printAddedTaskMessage(int totalNumberOfTodoTask, String addedTask){
        printHorizontalLines();
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
        System.out.println("Now you have " + totalNumberOfTodoTask + " tasks in the list.");
        printHorizontalLines();
    }
    public static void printDeleteTaskMessage(int totalNumberOfTodoTask, String removeTask){
        printHorizontalLines();
        System.out.println("Noted. I've removed this task:");
        System.out.println(removeTask);
        System.out.println("Now you have " + totalNumberOfTodoTask + " tasks in the list.");
        printHorizontalLines();
    }
    public static void printMissingCommandKeywordMessage(){
        printSingleMessage("It looks like you forgot to specify keywords!");
    }
    public static void printMissingParameterMessage(String taskType){
        String format = switch (taskType) {
            case "deadline" -> "[Task_name] /by [Deadline]";
            case "event" -> "[Task_name] /from [Start_date] /to [End_date]";
            default -> "";
        };
        printSingleMessage("It seems you forgot to specify a parameter! Please enter it in the following format: " + format);
    }
}
