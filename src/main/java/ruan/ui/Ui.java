package ruan.ui;

import java.util.Scanner;
import ruan.exception.ErrorType;
import ruan.exception.RuanException;
import ruan.task.Task;

/**
 * Represents a UI class responsible for interacting with the user/displaying of message
 * Handles all input and output display operations
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructs a Ui instance with a Scanner for reading user input
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the logo of the program/application
     */
    public void printLogo() {
        String logo = "   ____        _        \n"
            + "  |  _ \\ _   _| | _____ \n"
            + "  | | | | | | | |/ / _ \\\n"
            + "  | |_| | |_| |   <  __/\n"
            + "  |____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
    }

    /**
     * Prints the welcome message when the program starts
     */
    public void printWelcome() {
        printLogo();
        String[] message = {
            "  Hello, I'm Ruan",
            "  What can I do for you?"
        };
        printMessage(message);
    }

    /**
     * Reads the user input command
     * @return User command as a string
     */
    public String readCommand() {
        return scanner.nextLine();
    }
    
    /**
     * Prints an error message when loading tasks fails
     * @throws RuanException Throws a RuanException with an appropriate error type
     */
    public void printLoadingError() throws RuanException {
        throw new RuanException(ErrorType.FAIL_TO_LOAD_FILE);
    }

    /**
     * Prints an error message.
     * @param message The error message to be printed
     */
    public void printError(String message) {
        String[] errorMessage = {message};
        printMessage(errorMessage);
    }

    /**
     * Prints the given message to the user
     * @param message Array of strings representing the message to print
     */
    public void printMessage(String[] message) {
        //to print the header line
        showLine();
        for(String line: message){
            if(line != null){
                //print each line string in a new line
                System.out.println("  "+line);
            }
        }
        //to print the footer line
        showLine();
    }   

    /**
     * Prints a message indicating that a task has been added successfully
     * @param task Task that has been added
     * @param size Current size of the task list after adding the task
     */
    public void printTaskAdded(Task task, int size) {
        String[] message = {
            "Got it. I've added this task:",
            "  " + task.toString(),
            "Now you have " + size + " tasks in the list."
        };
        printMessage(message);
    }

    /**
     * Prints a horizontal line to wrap output sections
     */
    public void showLine() {
        System.out.println("  -----------------------------------");
    }


}

