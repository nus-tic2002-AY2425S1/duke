import java.util.Scanner;

public class KLbot {
    private static final String botName = "KLbot";
    private static final Scanner in = new Scanner(System.in);
    private static TaskList[] userList = new TaskList[100];
    private static int listCounter = 0;

    public static void main(String[] args) {
        greetUser();
        botLoop();
        sayGoodbye();
        in.close();
    }

    private static void greetUser() {
        printLine();
        System.out.println(" Hey there! I'm " + botName + ", your cheerful helper buddy! \uD83D\uDE0A");
        System.out.println(" To mark a task as complete or undo it, just type 'mark' or 'unmark' followed by the task number. For example: 'mark 1'");
        System.out.println(" What can I do for you today? (Type 'bye' when you're ready to go!)");
        printLine();
    }

    private static void botLoop() {
        while (true) {
            String userInput = in.nextLine();
            if (isExit(userInput)) {
                break;
            } else if (userInput.startsWith("mark")) {
                int taskIndex = parseTaskIndex(userInput);
                markTaskAsCompleted(taskIndex);
            } else if (userInput.startsWith("unmark")) {
                int taskIndex = parseTaskIndex(userInput);
                markTaskAsIncomplete(taskIndex);
            } else if (isShowList(userInput)) {
                displayTaskList();
            } else {
                addTask(userInput);
            }
        }
    }

    private static boolean isExit(String userInput) {
        String userExit = "bye";
        return userInput.equalsIgnoreCase(userExit);
    }

    private static boolean isShowList(String userInput) {
        String showList = "list";
        return userInput.equalsIgnoreCase(showList);
    }

    private static int parseTaskIndex(String userInput) {
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid task number. Please try again.");
            return -1;
        }
    }

    private static void markTaskAsCompleted(int taskIndex) {
        if (taskIndexIsValid(taskIndex)) {
            userList[taskIndex].markAsCompleted();
            printLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(userList[taskIndex]);
            printLine();
        }
    }

    private static void markTaskAsIncomplete(int taskIndex) {
        if (taskIndexIsValid(taskIndex)) {
            userList[taskIndex].markAsIncomplete();
            printLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(userList[taskIndex]);
            printLine();
        }
    }

    private static boolean taskIndexIsValid(int taskIndex) {
        if (taskIndex == -1) return false;
        if (listCounter == 0) {
            System.out.println("It looks like your task list is empty right now. Please add some tasks, and I'll be happy to help you mark or unmark them!");
            return false;
        }
        if (taskIndex < 0 || taskIndex >= listCounter) {
            System.out.println("Task number out of range. Please enter a valid task number.");
            return false;
        }
        return true;
    }

    private static void addTask(String userInput) {
        if (userInput.isBlank()) {
            System.out.println("Oops! It looks like you didn’t enter anything. Please type something to continue.");
        } else if (listCounter < userList.length) {
            printLine();
            System.out.println("Got it! I’ve added  \"" + userInput + "\" to the list!\uD83D\uDE0A");
            System.out.println("Is there anything else I can help you with? Just let me know!");
            printLine();
            userList[listCounter] = new TaskList(userInput);
            listCounter++;
        } else {
            System.out.println("Task list is full! Cannot add more tasks.");
        }
    }

    public static void displayTaskList() {
        printLine();

        for (int i = 0; i < listCounter; i++) {
            int listNum = i + 1;
            System.out.println("\t" + listNum + "." + userList[i]);
        }

        printLine();
    }

    private static void sayGoodbye() {
        printLine();
        System.out.println("Aww, you're leaving? It was so nice chatting with you! Take care and see you soon! \uD83D\uDC96");
        printLine();
    }

    private static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
