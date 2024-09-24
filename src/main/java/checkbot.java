import java.util.ArrayList;
import java.util.Scanner;

public class checkbot {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static Scanner in = new Scanner(System.in);

    public static String readInput() {
        String input;
        input = in.nextLine();
        return input;
    }

    public static void printHello() {
        System.out.println(StringHelper.hello);
    }

    public static void printExit() {
        System.out.println(StringHelper.exit);
    }

    public static void printCommandNotFound() {
        System.out.println(StringHelper.commandNotFound);
    }

    public static void printEmptyDescription() {
        System.out.println(StringHelper.emptyDescription);
    }

    public static void printEmptyTime() {
        System.out.println(StringHelper.emptyTime);
    }

    public static void addTask(String input) throws EmptyInputException, EmptyTimeException {
        String[] taskArray = input.split(" ",2);
        if (taskArray.length < 2) {
            throw new EmptyInputException();
        }
        String taskType = taskArray[0].toLowerCase();
        String taskDetails = taskArray[1];

        switch (taskType) {
            case "todo":
                addTodo(taskDetails);
                break;
            case "deadline":
                try{
                    addDeadline(taskDetails);
                    break;
                } catch (CommandNotFoundException e) {
                    System.out.println(StringHelper.deadlineError);
                    break;
                }
            case "event":
                try{
                    addEvent(taskDetails);
                    break;
                } catch (CommandNotFoundException e) {
                    System.out.println(StringHelper.eventError);
                    break;
                }
            default:
                // do nothing
        }
    }

    public static void addTodo(String input){
        Todo task = new Todo(input);
        tasks.add(task);
        echoTask(task);
    }

    public static void addDeadline(String input) throws EmptyInputException, EmptyTimeException, CommandNotFoundException {
        // input format: <task> /by <datetime>
        if (!input.contains("/by")){
            throw new CommandNotFoundException();
        }
        String[] deadlineArray = input.split("/by",2);
        String description = deadlineArray[0].trim();
        String dueDateTime = deadlineArray[1].trim();
        if (description.isEmpty()) {
            throw new EmptyInputException();
        }
        if (dueDateTime.isEmpty()) {
            throw new EmptyTimeException();
        }

        Deadline task = new Deadline(description, dueDateTime);
        tasks.add(task);
        echoTask(task);
    }

    public static void addEvent(String input) throws EmptyInputException, EmptyTimeException, CommandNotFoundException {
        // input format: <event> /from <datetime> /to <datetime>
        if (!input.contains("/from") || !input.contains("/to")){
            throw new CommandNotFoundException();
        }
        String[] eventArray = input.split("/from",2);
        String[] dateTimeArray = eventArray[1].split("/to",2);
        String description = eventArray[0].trim();
        if (description.isEmpty()) {
            throw new EmptyInputException();
        }
        String startDateTime = dateTimeArray[0].trim();
        String endDateTime = dateTimeArray[1].trim();
        if (startDateTime.isEmpty() || endDateTime.isEmpty()) {
            throw new EmptyTimeException();
        }

        Event task = new Event(description, startDateTime, endDateTime);
        tasks.add(task);
        echoTask(task);
    }

    public static void echoTask(Task task) {
        System.out.println(StringHelper.outputLine + System.lineSeparator() +
                "Got it! I've added this task:" + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                "Now you have " + tasks.size() + " task(s) in the list." + System.lineSeparator() +
                StringHelper.outputLine);
    }

    public static void printTasks() {
        System.out.println(StringHelper.outputLine);
        System.out.println("Here are the task(s) in your list:");
        for (Task task : tasks) {
            System.out.println(tasks.indexOf(task)+1 + ". " + task.getListView());
        }
        System.out.println(StringHelper.outputLine);
    }

    public static void markTask(Task task) {
        task.setDone(true);
        System.out.println(StringHelper.outputLine + System.lineSeparator() +
                "Nice! I've marked this task as done: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                StringHelper.outputLine);
    }

    public static void unmarkTask(Task task) {
        task.setDone(false);
        System.out.println(StringHelper.outputLine + System.lineSeparator() +
                "Okay, I've marked this task as not done yet: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                StringHelper.outputLine);
    }

    public static void deleteTask(Task task) {
        tasks.remove(task);
        System.out.println(StringHelper.outputLine + System.lineSeparator() +
                "Got it. I've removed this task: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                "Now you have " + tasks.size() + " task(s) in the list." + System.lineSeparator() +
                StringHelper.outputLine);

    }

    public static void setStatus(String  input) throws EmptyInputException {
        String[] setStatusArray = input.split(" ", 2);
        if (setStatusArray.length < 2) {
            throw new EmptyInputException();
        }
        String action = setStatusArray[0].toLowerCase();
        int taskIdx = Integer.parseInt(setStatusArray[1]) - 1;

        switch (action) {
            case "mark":
                markTask(tasks.get(taskIdx));
                break;
            case "unmark":
                unmarkTask(tasks.get(taskIdx));
                break;
            case "delete":
                deleteTask(tasks.get(taskIdx));
                break;
            default:
                printCommandNotFound();
                break;
        }
    }

    public static void main(String[] args) {
        printHello();
        boolean goToExit = false;

        do {
            String input = readInput().trim();
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
                    // fallthrough
                case "delete":
                    try {
                        setStatus(input);
                        break;
                    } catch (EmptyInputException e) {
                        System.out.println(StringHelper.emptyNumber);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println(StringHelper.nonIntegerNumber);
                        break;
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println(StringHelper.invalidTaskNumber);
                        break;
                    }
                case "todo":
                    // fallthrough
                case "deadline":
                    // fallthrough
                case "event":
                    try {
                        addTask(input);
                        break;
                    } catch (EmptyInputException e) {
                        printEmptyDescription();
                        break;
                    } catch (EmptyTimeException e) {
                        printEmptyTime();
                        break;
                    }
                default:
                    printCommandNotFound();
            }
        } while (!goToExit);
    }
}
