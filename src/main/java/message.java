public class message {
    public static void printHorizontalLines(){
        System.out.println("____________________________________________________________");
    }
    public static void printSingleMessage(String textMessage){
        printHorizontalLines();
        System.out.println(textMessage);
        printHorizontalLines();
    }
    // (Wk 3) Level-0 Print welcome message
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
    public static void printTaskStatusMessage(boolean isDone, task currentTask){
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
}
