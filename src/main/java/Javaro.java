import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Javaro {
    
    // Use constant variables to store the keyword commands
    // https://www.javatpoint.com/java-constant
    private static final String CHATBOT_NAME = "Javaro";
    private static final String LINE = "____________________________________________________________";
    private static final String NEW_LINE = "\n";
    
    private static final String BY="/by";
    
    private static final String FROM="/from";
    private static final String TO="/to";
    
    private static final String DIRECTORY_PATH="./data/";
    private static final String FILE_PATH="./data/tasks.txt";
    
    // Assume there will be no more than 100 tasks. Initialize an empty list of String array
    private static ArrayList<Task> taskList;

    private static StorageFile taskFile;

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
    // https://stackoverflow.com/questions/2914695/how-can-you-write-a-function-that-accepts-multiple-types
    public static <T> void printMessage(T messageList) {
        String line = getLine();        // includes space before line
        String space = getSpace(false, false);
        
        StringBuilder stringBuilder = new StringBuilder().append(line).append(NEW_LINE);
        
        // System.out.println(messageList.getClass());

        // https://stackoverflow.com/questions/40899820/arrays-check-if-object-is-an-array
        if (messageList.getClass().isArray()) {
            for (int i = 0; i < Array.getLength(messageList); i++) {
                stringBuilder.append(space)
                             .append(Array.get(messageList, i))
                             .append(NEW_LINE);
            }
        } 
        
        // https://stackoverflow.com/questions/14674027/checking-if-object-is-instance-of-listobject
        else if (messageList instanceof ArrayList<?>) {
            for (int i = 0; i < ((ArrayList<?>) messageList).size(); i++) {
                stringBuilder.append(space).append(
                                                ((ArrayList<?>) messageList)
                                                .get(i)
                                            )
                                           .append(NEW_LINE);
            }
        }

        String text = stringBuilder.append(line).toString();
        
        System.out.println(text);
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

    // For list command
    public static void printTaskList(ArrayList<Task> taskList) throws TaskException {
        // https://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
        // ArrayList<String> places = new ArrayList<>(Arrays.asList("Buenos Aires", "Córdoba", "La Plata"));
        ArrayList<String> messageList = new ArrayList<>(Arrays.asList("Here are the tasks in your list:"));
        
        int taskListSize = taskList.size();
        
        if (taskListSize == 0) {
            throw new TaskException("Good job! You're all caught up!");
        }
        
        for (int i = 0; i < taskListSize; i++) {
            Task current = taskList.get(i);         // taskList.get(i) contains the checkbox
            String index = Integer.toString(i + 1);
            String line = index + ". " + current;
            messageList.add(line);
        }
        
        printMessage(messageList);
    }

    // TODO: Add validation checks for input, e.g. What if user just press enter without keying any words
    public static boolean checkEquals(String str1, String str2) {
        return str1.equals(str2);
    }

    // Check if user input starts with a specified command
    public static boolean checkInputStartsWith(String input, String command) {
        return input.startsWith(command.toString());
    }


    public static String getTaskWord(int taskListSize) {
        StringBuilder taskWordStringBuilder = new StringBuilder(" task");
        if (taskListSize > 1) {
            taskWordStringBuilder.append("s");
        }
        String taskWord = taskWordStringBuilder.toString();
        return taskWord;
    }

    // Add the ability to mark tasks as done. Optionally, add the ability to change the status back to not done.
    // list will be the list of tasks that the user has entered
    // input will be the command that the user types (e.g. "mark 1")
    // TODO: What if index entered by user is greater than the number of items in the list
    public static ArrayList<Task> markDone(ArrayList<Task> taskList, String inputIndex) throws TaskException {
        String message;

        int indexToMark = -1;
        Task taskToMark = null;

        String[] messageList = null;
        // ArrayList<String> messageList = new ArrayList<>(Arrays.asList("Here are the tasks in your list:"));

        // https://stackoverflow.com/questions/5554734/what-causes-a-java-lang-arrayindexoutofboundsexception-and-how-do-i-prevent-it
        // https://www.geeksforgeeks.org/array-index-out-of-bounds-exception-in-java/
        try {
            // indexToMark = Integer.parseInt(input.substring(indexOfSpace + 1)) - 1;
            indexToMark = Integer.parseInt(inputIndex) - 1;
            taskToMark = taskList.get(indexToMark);
            
            String alreadyMarkedMessage = "The task is already marked as done. No action done.";
            
            // System.out.println("taskToMark getIsDone: " + taskToMark.getIsDone());
            // System.out.println("taskToMark getIsDoneValue: " + taskToMark.getIsDoneValue());

            if (taskToMark.getIsDone() == true) {
                System.out.println("getIsDone is true");
                // throw new TaskException(alreadyMarkedMessage.toString());
                messageList = new String[]{alreadyMarkedMessage};
                throw new TaskException(alreadyMarkedMessage);
            }
    
            // Mark task as done
            message = "Nice! I've marked this task as done:";
            // messageList.add(message);
            taskToMark.setDone(true);

            taskFile.markTask(taskList, indexToMark);

            // taskFile.replaceTask(taskList, taskToMark);
            // taskToMark.writeToFile(taskFile);

            // messageList.add(formatSpace(2) + taskToMark);
            // String[] messageList = {message, formatSpace(2) + taskToMark};

            messageList = new String[]{message, formatSpace(2) + taskToMark};

        } catch (IndexOutOfBoundsException | NumberFormatException e) {     // handles the case where index (from input) > number of items in the list, or when index (from input) > number of items in the list
            int taskListSize = taskList.size();
            if (taskListSize < 1) {
                message = "Your task list is empty. Please add a task first.";
            } else {
                message = "Task not found. Please enter a valid task number from 1 to " + taskList.size() + ".";
            }
            messageList = new String[]{message};
            // messageList.add(message);
            throw new TaskException(message);
        }

        // System.out.println("Now, messageList is " + messageList);
        // Print result on CLI
        // String[] messageList;
        // if (taskToMark == null) {
        //     String[] messageList = {};
        // }
        // System.out.println(Arrays.toString(messageList));
        printMessage(messageList);

        return taskList;
        
        /*
        // Unmark task
        StringBuilder alreadyUnmarkedMessage = new StringBuilder(alreadyMarkedMessage).append("not ");
        alreadyUnmarkedMessage = alreadyUnmarkedMessage.append("done. No action done.");
        if (taskToMark.getIsDone() == false) {
                // throw new TaskException(alreadyUnmarkedMessage.toString());
                throw new TaskException("The task is already marked as not done. No action done.");
            }
            // Mark task as not done
            message = "OK, I've marked this task as not done yet:";
            taskToMark.setDone(false);
            // taskToMark.writeToFile(taskFile);
        */

        
    }


    public static ArrayList<Task> unmarkDone(ArrayList<Task> taskList, String inputIndex) throws TaskException {
        String message;

        int indexToMark = -1;
        Task taskToMark = null;

        String[] messageList = null;
        // ArrayList<String> messageList = new ArrayList<>(Arrays.asList("Here are the tasks in your list:"));

        // https://stackoverflow.com/questions/5554734/what-causes-a-java-lang-arrayindexoutofboundsexception-and-how-do-i-prevent-it
        // https://www.geeksforgeeks.org/array-index-out-of-bounds-exception-in-java/
        try {
            indexToMark = Integer.parseInt(inputIndex) - 1;
            taskToMark = taskList.get(indexToMark);
            
            
            if (taskToMark.getIsDone() == false) {
                // throw new TaskException(alreadyUnmarkedMessage.toString());
                String alreadyUnmarkedMessage = "The task is already marked as not done. No action done.";
                throw new TaskException(alreadyUnmarkedMessage);
            }
            // Mark task as not done
            message = "OK, I've marked this task as not done yet:";
            taskToMark.setDone(false);
            // taskToMark.writeToFile(taskFile);

            // System.out.println("taskToMark getIsDone: " + taskToMark.getIsDone());
            // System.out.println("taskToMark getIsDoneValue: " + taskToMark.getIsDoneValue());

            // if (taskToMark.getIsDone() == false) {
            //     System.out.println("getIsDone is true");
            //     // throw new TaskException(alreadyMarkedMessage.toString());
            //     messageList = new String[]{alreadyUnmarkedMessage};
            //     throw new TaskException(alreadyUnmarkedMessage);
            // }
    
            // Mark task as done
            message = "Nice! I've marked this task as not done yet:";
            // messageList.add(message);
            taskToMark.setDone(false);
            
            taskFile.markTask(taskList, indexToMark);

            // taskFile.replaceTask(taskList, taskToMark);
            // taskToMark.writeToFile(taskFile);

            // messageList.add(formatSpace(2) + taskToMark);
            // String[] messageList = {message, formatSpace(2) + taskToMark};

            messageList = new String[]{message, formatSpace(2) + taskToMark};

        } catch (IndexOutOfBoundsException | NumberFormatException e) {     // handles the case where index (from input) > number of items in the list, or when index (from input) > number of items in the list
            int taskListSize = taskList.size();
            if (taskListSize < 1) {
                message = "Your task list is empty. Please add a task first.";
            } else {
                message = "Task not found. Please enter a valid task number from 1 to " + taskList.size() + ".";
            }
            messageList = new String[]{message};
            // messageList.add(message);
            throw new TaskException(message);
        }

        // System.out.println("Now, messageList is " + messageList);
        // Print result on CLI
        // String[] messageList;
        // if (taskToMark == null) {
        //     String[] messageList = {};
        // }
        // System.out.println(Arrays.toString(messageList));
        printMessage(messageList);

        return taskList;
        
    }


    /* 
    public static void markDone(ArrayList<Task> taskList, String input) throws TaskException {
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
            taskToMark = taskList.get(indexToMark);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            message = "Task not found. Please enter a valid task number from 1 to " + taskList.size() + ".";
            throw new TaskException(message);
        }
        
        StringBuilder alreadyMarkedMessage = new StringBuilder("The task is already marked as ");
        StringBuilder alreadyUnmarkedMessage = new StringBuilder(alreadyMarkedMessage).append("not ");
        alreadyMarkedMessage = alreadyMarkedMessage.append("done. No action done.");
        alreadyUnmarkedMessage = alreadyUnmarkedMessage.append("done. No action done.");
        
        if (checkInputStartsWith(input, Command.MARK)) {
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
    */


    // Note that here, input is without command in front
    public static String extractDescription(String details, String command) throws TaskException {
        String description = details.trim();

        boolean isTodo = checkEquals(command, Command.TODO);
        boolean isDeadline = checkEquals(command, Command.DEADLINE);
        boolean isEvent = checkEquals(command, Command.EVENT);

        if (isTodo == true) {
            description = details.substring(0, details.length()).trim();
        } else {
            int slashIndex = details.indexOf('/');
            // Check if details has '/', i.e. whether '/by' or '/from' or '/to' is present
            boolean hasSlash = slashIndex != -1;
            
            String missingInfo = new String();
            String message;
    
            if (hasSlash == false) {     // User did not input /
                if (isDeadline == true) {
                    missingInfo = BY;
                    // throw new TaskException("Error: Missing '/' in deadline command. Please enter the deadline in the format 'deadline <description> /by <due date>'.");
                } else if (isEvent == true) {
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
                if (isDeadline == true) {      // "Please enter the due date of the deadline"
                    missingInfo = "due date";
                } else if (isEvent == true) {      // "Please enter the start date/time of the event"
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
    public static ArrayList<Task> addTask(ArrayList<Task> taskList, String input) throws TaskException {
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
        if (checkEquals(command, Command.TODO)) {
            task = new Todo(description, false);
        } else if (checkEquals(command, Command.DEADLINE)) {
            // Check if input contains "/by"
            // boolean hasBy = input.contains(BY);
            // System.out.println("hasBy " + hasBy);
            
            // if (isContain(input, BY) == false) {
            // if (hasBy == false) {
            //     throw new TaskException("Missing /by information");
            // }

            String due = extractDateTime(input, BY);
            
            task = new Deadline(description, false, due);
        } else if (checkEquals(command, Command.EVENT)) {
            // Check if input contains "/from" and "/to"
            // boolean isValid = input.contains(FROM) && input.contains(TO);

            if (input.contains(FROM) == false) {
                throw new TaskException("Please enter \"/from\" followed by the start date/time of the event");
            }

            String start = extractDateTime(input, FROM);
            String end = extractDateTime(input, TO);

            task = new Event(description, false, start, end);
        }

        taskList.add(task);
        // System.out.println(task.generateString());
        taskFile.writeToFile(task.encodeTask());
        // task.writeToFile(taskFile);

        int taskListSize = taskList.size();
        String taskWord = getTaskWord(taskListSize);

        String[] messageList = {"Got it. I've added this task:", formatSpace(2) + task, 
                                "Now you have " + taskListSize + taskWord + " in the list."};

        printMessage(messageList);

        return taskList;
    }


    public static ArrayList<Task> deleteTask(ArrayList<Task> taskList, String input) throws TaskException {
        // System.out.println("taskList before removal: " + taskList);
        // Split the input into a String array where the first element is the command and the second element is the description + deadline OR start/end date/time or event
        String[] splitInput = input.trim().split(" ", 2);
        String command = splitInput[0];
        String index = splitInput[1];
        int indexToDelete = -1;
        Task taskToDelete;
        String message;

        try {
            indexToDelete = Integer.parseInt(index) - 1;
            taskToDelete = taskList.get(indexToDelete);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            message = "Task not found. Please enter a valid task number from 1 to " + taskList.size() + ".";
            throw new TaskException(message);
        }

        // System.out.println("indexToDelete is " + indexToDelete + NEW_LINE + "taskToDelete is " + taskToDelete);
        taskList.remove(indexToDelete);

        int taskListSize = taskList.size();
        String taskWord = getTaskWord(taskListSize);

        String[] messageList = {"Noted. I've removed this task:", 
                                formatSpace(2) + taskToDelete,
                                "Now you have " + taskListSize + taskWord + " in the list."};
        printMessage(messageList);

        // System.out.println("taskList after removal: " + taskList);
        return taskList;
    }

    
    // This function echos commands entered by the user, and exits when the user types the command bye.
    public static void echo() {
        Scanner in = new Scanner(System.in);

        taskFile = new StorageFile();
        // System.out.println("taskFile is " + taskFile);

        // taskList = new ArrayList<>();

        // Initialize taskList
        try {
            taskList = taskFile.loadTasks();
        } catch (StorageFileException e) {
            String[] messageList = {e.getMessage()};
            printMessage(messageList);
        }
        // System.out.println("tasklist is " + taskList);

        // String inputRaw = in.nextLine().trim();
        // String[] input = inputRaw.split(" ", 2);
        // // System.out.println("input 0 " + input[0]);
        // // System.out.println("input 1 " + input[1]);
        // String command = input[0];
        // // System.out.println(command);
        // String taskDetails = input[1];

        // System.out.println(Command.BYE);

        // Continue looping indefinitely
        while (true) {
            
            System.out.println();

            // Get the input
            String inputRaw = in.nextLine().trim();
            String[] input = inputRaw.split(" ", 2);
            // System.out.println("input 0 " + input[0]);
            // System.out.println("input 1 " + input[1]);
            String command = input[0].toLowerCase();
            // System.out.println(command);

            String taskDetails = null;
            if (input.length > 1) {
                taskDetails = input[1];
            }

            // String taskDetails = null;
            // try {
            //     String taskDetails = input[1];
            //     if (checkEquals(command, Command.BYE)) {
            //         exit();
            //         break;
            //     } else if (taskDetails == null) {
            //         // user only entered one word
            //         throw new CommandException("Insufficient details");
            //     }
            // } catch (ArrayIndexOutOfBoundsException exception) {
            //     System.out.println(exception.getMessage());
            // }

            // Command input = Command.valueOf(in.nextLine().toLowerCase().trim());

            // Check if the command entered by the user is valid
            
            // For switch commandEnum
            // Command commandEnum;
            // try {
            //     commandEnum = Command.fromString(command);
            // } catch (IllegalArgumentException e) {
            //     String message = "Invalid command entered. " + NEW_LINE + getSpace(false, false) + 
            //                      "Please start with 'list', 'mark', 'unmark', 'todo', 'deadline', 'event'. If you are done, please enter 'bye' to exit the chat";
            //     throw new CommandException(message);
            // }

            try {
                // /* 
                if (checkEquals(command, Command.BYE)) {
                    exit();
                    break;
                } else if (checkEquals(command, Command.LIST)) {
                    printTaskList(taskList);
                } else if (checkEquals(command, Command.MARK)) {        // Check if input contains the command "mark" or "unmark"
                    markDone(taskList, taskDetails);
                } else if (checkEquals(command, Command.UNMARK)) {        // Check if input contains the command "mark" or "unmark"
                    unmarkDone(taskList, taskDetails);
                } else if (
                    checkEquals(command, Command.TODO) ||
                    checkEquals(command, Command.DEADLINE) || 
                    checkEquals(command, Command.EVENT)
                    ) {
                        taskList = addTask(taskList, inputRaw);
                } else if (checkEquals(command, Command.DELETE)) {
                    // taskList = deleteTask(taskList, input);
                } else {
                    String message = "Invalid command entered. " + NEW_LINE + getSpace(false, false) + 
                                        "Please start with 'list', 'mark', 'unmark', 'todo', 'deadline', 'event'. If you are done, please enter 'bye' to exit the chat";
                    throw new CommandException(message);
                }

                System.lineSeparator();
                // */

                /* 
                switch (commandEnum) {
                    case BYE:
                        exit();
                        break;
                    case LIST:
                        printTaskList(taskList);
                        break;
                    case Command.MARK:
                    case Command.UNMARK:
                        markDone(taskList, inputRaw);
                        break;
                    case Command.TODO:
                    case Command.DEADLINE:
                    case Command.EVENT:
                        taskList = addTask(taskList, inputRaw);
                        break;
                    case Command.DELETE:
                        taskList = deleteTask(taskList, inputRaw);
                        break;
                    default:
                        String message = "Invalid command entered. " + NEW_LINE + getSpace(false, false) + 
                                            "Please start with 'list', 'mark', 'unmark', 'todo', 'deadline', 'event'. If you are done, please enter 'bye' to exit the chat";
                        throw new CommandException(message);
                }
                */

                /* 
                // https://stackoverflow.com/questions/2836286/enums-use-in-switch-case
                switch (Command.valueOf(command)) {
                    case BYE:
                        exit();
                        break;
                    case LIST:
                        printTaskList(taskList);
                        break;
                    case MARK:
                    case UNMARK:
                        markDone(taskList, inputRaw);
                        break;
                    case TODO:
                    case DEADLINE:
                    case EVENT:
                        taskList = addTask(taskList, inputRaw);
                        break;
                    case DELETE:
                        taskList = deleteTask(taskList, inputRaw);
                        break;
                    default:
                        String message = "Invalid command entered. " + NEW_LINE + getSpace(false, false) + 
                                            "Please start with 'list', 'mark', 'unmark', 'todo', 'deadline', 'event'. If you are done, please enter 'bye' to exit the chat";
                        throw new CommandException(message);
                }
                */

            } catch (CommandException commandException) {     // When user enters an invalid command, e.g. gibberish
                // String[] messageList = {commandException.getMessage()};
                // printMessage(messageList);
            } catch (TaskException taskException) {
                String[] messageList = {taskException.getMessage()};
                printMessage(messageList);
            }
        }

        // in.close();
    }

    public static void main(String[] args) {
        // System.out.println(Command.BYE);
        // System.out.println(Command.BYE.getClass().getName());
        greet();
        echo();
    }
}
