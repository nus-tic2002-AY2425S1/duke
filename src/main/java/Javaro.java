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
    
    // /* 
    // public static void space(int numberOfSpace) {
    public static String space(int numberOfSpace) {
        String space = String.format("%" + numberOfSpace + "s", ""); 
        return space;
        // System.out.print(space);
    }
    // */

    /*
    public static void space(boolean isLine, boolean isTask) {
        // https://stackoverflow.com/questions/1073787/print-spaces-with-string-format
        String space; 

        // If space is to come before a horizontal line, use "    "
        if (isLine) {
            // Print 4 spaces
            space = space(4);
        } else {        // If space is to come before a line of text, use "     "
            space = space(5);
        }

        if (isTask == false) {
            space = space(6);
        }

        System.out.print(space);
    }
    */

    public static void space(boolean isLine, boolean isTask) {
        // https://stackoverflow.com/questions/1073787/print-spaces-with-string-format
        String space = ""; 

        // If space is to come before a horizontal line, use "    "
        if (isLine == true && isTask == true) {
            // Print 4 spaces
            // space = space(4);
        } else if (isLine == true && isTask == false) {
            // System.out.println("istask is true");
            space = space(4);
        } else if (isLine == false && isTask == true) {        // If space is to come before a line of text, use "     "
            // For printing task
            space = space(7);
        } else if (isLine == false && isTask == false) {
            // System.out.println("istask is false");
            space = space(5);
        } 

        System.out.print(space);
    }

    // Print the message
    public static void printMessage(boolean isLine, String message) {
        // System.out.println("isline value is " + isLine);
        space(isLine, false);
        System.out.println(message);
    }

    // This function prints a horizontal line
    public static void printLine() {
        space(true, false);
        System.out.println(LINE);
        // printMessage(true, LINE);
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

        // When adding the task to the list, initialize the task with checkbox as not done
        // Task newTask = new Task();
        // newTask.setDescription(item);
        
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

    public static void addTodo(Task[] list, String input) {
        // printLine();

        String messagePart1 = "Got it. I've added this task:";
        // System.out.println(messagePart1);

        String description = input.substring(TODO.length() + 1, input.length());
        System.out.println(description);

        Todo task = new Todo(description, false);

        addToList(list, task);

        System.out.println("This is the todo task: " + task);

        String messagePart2 = "Now you have " + list.length + " tasks in the list.";
        // System.out.println(messagePart2);

        // printLine();
    }

    // Add task (Types: Todo, Deadline, Event)
    public static Task[] addTask(Task[] list, String input) {
        printLine();
        
        String command = input.split(" ")[0];
        // System.out.println("command is " + command);

        space(false, false);
        String messagePart1 = "Got it. I've added this task:";
        System.out.println(messagePart1);
        
        String description = input.substring(command.length() + 1, input.length());
        // System.out.println(description);
        
        Task task = new Task(description, false);

        if (checkEquals(command, TODO)) {
            task = new Todo(description, false);
        } else if (checkEquals(command, DEADLINE)) {
            // deadline return book /by Sunday
            
            // Check if input contains "/by"
            String by = "/by";
            int byLength = by.length();

            boolean isValid = input.contains(by);
            int indexOfBy = input.indexOf(by) + byLength + 1;
            
            description = input.substring(command.length() + 1, indexOfBy - byLength - 1);
            
            String due = input.substring(indexOfBy, input.length());
            // System.out.println("Due: " + due);
            
            task = new Deadline(description, false, due);
        } else if (checkEquals(command, EVENT)) {
            // event project meeting /from Mon 2pm /to 4pm

            // Check if input contains "/by"
            String from = "/from";
            int fromLength = from.length();
            String to = "/to";
            int toLength = to.length();

            boolean isValid = input.contains(from) && input.contains(to);
            int indexOfFrom = input.indexOf(from) + fromLength + 1;
            int indexOfTo = input.indexOf(to) + toLength + 1;
            
            description = input.substring(command.length() + 1, indexOfFrom - fromLength - 1);
            // System.out.println("Description " + description);
            
            String start = input.substring(indexOfFrom, indexOfTo - to.length() - 2);
            String end = input.substring(indexOfTo, input.length());
            // System.out.println("Start: " + start);
            // System.out.println("End: " + end);

            task = new Event(description, false, start, end);
        }

        space(false, true);
        System.out.println(task);
        // System.out.println("This is the " + task.getClass().getName() + " task: " + task);

        int listLength = list.length;
        Task[] newList = Arrays.copyOf(list, listLength + 1);

        newList[listLength] = task;

        space(false, false);
        String messagePart2 = "Now you have " + newList.length;
        if (newList.length == 1) {
            messagePart2 += " task";
        } else {
            messagePart2 += " tasks";
        }
        messagePart2 += " in the list.";
        System.out.println(messagePart2);
        
        printLine();
        
        return newList;
    }

    // This function echos commands entered by the user, and exits when the user types the command bye.
    public static void echo() {
        Scanner in = new Scanner(System.in);

        // Get the input
        String input = in.nextLine();

        // Assume there will be no more than 100 tasks. Initialize an empty list of String array
        Task[] list = new Task[0];

        // Continue looping until input is "bye"
        while (checkEquals(input, BYE) == false) {

            // int taskCount = list.length;
            
            if (checkEquals(input, LIST)) {
                printList(list);
            } else if (checkInputStartsWith(input, MARK) || checkInputStartsWith(input, UNMARK)) {        // Check if input contains the command "mark" or "unmark"
                markDone(list, input);
            } else if (
                    checkInputStartsWith(input, TODO) ||
                    checkInputStartsWith(input, DEADLINE) || 
                    checkInputStartsWith(input, EVENT)
                ) {
                    list = addTask(list, input);
                }
            // else if (checkInputStartsWith(input, TODO)) {
            //     addTodo(list, input);
            // } else if (checkInputStartsWith(input, DEADLINE)) {
            // } else if (checkInputStartsWith(input, EVENT)) {
            // } 
            else {
                // TODO: Since we now have 3 types of tasks (Todo, Deadline, and Event) implemented in Level-4, it should be safe to assume that all tasks that should be added to the task list, should start with either "todo", or "deadline", or "event". Hence, if it just starts with the task description, then it should not be a valid task
                // list = addToList(list, input);
                printAddMessage(input);
            }

            System.lineSeparator();

            // Ask the user for the next input
            input = in.nextLine();
        }
    }

    public static void main(String[] args) {
        greet();
        echo();
        exit();
    }
}
