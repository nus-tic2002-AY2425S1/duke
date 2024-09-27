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
    static final String BY="/by";
    static final String EVENT="event";
    static final String FROM="/from";
    static final String TO="/to";
    
    // Solution below adapted from https://stackoverflow.com/questions/1073787/print-spaces-with-string-format
    public static String space(int numberOfSpace) {
        String space = String.format("%" + numberOfSpace + "s", ""); 
        return space;
    }

    public static void space(boolean isLine, boolean isTask) {
        String space = ""; 

        // If space is to come before a horizontal line, use "    "
        if (isLine == true && isTask == false) {
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

    // Note that here, input is without command in front
    public static String extractDescription(String details, String command) {
        System.out.println("details in extractDescription: " + details);
        String description = details.trim();
        int endIndex;
        try {
            switch (command) {
                case TODO:
                    break;
                case DEADLINE:
                    endIndex = details.indexOf('/') - 1;
                    if (endIndex == -1) {
                        throw new TaskException("No deadline found");
                    }
                case EVENT:
                    endIndex = details.indexOf('/') - 1;
                    if (endIndex == -1) {
                        throw new TaskException("No deadline found");
                    }
                    break;
            }
        } catch (TaskException taskException) {
            System.out.println("Error: " + taskException.getMessage());
        }
        
        /* 
        int commandLength = input.trim().split(" ", 2)[0].length();
        int startIndex = commandLength + 1;
        
        String description = null;
        int endIndex;
        // String description = input.substring(commandLength + 1, input.indexOf('/'));
        
        // Check if input contains '/'
        if (input.contains("/")) {
            // Set startIndex to after command length and end index to before '/'
            endIndex = input.indexOf('/') - 1;
            // description = input.substring(startIndex, input.indexOf('/') - 1);
        } else {
            // If the command is todo
            endIndex = input.length();
            // description = input.substring(startIndex, input.length());
        }

        // System.out.println("(extractDescription) description: " + description);
        // System.out.println("start " + startIndex + " end " + endIndex);
        // System.out.println("start " + startIndex + " " + input.charAt(startIndex) + " end " + endIndex  + " " + input.charAt(endIndex));

        try {
            description = input.substring(startIndex, endIndex);
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            System.out.println("Missing description");
        }
        // description = description.trim();

        return description;
        */

        return description;
    }

    // Does not apply to Todo tasks. 
    // For Deadline tasks, extracts the date/time from input ("after '/by'"), using substring from after '/by' to the end of input. 
    // For Event tasks, extracts the date/time from input. 
    // TODO: For Deadline, return a List of String that just contains one element which is the due date
    // TODO: For Event, return a List of String where first element is the start time and second element is the end time
    
    // keyword is either "/by", or "/from" or "/to"
    public static String extractDateTime(String input, String keyword) {
        System.out.println("in extractDateTime: " + input.contains(keyword));
        
        // Check if user input contains the keyword
        try {
            if (input.contains(keyword) == false) {
                String message = "No " + keyword;
                throw new TaskException(message);
            }
        } catch (TaskException taskException) {
            printLine();
        }

        // Get index of word after keyword and space
        int startIndex = input.indexOf(keyword) + keyword.length() + 1;
        int endIndex;

        // For /from, endIndex is before /to
        if (checkEquals(keyword, "/from")) {
            endIndex = input.indexOf('/', startIndex);
            // int endIndex = input.substring(indexOfSlash, input.length());
        } else {
            endIndex = input.length();
        }

        String dateTime = null;

        try {
            dateTime = input.substring(startIndex, endIndex).trim();
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            System.out.println("Uh oh! There is no due date");
            // System.out.println("Missing date/time for " + );
        }

        return dateTime;
        
    }

    // public static void printExceptionMessage() {
    //     prin
    // }

    // Add task (Types supported: Todo, Deadline, Event)
    public static Task[] addTask(Task[] list, String input) throws TaskException {
        printLine();
        
        // Split the input into a String array where the first element is the command and the second element is the description + deadline OR start/end date/time or event
        String[] splitInput = input.trim().split(" ", 2);

        String command = splitInput[0];
    
        // Check if description is empty
        
        if (splitInput.length < 2 || splitInput[1].startsWith("/")) {
            throw new TaskException("The description of a task cannot be empty");
        }
        
        /* 
        try {
            if (splitInput.length < 2 || splitInput[1].startsWith("/")) {
                throw new TaskException("The description of a task cannot be empty");
            }
        } catch (TaskException missingDescription) {
            // printLine();
            String message = missingDescription.getMessage();
            printMessage(false, message);
            printLine();
            return list;
        }
        */
        
        space(false, false);
        String messagePart1 = "Got it. I've added this task:";
        System.out.println(messagePart1);

        // String description;
        // try {
        //     description = extractDescription(input);
        // } catch (EmptyDescriptionException emptyDescriptionException) {
        //     System.out.println(emptyDescriptionException);
        // }

        String description = null;
        // try {
        //     description = extractDescription(splitInput[1], command);
        // } catch (TaskException taskException) {
        //     String message = "Missing description";
        //     System.out.println(taskException.getMessage());
        // }
        
        Task task = null;
        // Task task = new Task(description, false);

        // TODO: Handle the case where input is not in correct format, e.g. missing "/by" for deadline tasks, or missing "/from" / "/to" for event tasks
        if (checkEquals(command, TODO)) {
            task = new Todo(description, false);
        } else if (checkEquals(command, DEADLINE)) {
            // Check if input contains "/by"
            boolean isValid = input.contains(BY);
            
            String due = extractDateTime(input, BY);
            
            task = new Deadline(description, false, due);
        } else if (checkEquals(command, EVENT)) {
            // Check if input contains "/from" and "/to"
            boolean isValid = input.contains(FROM) && input.contains(TO);

            String start = extractDateTime(input, FROM);
            String end = extractDateTime(input, TO);

            task = new Event(description, false, start, end);
        }

        space(false, true);
        System.out.println(task);

        list = addToList(list, task);

        space(false, false);
        int listLength = list.length;
        
        String messagePart2 = "Now you have " + listLength + " task";

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
        
        // Assume there will be no more than 100 tasks. Initialize an empty list of String array
        Task[] list = new Task[0];
        
        // Continue looping indefinitely
        while (true) {
            
            // Get the input
            String input = in.nextLine().trim();
            
            // Check if the command entered by the user is valid
            try {
                if (checkEquals(input, BYE)) {
                    exit();
                    break;
                } else if (checkEquals(input, LIST)) {
                    printList(list);
                } else if (checkInputStartsWith(input, MARK) || checkInputStartsWith(input, UNMARK)) {        // Check if input contains the command "mark" or "unmark"
                    markDone(list, input);
                } else if (
                        checkInputStartsWith(input, TODO) ||
                        checkInputStartsWith(input, DEADLINE) || 
                        checkInputStartsWith(input, EVENT)
                    ) {
                        list = addTask(list, input);
                } else {
                    String message = "Invalid command entered. Please start with 'list', 'mark', 'unmark', 'todo', 'deadline', 'event'. If you are done, please enter 'bye' to exit the chat";
                    throw new CommandException(message);
                }

                System.lineSeparator();

            } catch (CommandException commandException) {     // When user enters an invalid command, e.g. gibberish
                printLine();
                printMessage(true, commandException.getMessage());
                printLine();
            } catch (TaskException taskException) {
                printMessage(false, taskException.getMessage());
                printLine();
            }
        }
        
    }

    public static void main(String[] args) {
        greet();
        echo();
    }
}
