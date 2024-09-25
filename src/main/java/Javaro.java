import java.util.Arrays;
import java.util.Scanner;

public class Javaro {
    
    // Use constant variables to store the keyword commands
    // https://www.javatpoint.com/java-constant
    static final String CHATBOT_NAME = "Javaro";
    static final String LINE = "____________________________________________________________";

    // Commands used in chatbot
    static final String BYE="bye";
    static final String LIST="list";
    static final String MARK="mark";
    static final String UNMARK="unmark";
    static final String TODO="todo";
    static final String DEADLINE="deadline";
    static final String EVENT="event";
    
    // Solution below adapted from https://stackoverflow.com/questions/1073787/print-spaces-with-string-format
    public static String space(int numberOfSpace) {
        String space = String.format("%" + numberOfSpace + "s", ""); 
        return space;
    }

    public static void space(boolean isLine, boolean isTask) {
        String space = ""; 

        // If space is to come before a horizontal line, use "    "
        if (isLine == true && isTask == false) {
            // System.out.println("istask is true");
            space = space(4);
        } else if (isLine == false && isTask == true) {        // If space is to come before a line of text, use "     "
            // For printing task
            space = space(7);
        } else if (isLine == false && isTask == false) {
            space = space(5);
        } 

        System.out.print(space);
    }

    // Print the message
    public static void printMessage(boolean isLine, String message) {
        space(isLine, false);
        System.out.println(message);
    }

    // This function prints a horizontal line
    public static void printLine() {
        space(true, false);
        System.out.println(LINE);
    }

    // Chatbot greets the user
    public static void greet() {
        printLine();
        
        // Print first message: "Hello, I'm Javaro."
        String message;
        message = "Hello, I'm " + CHATBOT_NAME + ".";
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
    public static Task[] addToList(Task[] list, Task task) {
        int listLength = list.length;
        Task[] newList = Arrays.copyOf(list, listLength + 1);

        newList[listLength] = task;

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
    public static boolean checkEquals(String str1, String str2) {
        // Check if two strings are similar irrespective of the differences in case
        return str1.equalsIgnoreCase(str2);
    }

    // Check if user input starts with a specified command
    public static boolean checkInputStartsWith(String input, String command) {
        return input.startsWith(command);
    }

    // Add the ability to mark tasks as done. Optionally, add the ability to change the status back to not done.
    // list will be the list of tasks that the user has entered
    // input will be the command that the user types (e.g. "mark 1")
    // TODO: What if the user mark a task that is already done, or try to unmark a task that is not done
    // TODO: What if index entered by user is greater than the number of items in the list
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

    public static void parseTaskInfo(String input, String info) {
        boolean isValid = input.contains(info);
        // TODO: Replace if-else with try-catch block
        if (isValid) {
            int infoLength = info.length();
            int indexOfInfo = input.indexOf(info) + infoLength + 1;
        } else {
            System.out.println("Invalid input");
        }
    }

    public static int indexOfKeyword(String input, String keyword) {
        return input.indexOf(keyword) + keyword.length() + 1;
    }

    public static String extractDescription(String input) {
        // return input.substring(command.length() + 1, indexOfBy - byLength - 1);
        
        int commandLength = input.trim().split(" ")[0].length();
        // System.out.println("commandLength is " + commandLength);
        int startIndex = commandLength + 1;
        
        String description;
        int endIndex;
        // String description = input.substring(commandLength + 1, input.indexOf('/'));
        
        // Check if input contains '/'
        if (input.contains("/")) {
            // set start index to after command length and end index to before '/'
            endIndex = input.indexOf('/') - 1;
            // description = input.substring(startIndex, input.indexOf('/') - 1);
        } else {
            // if cmd is todo, set endindex as input.length
            endIndex = input.length();
            // description = input.substring(startIndex, input.length());
        }

        description = input.substring(startIndex, endIndex);
        // description = description.trim();

        // System.out.println("desc " + description);
        return description;
    }

    // Does not apply to Todo tasks. 
    // For Deadline tasks, extracts the date/time from input ("after '/by'"), using substring from after '/by' to the end of input. 
    // For Event tasks, extracts the date/time from input. 
    // TODO: For Deadline, return a List of String that just contains one element which is the due date
    // TODO: For Event, return a List of String where first element is the start time and second element is the end time
    
    // keyword is either "/by", or "/from" or "/to"
    public static String extractDateTime(String input, String keyword) {
        // System.out.println("index of keyword is " + input.indexOf(keyword));
        
        // Get index of word after keyword and space
        int startIndex = input.indexOf(keyword) + keyword.length() + 1;
        int endIndex;

        if (checkEquals(keyword, "/from")) {
            endIndex = input.indexOf('/', startIndex);
            // int endIndex = input.substring(indexOfSlash, input.length());
        } else {
            endIndex = input.length();
        }

        endIndex -= 1;

        return input.substring(startIndex, endIndex + 1).trim();

        /* 
        switch (keyword) {
            case "/by":
            case "/to":
                // System.out.println("Keyword is /by or /to");
                int endIndex = input.length() - 1;
                break;
            case "/from":
                // System.out.println("Keyword is /from");
                int indexOfSlash = input.indexOf('/', startIndex);
                System.out.println("At " + indexOfSlash + " is " + input.substring(indexOfSlash, input.length()));
                break;
        }
        */

        /* 
        int startIndex = input.indexOf(keyword) + keyword.length() + 1;
        // System.out.println("char at start index " + startIndex + " is " + input.charAt(startIndex));
        
        // For /from, endIndex is before /to
        int endIndex = input.indexOf(' ', startIndex);

        if (endIndex == -1) {
            endIndex = input.length() - 1;
        }

        // System.out.println("char at end index " + endIndex + " is " + input.charAt(endIndex));
        return input.substring(startIndex, endIndex + 1).trim();
        */
        
    }

    /* 
    public static String extractDateTime(String input) {
        int indexOfSlash = input.indexOf('/');
        // System.out.println("indexOfSlash is " + indexOfSlash);
        // System.out.println("indexOf " + input.charAt(input.indexOf(' ', indexOfSlash)) + " is " + (input.indexOf(' ', indexOfSlash) - 1));
        // String command = input.substring(indexOfSlash + 1, input.indexOf(input, indexOfSlash));
        return "";
    }
    */

    // Add task (Types supported: Todo, Deadline, Event)
    public static Task[] addTask(Task[] list, String input) {
        printLine();
        
        String command = input.trim().split(" ")[0];

        space(false, false);
        String messagePart1 = "Got it. I've added this task:";
        System.out.println(messagePart1);
        
        String description = extractDescription(input);
        
        Task task = new Task(description, false);

        // TODO: Handle the case where input is not in correct format, e.g. missing "/by" for deadline tasks, or missing "/from" / "/to" for event tasks
        if (checkEquals(command, TODO)) {
            task = new Todo(description, false);
        } else if (checkEquals(command, DEADLINE)) {
            String by = "/by";
            int byLength = by.length();
            
            // Check if input contains "/by"
            boolean isValid = input.contains(by);
            int indexOfBy = indexOfKeyword(input, by);
            
            // description = input.substring(command.length() + 1, indexOfBy - byLength - 1);
            
            // String due = input.substring(indexOfBy, input.length());
            String due = extractDateTime(input, by);
            
            task = new Deadline(description, false, due);
        } else if (checkEquals(command, EVENT)) {
            // event project meeting /from Mon 2pm /to 4pm

            // Check if input contains "/by"
            String from = "/from";
            int fromLength = from.length();
            String to = "/to";
            int toLength = to.length();

            boolean isValid = input.contains(from) && input.contains(to);
            int indexOfFrom = indexOfKeyword(input, from);
            int indexOfTo = indexOfKeyword(input, to);
            
            // description = input.substring(command.length() + 1, indexOfFrom - fromLength - 1);

            // String start = input.substring(indexOfFrom, indexOfTo - to.length() - 2);
            // String end = input.substring(indexOfTo, input.length());
            String start = extractDateTime(input, from);
            String end = extractDateTime(input, to);

            task = new Event(description, false, start, end);
        }

        space(false, true);
        System.out.println(task);

        list = addToList(list, task);

        space(false, false);
        int listLength = list.length;
        
        String messagePart2 = "Now you have " + listLength + " task";
        // String messagePart2 = "Now you have " + listLength;
        
        // if (listLength == 1) {
        //     messagePart2 += " task";
        // } else {
        //     messagePart2 += " tasks";
        // }
        
        if (listLength > 1) {
            messagePart2 += "s";
        }
        
        messagePart2 += " in the list.";

        System.out.println(messagePart2);
        
        printLine();
        
        return list;
    }

    // This function echos commands entered by the user, and exits when the user types the command bye.
    public static void echo() {
        Scanner in = new Scanner(System.in);

        // Get the input
        String input = in.nextLine().trim();

        // Assume there will be no more than 100 tasks. Initialize an empty list of String array
        Task[] list = new Task[0];

        // Continue looping until input is "bye"
        while (checkEquals(input, BYE) == false) {

            if (checkEquals(input, LIST)) {
                printList(list);
            } else if (checkInputStartsWith(input, MARK) || checkInputStartsWith(input, UNMARK)) {        // Check if input contains the command "mark" or "unmark"
                markDone(list, input);
            } else if (
                    checkInputStartsWith(input, TODO) ||
                    checkInputStartsWith(input, DEADLINE) || 
                    checkInputStartsWith(input, EVENT)
                ) {
                    // String command = input.substring(0, input.indexOf(' '));
                    // System.out.println("command is " + command);
                    // String idk = extractDateTime(input, "/by");
                    // System.out.println("idk is " + idk);
                    // extractDescription(input);
                    list = addTask(list, input);
                }
            else {
                // TODO: Since we now have 3 types of tasks (Todo, Deadline, and Event) implemented in Level-4, it should be safe to assume that all tasks that should be added to the task list, should start with either "todo", or "deadline", or "event". Hence, if it just starts with the task description, then it should not be a valid task
                System.out.println("Invalid command entered");
            }

            System.lineSeparator();

            // Ask the user for the next input
            input = in.nextLine().trim();
        }
    }

    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }
}
