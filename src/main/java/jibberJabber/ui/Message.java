package jibberJabber.ui;

import jibberJabber.tasks.Task;
/**
 * This Message class handles all customised messages, providing methods to handle exceptions and successful command execution
 */
public class Message {
    /**
     * This method returns a string separator that wraps around text upon completion of commands
     */
    public static void printHorizontalLines(){
        System.out.println("____________________________________________________________");
    }
    private static void printSingleMessage(String textMessage){
        printHorizontalLines();
        System.out.println(textMessage);
        printHorizontalLines();
    }
    /**
     * This method will set the intended system name and defaulted welcome message upon running the program
     *
     * @param systemName The system name to be displayed upon running the program
     */
    public static void printWelcomeMessage(String systemName) {
        printHorizontalLines();
        System.out.println("Hello! I'm " + systemName);
        System.out.println("What can I do for you?");
        printHorizontalLines();
    }
    /**
     * This method returns the defaulted ending message upon "bye" keyword command
     */
    public static void printEndingMessage(){
        printSingleMessage("Bye. Hope to see you again soon!");
    }
    /**
     * This method will return the empty messages depending on the isList argument toggle
     *
     * @param isList A boolean to determine if the empty message is meant to detect empty list or empty task name
     */
    public static void printEmptyMessage(boolean isList){
        if(isList) printSingleMessage("The list is empty.");
        else printSingleMessage("The task is empty.");
    }
    /**
     * This method returns the defaulted message for invalid integer
     */
    public static void printInvalidIntegerMessage(){
        printSingleMessage("Mark item needs to be a valid number.");
    }
    /**
     * This method returns the defaulted message for out of bound indices (index <= 0 or index > list size)
     */
    public static void printOutOfBoundsMessage(){
        printSingleMessage("Index is out of bounds.");
    }
    /**
     * This method returns the defaulted message for duplicated tasks created
     */
    public static void printDuplicateMessage(){
        printSingleMessage("Task exist in list.");
    }
    /**
     * This method sets isDone status for each currentTask within the arraylist with the defaulted Task status message
     *
     * @param isDone A boolean to the status of done-ness for the task (Done / Not Done)
     * @param currentTask The current task object being pointed in the array list
     */
    public static void printTaskStatusMessage(boolean isDone, Task currentTask){
        String message;
        if(isDone) message = "Nice! I've marked this task as done";
        else message = "OK, I've marked this task as not done yet";
        printHorizontalLines();
        System.out.println(message + ":");
        System.out.println("\t" + currentTask.printAddedTask());
        printHorizontalLines();
    }
    /**
     * This method returns the defaulted message for task that have already been marked / unmarked
     */
    public static void printMarkedTaskMessage(){
        printSingleMessage("Task have been marked / unmarked");
    }
    /**
     * This method sets the total number of tasks and newly added task in a defaulted message upon completion of Add keyword command
     *
     * @param totalNumberOfTodoTask Integer that reflects the total number of task created
     * @param addedTask The newly added task name
     */
    public static void printAddedTaskMessage(int totalNumberOfTodoTask, String addedTask){
        printHorizontalLines();
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
        System.out.println("Now you have " + totalNumberOfTodoTask + " tasks in the list.");
        printHorizontalLines();
    }
    /**
     * This method sets the total number of tasks and deleted task name in a defaulted message upon completion of delete keyword command
     *
     * @param totalNumberOfTodoTask Integer that reflects the total number of task created
     * @param removeTask The task name that is deleted from the array list
     */
    public static void printDeleteTaskMessage(int totalNumberOfTodoTask, String removeTask){
        printHorizontalLines();
        System.out.println("Noted. I've removed this task:");
        System.out.println(removeTask);
        System.out.println("Now you have " + totalNumberOfTodoTask + " tasks in the list.");
        printHorizontalLines();
    }
    /**
     * This method returns the defaulted message for commands that has missing keywords (todo/event/deadline/list/delete/mark/unmark/find)
     */
    public static void printMissingCommandKeywordMessage(){
        printSingleMessage("It looks like you forgot to specify keywords!");
    }
    /**
     * This method will return a defaulted message if the format of the command is not proper
     *
     * @param taskType A string that is match against keywords which determines the format of the command
     */
    public static void printMissingParameterMessage(String taskType){
        String format = switch (taskType) {
            case "deadline" -> "[Task_name] /by [Deadline]";
            case "event" -> "[Task_name] /from [Start_date] /to [End_date]";
            default -> "";
        };
        printSingleMessage("It seems you forgot to specify a parameter! Please enter it in the following format: " + format);
    }
    /**
     * This method returns the defaulted message when the system is unable to create directory
     */
    public static void printFailedDirectoryCreationMessage(){
        printSingleMessage("Failed to create a directory");
    }
    /**
     * This method returns the defaulted message when the system is unable to create file
     */
    public static void printFailedToCreateFileMessage(){
        printSingleMessage("Failed to create file");
    }
    /**
     * This method returns the defaulted message when the system is unable to append newly added task into existing text file
     */
    public static void printFailedToAppendToFileMessage(){
        printSingleMessage("Failed to append text to file");
    }
    /**
     * This method returns the defaulted message when the date format passed during deadline and event commands are invalid
     */
    public static void printInvalidDateFormatMessage(){
        printSingleMessage("Invalid date format. Date should be in the format of d/M/yyyy");
    }
    /**
     * This method returns the defaulted message when start date is after end date
     */
    public static void printInvalidTimeMessage(){
        printSingleMessage("Invalid date range. Both dates should be present and start date should be before end date");
    }
    /**
     * This method returns the defaulted message when no task is found within the date range given
     */
    public static void printNoTaskFoundWithinPeriodMessage(){
        printSingleMessage("No task found with the period");
    }
    /**
     * This method returns the defaulted message when no task is found with the keyword
     */
    public static void printNoTaskFoundWithKeywordMessage(){
        printSingleMessage("No task found");
    }
}
