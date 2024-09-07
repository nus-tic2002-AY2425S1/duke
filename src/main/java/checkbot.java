import java.util.Scanner;

public class checkbot {
    public static String horizontalLine = "--------------------------------------";
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;
    public static Scanner in = new Scanner(System.in);

    public static String readInput() {
        String input;
        input = in.nextLine();
        return input;
    }

    public static void printHello() {
        System.out.println(horizontalLine + System.lineSeparator() +
                "Hello! I'm checkbot." + System.lineSeparator() +
                "What can I do for you? :)" + System.lineSeparator() +
                horizontalLine);
    }

    public static void printExit() {
        System.out.println(horizontalLine + System.lineSeparator() +
                "Bye. Hope to see you again soon!" + System.lineSeparator() +
                horizontalLine);
    }

    public static void printNotFound() {
        System.out.println(horizontalLine + System.lineSeparator() +
                "Sorry, I didn't get that. :(" + System.lineSeparator() +
                "Try starting a task with \"todo\", \"deadline\", or \"event\"." + System.lineSeparator() +
                "To see the full task list, type \"list\"." + System.lineSeparator() +
                "To end this session, type \"bye\"." + System.lineSeparator() +
                horizontalLine);
    }

    public static void addTask(String input) {
        String taskType = input.split(" ",2)[0].toLowerCase();
        // TODO: ArrayIndexOutOfBoundsException - task is empty
//        String taskDetails = input.split(" ",2)[1];

        switch (taskType) {
            case "todo":
                addTodo(input.split(" ",2)[1]);
                break;
            case "deadline":
                addDeadline(input.split(" ",2)[1]);
                break;
            case "event":
                addEvent(input.split(" ",2)[1]);
                break;
            default:
                printNotFound();
                break;
        }
    }

    public static void addTodo(String input){
        Todo task = new Todo(input);
        tasks[taskCount] = task;
        echoTask(taskCount);
        taskCount++;
    }

    public static void addDeadline(String input){
        // TODO: ArrayIndexOutOfBoundsException - absence of "/by"
        String description = input.split("/by")[0].trim();
        String dueDateTime = input.split("/by")[1].trim();

        Deadline task = new Deadline(description, dueDateTime);
        tasks[taskCount] = task;
        echoTask(taskCount);
        taskCount++;
    }

    public static void addEvent(String input){
        // TODO: StringIndexOutOfBoundsException - absence of "/from" and "/to"
        int idxOfFrom = input.indexOf("/from");
        int idxOfTo = input.indexOf("/to");
        String description = input.substring(0, idxOfFrom-1).trim();
        String startDateTime = input.substring(idxOfFrom+6, idxOfTo-1).trim();
        String endDateTime = input.substring(idxOfTo+4);

        Event task = new Event(description, startDateTime, endDateTime);
        tasks[taskCount] = task;
        echoTask(taskCount);
        taskCount++;
    }

    public static void echoTask(int taskIdx) {
        System.out.println(horizontalLine + System.lineSeparator() +
                "Got it! I've added this task:" + System.lineSeparator() +
                "  " + tasks[taskIdx].getListView() + System.lineSeparator() +
                "Now you have " + (taskIdx+1) + " task(s) in the list." + System.lineSeparator() +
                horizontalLine);
    }

    public static void printTasks() {
        System.out.println(horizontalLine);
        System.out.println("Here are the task(s) in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i+1 + ". " + tasks[i].getListView());
        }
        System.out.println(horizontalLine);
    }

    public static void markTask(Task task) {
        task.setDone(true);
        System.out.println(horizontalLine + System.lineSeparator() +
                "Nice! I've marked this task as done: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                horizontalLine);
    }

    public static void unmarkTask(Task task) {
        task.setDone(false);
        System.out.println(horizontalLine + System.lineSeparator() +
                "Okay, I've marked this task as not done yet: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                horizontalLine);
    }

    public static void setStatus(String  input) {
        // TODO: ArrayIndexOutOfBoundsException - no number indicated
        String action = input.split(" ")[0].toLowerCase();
        // TODO: NumberFormatException - number not numeric digits
        // TODO: ArrayIndexOutOfBoundsException - task number <= 0
        int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;

        // TODO: NullPointerException - task number > taskCount
        switch (action) {
            case "mark":
                markTask(tasks[taskIdx]);
                break;
            case "unmark":
                unmarkTask(tasks[taskIdx]);
                break;
            default:
                printNotFound();
                break;
        }
    }

    public static void main(String[] args) {
        printHello();
        boolean goToExit = false;

        do {
            String input = readInput();
            String keyword = input.split(" ")[0].toLowerCase();

            switch (keyword) {
                case "bye":
                    printExit();
                    goToExit = true;
                    break;
                case "list":
                    printTasks();
                    break;
                case "mark":
                    // fallthrough
                case "unmark":
                    setStatus(input);
                    break;
                default:
                    addTask(input);
                    break;
            }
        } while (!goToExit);
    }
}
