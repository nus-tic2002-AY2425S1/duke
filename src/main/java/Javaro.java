import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

public class Javaro {
    
    // Use constant variables to store the keyword commands
    // https://www.javatpoint.com/java-constant
    static final String CHATBOT_NAME = "Javaro";
    static final String LINE = "____________________________________________________________";
    static final String NEW_LINE = "\n";

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
    // https://stackoverflow.com/questions/69576641/why-would-you-use-a-stringbuilder-method-over-a-string-in-java
    public static String formatSpace(int numberOfSpace) {
        // String space = String.format("%" + numberOfSpace + "s", "");
        String format = "%" + numberOfSpace + "s";
        String space = String.format(format, "");
        return space;
    }

    public static String getSpace(boolean isLine, boolean isTask) {
        // If space is to come before a horizontal line, use "    "
        if (isLine == true && isTask == false) {
            return formatSpace(4);
        } else if (isLine == false && isTask == true) {        // If space is to come before a line of text, use "     "
            // For printing task
            return formatSpace(7);
        } else if (isLine == false && isTask == false) {
            return formatSpace(5);
        } else {
            return "";
        }
    }

    public static String getLine() {
        String space = getSpace(true, false);
        return space + LINE;
    }

    // Prints a horizontal line, followed by the message (may be one or multiple lines), then another horizontal line
    public static void printMessage(String[] message) {
        String line = getLine();        // includes space before line
        String space = getSpace(false, false);
        
        StringBuilder stringBuilder = new StringBuilder().append(line).append(NEW_LINE);
        
        for (int i = 0; i < message.length; i++) {
            stringBuilder.append(space).append(message[i]).append(NEW_LINE);
        }
        
        String text = stringBuilder.append(line).toString();
        
        System.out.println(text);
    }

    public static String[] addMessage(String[] messageList, String message) {
        int listLength = messageList.length;
        String[] newList = Arrays.copyOf(messageList, listLength + 1);
        newList[listLength] = message;
        return newList;
    }

    // Print greeting message
    public static void greet() {
        String[] messageList = {"Hello, I'm " + CHATBOT_NAME + ".", "What can I do for you?"};
        printMessage(messageList);
    }

    // Chatbot exits
    public static void exit() {
        String[] messageList = {"Bye. Hope to see you again soon!"};
        printMessage(messageList);
    }

    // 1st element has index 0. Length 1
    public static Task[] addToList(Task[] list, Task task) {
        int listLength = list.length;
        Task[] newList = Arrays.copyOf(list, listLength + 1);
        newList[listLength] = task;
        return newList;
    }

    public static void printTaskList(Task[] list) throws TaskException {
        String[] messageList = {"Here are the tasks in your list:"};
        if (list.length == 0) {
            throw new TaskException("Yay! You're all caught up!");
        }
        for (int i = 0; i < list.length; i++) {
            Task current = list[i];
            String index = Integer.toString(i + 1);

            // list[i] contains the checkbox
            String line = index + ". " + current;
            
            messageList = addMessage(messageList, line);
        }
        printMessage(messageList);
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
    public static void markDone(Task[] list, String input) throws TaskException {
        String message;

        // To get the task number that the user wants to mark/unmark, cannot simply extract the last character because this would assume that the task number will always be less than 10, i.e. it will not work if there are more than 9 tasks in the list
        // Because command can be "mark" or "unmark", cannot simply use the length of the word "mark" or "unmark" and +2 (+1 for the space after the command and another +1 to get to the task number) to get the value of the task number that the user wants to mark/unmark
        // Instead, the substring function extracts the value after the space, i.e. value of task number that user wants to mark/unmark. Then to get the index (because String array indices start from 0), need to -1
        int indexOfSpace = input.indexOf(' ');
        int indexToMark = -1;
        Task taskToMark;
        
        // https://stackoverflow.com/questions/5554734/what-causes-a-java-lang-arrayindexoutofboundsexception-and-how-do-i-prevent-it
        // https://www.geeksforgeeks.org/array-index-out-of-bounds-exception-in-java/
        // TODO: Test if it handles the case where index (from input) > number of items in the list, or when index (from input) > number of items in the list
        try {
            indexToMark = Integer.parseInt(input.substring(indexOfSpace + 1)) - 1;
            taskToMark = list[indexToMark];
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            message = "Task not found. Please enter a valid task number from 1 to " + list.length + ".";
            throw new TaskException(message);
        }
        
        StringBuilder alreadyMarkedMessage = new StringBuilder("The task is already marked as ");
        StringBuilder alreadyUnmarkedMessage = new StringBuilder(alreadyMarkedMessage).append("not ");
        alreadyMarkedMessage = alreadyMarkedMessage.append("done. No action done.");
        alreadyUnmarkedMessage = alreadyUnmarkedMessage.append("done. No action done.");
        
        if (input.startsWith(MARK)) {
            if (taskToMark.getDone() == true) {
                // throw new TaskException(alreadyMarkedMessage.toString());
                throw new TaskException("The task is already marked as done. No action done.");
            }
            // Mark task as done
            message = "Nice! I've marked this task as done:";
            taskToMark.setDone(true);
        } else {
            if (taskToMark.getDone() == false) {
                // throw new TaskException(alreadyUnmarkedMessage.toString());
                throw new TaskException("The task is already marked as not done. No action done.");
            }
            // Mark task as not done
            message = "OK, I've marked this task as not done yet:";
            taskToMark.setDone(false);
        }
        // Print result on CLI
        String[] messageList = {message, formatSpace(2) + taskToMark};
        printMessage(messageList);
    }


    // Note that here, input is without command in front
    public static String extractDescription(String details, String command) throws TaskException {
        String description = details.trim();

        if (command.equals(TODO) == true) {
            description = details.substring(0, details.length()).trim();
        } else {
            int slashIndex = details.indexOf('/');
            // Check if details has '/', i.e. whether '/by' or '/from' or '/to' is present
            boolean hasSlash = slashIndex != -1;
            
            String missingInfo = new String();
            String message;
    
            if (hasSlash == false) {     // User did not input /
                if (command.equals(DEADLINE)) {
                    missingInfo = BY;
                    // throw new TaskException("Error: Missing '/' in deadline command. Please enter the deadline in the format 'deadline <description> /by <due date>'.");
                } else if (command.equals(EVENT)) {
                    missingInfo = FROM;
                    // throw new TaskException("Error: Missing '/' in event command. Please enter the event in the format 'event <description> /from <start date/time> /to <end date/time>'.");
                }
                message = "Please enter " + missingInfo + " information";
                throw new TaskException(message);
            }
    
            int afterIndex = details.substring(slashIndex).indexOf(' ');

            // Check if user has entered anything after '/by' or '/from' or '/to'
            boolean hasAfter = afterIndex != -1;
    
            if (hasAfter == false) {
                if (command.equals(DEADLINE)) {      // "Please enter the due date of the deadline"
                    missingInfo = "due date";
                } else if (command.equals(EVENT)) {      // "Please enter the start date/time of the event"
                    missingInfo = "start date/time";
                }
                message = "Please enter the " + missingInfo;
                throw new TaskException(message);
            }
    
            if (hasSlash == true && hasAfter == true) {
                description = details.substring(0, slashIndex - 1).trim();
            }
        }

        return description;
    }

    // Does not apply to Todo tasks. 
    // For Deadline tasks, extracts the date/time from input ("after '/by'"), using substring from after '/by' to the end of input. 
    // For Event tasks, extracts the date/time from input. 
    // TODO: For Deadline, return a List of String that just contains one element which is the due date
    // TODO: For Event, return a List of String where first element is the start time and second element is the end time
    
    // keyword is either "/by", or "/from" or "/to"
    public static String extractDateTime(String input, String keyword) throws TaskException {

        // Get index of word after keyword and space
        int startIndex = input.indexOf(keyword) + keyword.length() + 1;
        // System.out.println("startIndex is derived by " + input.indexOf(keyword) + " + length + 1 is " + keyword.length() + 1);
        int endIndex;

        // For /from, endIndex is before /to
        if (checkEquals(keyword, FROM)) {
            endIndex = input.indexOf('/', startIndex);
            // int endIndex = input.substring(indexOfSlash, input.length());

            if (endIndex == -1) {
                String message = "End date/time is missing." + NEW_LINE + getSpace(false, false) + 
                                    "Please enter the date/time that the event ends at after \"" + FROM + "\"";
                throw new TaskException(message);
            }
        } else {
            endIndex = input.length();
        }

        // first index of word after /from
        // System.out.println("startIndex in extractDateTime is " + startIndex);
        // System.out.println("endIndex in extractDateTime is " + endIndex);

        String dateTime = input.substring(startIndex, endIndex).trim();

        if (dateTime.isEmpty()) {
            String message = String.format("Please enter a value after %s", keyword);
            throw new TaskException(message);
        }

        return dateTime;
        
    }

    // https://stackoverflow.com/questions/25417363/java-string-contains-matches-exact-word
    private static boolean isContain(String source, String subItem){
        String regex = "\\b";
        String pattern = regex + subItem + regex;
        // String pattern = "\\b" + subItem + "\\b";
        
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }

    // Add task (Types supported: Todo, Deadline, Event)
    public static Task[] addTask(Task[] list, String input) throws TaskException {
        // printLine();
        
        // Split the input into a String array where the first element is the command and the second element is the description + deadline OR start/end date/time or event
        String[] splitInput = input.trim().split(" ", 2);

        String command = splitInput[0];
    
        // Check if description is empty
        if (splitInput.length < 2 || splitInput[1].startsWith("/")) {
            throw new TaskException("The description of a task cannot be empty. " + NEW_LINE + 
                                    getSpace(false, false) + "Please add a description for the task. Example: \"" + command + " borrow book\"");
        }
        
        String description = extractDescription(splitInput[1], command);
        // System.out.println("Description: " + description);

        Task task = null;

        // For todo, check if user has entered description. If not, don't add to task list
        if (checkEquals(command, TODO)) {
            task = new Todo(description, false);
        } else if (checkEquals(command, DEADLINE)) {
            // Check if input contains "/by"
            // boolean hasBy = input.contains(BY);
            // System.out.println("hasBy " + hasBy);
            
            // if (isContain(input, BY) == false) {
            // if (hasBy == false) {
            //     throw new TaskException("Missing /by information");
            // }

            String due = extractDateTime(input, BY);
            
            task = new Deadline(description, false, due);
        } else if (checkEquals(command, EVENT)) {
            // Check if input contains "/from" and "/to"
            // boolean isValid = input.contains(FROM) && input.contains(TO);

            if (input.contains(FROM) == false) {
                throw new TaskException("Please enter \"/from\" followed by the start date/time of the event");
            }

            String start = extractDateTime(input, FROM);
            String end = extractDateTime(input, TO);

            task = new Event(description, false, start, end);
        }

        list = addToList(list, task);

        int listLength = list.length;
        StringBuilder taskWordStringBuilder = new StringBuilder(" task");
        if (listLength > 1) {
            taskWordStringBuilder.append("s");
        }
        String taskWord = taskWordStringBuilder.toString();

        String[] messageList = {"Got it. I've added this task:", formatSpace(2) + task, "Now you have " + listLength + taskWord + " in the list."};

        printMessage(messageList);

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
                    printTaskList(list);
                } else if (checkInputStartsWith(input, MARK) || checkInputStartsWith(input, UNMARK)) {        // Check if input contains the command "mark" or "unmark"
                    markDone(list, input);
                } else if (
                        checkInputStartsWith(input, TODO) ||
                        checkInputStartsWith(input, DEADLINE) || 
                        checkInputStartsWith(input, EVENT)
                    ) {
                        list = addTask(list, input);
                } else {
                    String message = "Invalid command entered. " + NEW_LINE + getSpace(false, false) + 
                                        "Please start with 'list', 'mark', 'unmark', 'todo', 'deadline', 'event'. If you are done, please enter 'bye' to exit the chat";
                    throw new CommandException(message);
                }

                System.lineSeparator();

            } catch (CommandException commandException) {     // When user enters an invalid command, e.g. gibberish
                String[] messageList = {commandException.getMessage()};
                printMessage(messageList);
            } catch (TaskException taskException) {
                String[] messageList = {taskException.getMessage()};
                printMessage(messageList);
            }
        }
        
    }

    public static void main(String[] args) {
        greet();
        echo();
    }
}
