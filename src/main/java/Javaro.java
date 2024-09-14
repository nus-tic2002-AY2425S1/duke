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
    public static Task[] addToList(Task[] list, String item) {
        int listLength = list.length;
        Task[] newList = Arrays.copyOf(list, listLength + 1);

        // When adding the task to the list, initialize the task with checkbox as not done
        Task newTask = new Task();
        newTask.setDescription(item);
        newList[listLength] = newTask;

        return newList;
    }

    public static void printAddMessage(String item) {
        printLine();

        String message = "added: " + item;
        printMessage(false, message);
        
        printLine();
    }

    public static void printTasks(Task[] list) {
        for (int i = 0; i < list.length; i++) {
            Task current = list[i];
            // System.out.println("desc " + current);
            String index = Integer.toString(i + 1);

            // list[i] contains the checkbox
            String line = index + ". " + current;
            
            printMessage(false, line);
        }
    }

    public static void printList(Task[] list) {
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
    // input will be the command that the user types (e.g. "mark 1")
    // What if the user mark a task that is already done, or try to unmark a task that is not done
    // What if index entered by user is greater than the number of items in the list
    public static void markDone(Task[] list, String input) {
        String message;

        // To get the task number that the user wants to mark/unmark, cannot simply extract the last character because this would assume that the task number will always be less than 10, i.e. it will not work if there are more than 9 tasks in the list
        // Because command can be "mark" or "unmark", cannot simply use the length of the word "mark" or "unmark" and +2 (+1 for the space after the command and another +1 to get to the task number) to get the value of the task number that the user wants to mark/unmark
        // Instead, the substring function extracts the value after the space, i.e. value of task number that user wants to mark/unmark. Then to get the index (because String array indices start from 0), need to -1
        int indexOfSpace = input.indexOf(' ');
        int indexToMark = Integer.parseInt(input.substring(indexOfSpace + 1)) - 1;

        Task taskToMark = list[indexToMark];
        // TODO: Check if the task number is a valid number and not an alphabet
        // TODO: Handle the case where index (from input) > number of items in the list
        if (indexToMark < 0 || indexToMark > list.length) {
            System.out.println("Item not found");
            return;
        }

        if (input.startsWith(MARK)) {
            // Mark task as done
            message = "Nice! I've marked this task as done:";
            taskToMark.setDone(true);
        } else {
            // Mark task as not done
            message = "OK, I've marked this task as not done yet:";
            taskToMark.setDone(false);
        }

        // Print result on CLI
        printLine();
        printMessage(false, message);
        printMessage(false, "  " + taskToMark);
        printLine();

    }

    // This function echos commands entered by the user, and exits when the user types the command bye.
    public static void echo() {
        Scanner in = new Scanner(System.in);

        // Get the input and convert it to lower case
        String input = in.nextLine().toLowerCase();

        // Assume there will be no more than 100 tasks. Initialize an empty list of String array
        Task[] list = new Task[0];

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
