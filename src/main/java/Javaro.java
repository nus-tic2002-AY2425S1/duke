import java.util.Arrays;
import java.util.Scanner;

public class Javaro {
    
    // Use constant variables to store the keyword commands
    // https://www.javatpoint.com/java-constant
    static final String CHATBOTNAME = "Javaro";
    static final String LINE = "____________________________________________________________";
    static final String BYE="bye";
    static final String LIST="list";
    static final String MARK="mark";
    static final String UNMARK="unmark";

    public static void space(boolean isLine) {
        // https://stackoverflow.com/questions/1073787/print-spaces-with-string-format
        String space; 

        // If space is to come before a horizontal line, use "    "
        if (isLine) {
            // Print 4 spaces
            space = String.format("%" + 4 + "s", ""); 
            System.out.print(space);
        
        // If space is to come before a line of text, use "     "
        } else {
            space = String.format("%" + 5 + "s", ""); 
            System.out.print(space);
        }
    }

    // Print the message
    public static void printMessage(boolean isLine, String message) {
        space(isLine);
        System.out.println(message);
    }

    // This function prints a horizontal line
    public static void printLine() {
        printMessage(true, LINE);
    }

    // Chatbot greets the user
    public static void greet() {
        printLine();
        
        // Print first message: "Hello, I'm Javaro."
        String message;
        message = "Hello, I'm " + CHATBOTNAME + ".";
        printMessage(false, message);
        
        // Print first message: "What can I do for you?"
        message = "What can I do for you?";
        printMessage(false, message);

        printLine();
    }

    // Chatbot exits
    public static void exit() {
        printLine();

        String message = "Bye. Hope to see you again soon!";
        printMessage(false, message);
        
        printLine();
    }

    // 1st element has index 0. Length 1
    public static String[] addToList(String[] list, String item) {
        int listLength = list.length;
        String[] newList = Arrays.copyOf(list, listLength + 1);

        // When adding the task to the list, initialize the task with checkbox as not done
        newList[listLength] = "[ ] " + item;
        return newList;
    }

    public static void printAddMessage(String item) {
        printLine();

        String message = "added: " + item;
        printMessage(false, message);
        
        printLine();
    }

    public static void printTasks(String[] list) {
        for (int i = 0; i < list.length; i++) {
            String index = Integer.toString(i + 1);

            // list[i] contains the checkbox
            String line = index + ". " + list[i];
            
            printMessage(false, line);
        }
    }

    public static void printList(String[] list) {
        printLine();
        
        String message = "Here are the tasks in your list:";
        printMessage(false, message);
        
        System.lineSeparator();

        printTasks(list);

        printLine();
    }

    // TODO: Add validation checks for input, e.g. What if user just press enter without keying any words
    public static boolean checkInput(String input, String command) {
        return input.equals(command);
    }

    // Add the ability to mark tasks as done. Optionally, add the ability to change the status back to not done.
    // list will be the list of tasks that the user has entered
    // input will be the command that the user types
    // What if the user mark a task that is already done, or try to unmark a task that is not done
    // What if index entered by user is greater than the number of items in the list
    public static void markDone(String[] list, String input) {
        String message;
        String task;

        // To get the task number that the user wants to mark/unmark, cannot simply extract the last character because this would assume that the task number will always be less than 10, i.e. it will not work if there are more than 9 tasks in the list
        // Because command can be "mark" or "unmark", cannot simply use the length of the word "mark" or "unmark" and +2 (+1 for the space after the command and another +1 to get to the task number) to get the value of the task number that the user wants to mark/unmark
        // Instead, the substring function extracts the value after the space, i.e. value of task number that user wants to mark/unmark. Then to get the index (because String array indices start from 0), need to -1
        int indexOfSpace = input.indexOf(' ');
        int indexToMark = Integer.parseInt(input.substring(indexOfSpace + 1)) - 1;

        // Get the position (index) of the opening bracket, so that I know where to insert the X
        int indexOpenBracket = list[indexToMark].indexOf("[");

        // TODO: Handle the case where index (from input) > number of items in the list
        if (indexToMark > list.length) {
            System.out.println("Item not found");
            return;
        }

        // Extract the part of the task from the beginning of the task, up to the space after the opening bracket
        String taskFirstPart = list[indexToMark].substring(0, indexOpenBracket + 1);

        // Extract the part of the task from the closing bracket, up to the end of the task
        String taskSecondPart = list[indexToMark].substring(indexOpenBracket + 2, list[indexToMark].length());

        if (input.startsWith(MARK)) {
            // Mark task as done
            message = "Nice! I've marked this task as done:";
            list[indexToMark] = taskFirstPart + 'X' + taskSecondPart;
            
        } else {
            // Mark task as not done
            message = "OK, I've marked this task as not done yet:";
            list[indexToMark] = taskFirstPart + ' ' + taskSecondPart;
        }

        // Overwrite current index with the marked task
        task = list[indexToMark];
        
        // Print result on CLI
        printLine();
        printMessage(false, message);
        printMessage(false, "  " + task);
        printLine();
        
    }

    // This function echos commands entered by the user, and exits when the user types the command bye.
    public static void echo() {
        Scanner in = new Scanner(System.in);

        // Get the input and convert it to lower case
        String input = in.nextLine().toLowerCase();

        // Assume there will be no more than 100 tasks. Initialize an empty list of String array
        String[] list = new String[0];

        // Continue looping until input is "bye"
        while (checkInput(input, BYE) == false) {
            
            if (checkInput(input, LIST)) {
                printList(list);
            } else if (input.startsWith(MARK) || input.startsWith(UNMARK)) {        // Check if input contains the command "mark" or "unmark"
                markDone(list, input);
            } else {
                list = addToList(list, input);
                printAddMessage(input);
            }

            System.lineSeparator();

            // Ask the user for the next input
            input = in.nextLine().toLowerCase();
        }
    }

    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }
}
