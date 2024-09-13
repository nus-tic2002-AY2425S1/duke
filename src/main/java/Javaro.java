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
        // String space = String.format("%4s", "");
        // String space = "    ";

        // If space is to come before a horizontal line, use "    "
        if (isLine) {
            // numberOfSpaces += 4;
            // System.out.println("number of spaces " + numberOfSpaces);
            // space = String.format("%" + numberOfSpaces + "s", "");

            // Print 4 spaces
            space = String.format("%" + 4 + "s", ""); 
            System.out.print(space);
        
        // If space is to come before a line of text, use "     "
        } else {
            space = String.format("%" + 5 + "s", ""); 
            System.out.print(space);
        }
    }

    public static void printMessage(boolean isLine, String message) {
        space(isLine);
        System.out.println(message);
    }

    // This function prints a horizontal line
    public static void printLine() {
        // String line = "____________________________________________________________";
        printMessage(true, LINE);
        // space(true);
        // System.out.println(line);
    }

    // Chatbot greets the user
    public static void greet() {
        // printLine();
        printMessage(true, LINE);
        
        String message;
        message = "Hello, I'm " + CHATBOTNAME + ".";
        // space(false);
        // System.out.println("Hello, I'm " + chatbotName + ".");
        printMessage(false, message);
        
        // space(false);
        // System.out.println("What can I do for you?");
        message = "What can I do for you?";
        printMessage(false, message);

        printLine();
    }

    // Chatbot exits
    public static void exit() {
        printLine();
        
        // space(false);
        // System.out.println("Bye. Hope to see you again soon!");

        String message = "Bye. Hope to see you again soon!";
        printMessage(false, message);
        
        printLine();
    }

    // int length = list.length;
    // String[] result = new String[length + 1];
    // for (int i = 0; i < length; i++) {
    //     result[i] = list[i];
    // }
    // result[length] = email;
    // return result;


    // double[] newOverseas = Arrays.copyOf(overseas, overseas.length + 1);
    // // System.out.println("this is new overseas ");
    // // System.out.println(Arrays.toString(newOverseas));
    
    // newOverseas[overseas.length] = expense;
    // // System.out.println("this is new overseas after adding expense ");
    // // System.out.println(Arrays.toString(newOverseas));
    
    // return newOverseas;


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
        
        // space(false);
        // System.out.println("added: " + item);
        String message = "added: " + item;
        printMessage(false, message);
        
        printLine();
    }

    public static void printTasks(String[] list) {
        for (int i = 0; i < list.length; i++) {
            String index = Integer.toString(i + 1);

            // list[i] should contain the checkbox
            // String line = index + ". [" + "] " + list[i];
            // list[i] = index + ". [" + "] " + list[i];
            // String line = list[i];
            String line = index + ". " + list[i];

            // space(false);
            // System.out.println(line);
            
            printMessage(false, line);
        }
    }

    public static void printList(String[] list) {
        // System.out.println(Arrays.toString(list));

        printLine();
        
        // space(false);
        // System.out.println("Here are the tasks in your list:");
        
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

    // input will be the command that the user types
    // What if index entered by user is greater than the number of items in the list
    public static String[] markDone(String[] list, String input, boolean isDone) {
        String message;
        String checkbox;
        String task;

        // Because command can be "mark" or "unmark", 
        int indexOfSpace = input.indexOf(' ');
        // System.out.println(indexOfSpace);
        // String indexToMark = input.substring(indexOfSpace + 1);
        int indexToMark = Integer.parseInt(input.substring(indexOfSpace + 1));
        // System.out.println("markLength: " + MARK.length());
        // System.out.println("unmarkLength: " + UNMARK.length());
        // System.out.println("indexToMark: " + indexToMark);
        // System.out.println("list index to mark: " + list[indexToMark - 1]);

        int indexOpenBracket = list[indexToMark - 1].indexOf("[");
        // System.out.println("indexOpenBracket " + indexOpenBracket);

        // TODO: Handle the case where index (from input) > number of items in the list
        if (indexToMark > list.length) {
            System.out.println("Item not found");
            return list;
        }

        String taskFirstPart = list[indexToMark - 1].substring(0, indexOpenBracket + 1);
        String taskSecondPart = list[indexToMark - 1].substring(indexOpenBracket + 2, list[indexToMark - 1].length());
        if (input.startsWith(MARK)) {
            // indexToMark = Integer.parseInt(input.substring(MARK.length() + 1));
            // indexToMark = indexOfSpace + 1;
            
            message = "Nice! I've marked this task as done:";
            // checkbox = "[X]"
            // System.out.println("asdf " + input);
            // list[indexToMark - 1] = Integer.toString(indexToMark + 1) + ". [X] ";
            // System.out.println("first part " + list[indexToMark - 1].substring(0, indexOpenBracket + 1));
            list[indexToMark - 1] = taskFirstPart + 'X' + taskSecondPart;
            // System.out.println("substring " + list[indexToMark - 1]);
            
            // message = "[X] " + list[indexToMark - 1];
            
        } else {
            message = "OK, I've marked this task as not done yet:";
            // checkbox = "[ ]";
            list[indexToMark - 1] = taskFirstPart + ' ' + taskSecondPart;
        }

        // task = checkbox + " " + list[indexToMark - 1];
        task = list[indexToMark - 1];
        
        printLine();
        // System.out.println("message " + message);
        printMessage(false, message);
        printMessage(false, "  " + task);
        printLine();
        
        // Update list to mark/unmark item
        // System.out.println("indexToMark: " + indexToMark);
        // System.out.println("String indexToMark: " + String.valueOf(indexToMark) + ". " + task);
        // System.out.println("item " + list[indexToMark - 1]);
        list[indexToMark - 1] = task;
        // System.out.println("TASK: " + task);

        return list;
    }

    /* 
    // Returns the new list of items
    // list - Original list
    // input - item to mark done
    // Add the ability to mark tasks as done. Optionally, add the ability to change the status back to not done.
    // what if you remark a task that is already done, or try to mark an undone task, maybe try ask chatgpt to think of corner cases
    public static void markDone(String[] list, String input, boolean isDone) {
        String message = "";
        // String[] markedList = new String[list.length];
        
        // I cannot simply just extract the last character, because what if the index is 2 digit, i.e. there are more than 9 tasks in the list
        // System.out.println("INPUT " + input + " TYPE " + input.getClass().getName());
        
        // System.out.println("substring " + input.substring(MARK.length() + 1));
        // String index = input.substring(MARK.length() + 1);

        // Extract out the task number that the user wants to "mark"
        // + 1 to factor in the space after "mark" command
        int indexToMark = Integer.parseInt(input.substring(MARK.length() + 1));
        // System.out.println("index " + index);
        // String[] inputParts = input.split(" ");
        // // System.out.println("this is inputparts " + inputParts[0] + " and " + inputParts[1]);
        // char indexChar = inputParts.charAt(1);
        
        // System.out.println("index to mark is " + input.charAt(input.length() - 1));
        
        // String item = list[indexToMark];
        // System.out.println("this is item " + item);

        // System.out.println("line at " + String.valueOf(indexToMark) + " is " + list[indexToMark - 1]);
        String line = list[indexToMark - 1];

        // for (int i = 0; i < list.length; i++) {
        //     // System.out.println("this is list[" + i + "]: " + list[i]);
        //     String task = list[i];
        //     String indexString = Integer.toString(i + 1);
        //     String line = "";
        //     if (indexToMark == i) {
        //         line = indexToMark + ". [X] " + list[i];
        //         isDone = true;
        //     } else {
        //         line = indexString + ". [ ] " + list[i];
        //         isDone = false;
        //     }
        //     // System.out.println("this is line " + line);
        //     markedList[i] = line;
        // }

        // System.out.println("printing marked list");
        // System.out.println(Arrays.toString(markedList));


        // for (int i = 0; i < list.length; i++) {
        //     String index = Integer.toString(i + 1);
        //     String line = index + ". [" + "] " + list[i];
        //     space(false);
        //     System.out.println(line);
        // }

        String finalLine = "  ";
        if (isDone) {
            message = "Nice! I've marked this task as done:";
            finalLine += "[X] " + line; 
        } else {
            message = "OK, I've marked this task as not done yet:";
            finalLine += "[ ] " + line; 
        }
        
        printLine();
        space(false);
        System.out.println(message);
        space(false);
        System.out.println(finalLine);
        printLine();

        // return list;
    }
    */


    // This function echos commands entered by the user, and exits when the user types the command bye.
    public static void echo() {
        Scanner in = new Scanner(System.in);

        // Get the input and convert it to lower case
        String input = in.nextLine().toLowerCase();

        // Assume there will be no more than 100 tasks.
        String[] list = new String[0];

        while (checkInput(input, BYE) == false) {         // while (input.equals("bye") == false) {
            
            if (checkInput(input, LIST)) {
            // if (input.equals("list")) {
                printList(list);

            } else if (input.startsWith(MARK)) {        // startsWith checks if one string has the other as a substring at the beginning e.g., Apple and App
                list = markDone(list, input, true);
            } else if (input.startsWith(UNMARK)) {
                list = markDone(list, input, false);
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
