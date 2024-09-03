import java.util.Scanner;

public class checkbot {
    public static String horizontalLine = "--------------------------------------";
    public static Task[] tasks = new Task[100];
    public static int taskCount = 0;

    public static String readInput() {
        String input;
        Scanner in = new Scanner(System.in);
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

    public static void addTask(String input) {
        String taskType = input.split(" ")[0];

        if (taskType.equalsIgnoreCase("todo")) {
            addTodo(input.substring(5));
        } else if (taskType.equalsIgnoreCase("deadline")) {
            addDeadline(input.substring(9));
        } else if (taskType.equalsIgnoreCase("event")) {
            addEvent(input.substring(6));
        }
        else {
            // TODO: Implement exception
            System.out.println(horizontalLine + System.lineSeparator() +
                    "Sorry, I didn't get that. :(" + System.lineSeparator() +
                    "Try starting a task with \"todo\", \"deadline\", or \"event\"." + System.lineSeparator() +
                    "To see the full task list, type \"list\"." + System.lineSeparator() +
                    "To end this session, type \"bye\"." + System.lineSeparator() +
                    horizontalLine);
            return;
        }

        System.out.println(horizontalLine + System.lineSeparator() +
                "Got it! I've added this task:" + System.lineSeparator() +
                "  " + tasks[taskCount].getListView() + System.lineSeparator() +
                "Now you have " + (taskCount+1) + " task(s) in the list." + System.lineSeparator() +
                horizontalLine);

        taskCount++;
    }

    public static void addTodo(String input){
        // TODO: exception - input is empty
        Todo task = new Todo(input);
        tasks[taskCount] = task;
    }

    public static void addDeadline(String input){
        // TODO: exception - absence of "/by"
        // TODO: exception - input is empty
        String description = input.split("/by")[0].trim();
        String dueDateTime = input.split("/by")[1].trim();

        Deadline task = new Deadline(description, dueDateTime);
        tasks[taskCount] = task;
    }

    public static void addEvent(String input){
        // TODO: exception - absence of "/from" and "/to"
        // TODO: exception - input is empty
        int idxOfFrom = input.indexOf("/from");
        int idxOfTo = input.indexOf("/to");
        String description = input.substring(0, idxOfFrom-1).trim();
        String startDateTime = input.substring(idxOfFrom+6, idxOfTo-1).trim();
        String endDateTime = input.substring(idxOfTo+4);

        Event task = new Event(description, startDateTime, endDateTime);
        tasks[taskCount] = task;
    }

    public static void printTasks() {
        System.out.println(horizontalLine);
        System.out.println("Here are the task(s) in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(i+1 + ". " + tasks[i].getListView());
        }
        System.out.println(horizontalLine);
    }

    public static void setStatus(String  input) {
        // TODO: exception - taskNum is 0 or > taskCount
        String action = input.split(" ")[0];
        int taskIdx = Integer.parseInt(input.split(" ")[1]) - 1;

        if (action.equalsIgnoreCase("mark")) {
            tasks[taskIdx].setDone(true);
            System.out.println(horizontalLine + System.lineSeparator() +
                    "Nice! I've marked this task as done: " + System.lineSeparator() +
                    "  " + tasks[taskIdx].getListView() + System.lineSeparator() +
                    horizontalLine);
        } else {
            tasks[taskIdx].setDone(false);
            System.out.println(horizontalLine + System.lineSeparator() +
                    "Okay, I've marked this task as not done yet: " + System.lineSeparator() +
                    "  " + tasks[taskIdx].getListView() + System.lineSeparator() +
                    horizontalLine);
        }
    }

    public static void main(String[] args) {
        printHello();

        while (true) {
            String input = readInput();

            if (input.trim().equalsIgnoreCase("bye")) {
                printExit();
                break;
            } else if (input.trim().equalsIgnoreCase("list")) {
                printTasks();
            } else if (input.toLowerCase().startsWith("mark") || input.toLowerCase().startsWith("unmark")) {
                setStatus(input);
            } else {
                addTask(input);
            }
        }
    }
}
