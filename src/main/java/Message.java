public class Message {
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
    public static void printMissingCommandKeywordMessage(){
        printSingleMessage("It looks like you forgot to specify the task type! Please indicate whether this task is a Todo, Event, or Deadline task so we can help you keep track of it.");
    }
    public static void printMissingDeadlineParameterMessage(){
        printSingleMessage("It seems you forgot to specify a parameter! Please enter it in the following format: [Task_name] /by [Deadline].");
    }
    public static void printMissingEventParameterMessage(){
        printSingleMessage("It seems you forgot to specify a parameter! Please enter it in the following format: [Task_name] /from [Start_date] /to [End_date].");
    }
}
