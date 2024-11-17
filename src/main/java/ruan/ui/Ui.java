package ruan.ui;

import java.util.Scanner;
import ruan.exception.ErrorType;
import ruan.exception.RuanException;
import ruan.task.Task;

/**
 * Class for all UI printing/display
 */
public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void printLogo() {
        String logo = "   ____        _        \n"
            + "  |  _ \\ _   _| | _____ \n"
            + "  | | | | | | | |/ / _ \\\n"
            + "  | |_| | |_| |   <  __/\n"
            + "  |____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
    }

    public void printWelcome() {
        printLogo();
        
        String[] message = {
            "  Hello, I'm Ruan",
            "  What can I do for you?"
        };
        printMessage(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }
    
    //print exception when failed to load task
    public void printLoadingError() throws RuanException {
        throw new RuanException(ErrorType.FAIL_TO_LOAD_FILE);
    }

    //print errors message
    public void printError(String message) {
        String[] errorMessage = {message};
        printMessage(errorMessage);
    }

    public void printMessage(String[] message) {
        //to print the header line
        showLine();

        for(String line: message){
            if(line == null){
                //exit this loop when line is null
                break;
            }
            //print each line string in a new line
            System.out.println("  "+line);
        }

        //to print the footer line
        showLine();
    }   

    public void printTaskAdded(Task task, int size) {
        String[] message = {
            "Got it. I've added this task:",
            "  " + task.toString(),
            "Now you have " + size + " tasks in the list."
        };
        printMessage(message);
    }

    public void showLine() {
        System.out.println("  -----------------------------------");
    }


}

