package checkbot.Task;

import checkbot.Exception.*;
import checkbot.Parser.*;
import checkbot.Ui.TextUi;
import checkbot.Utils.Messages;

import java.time.DateTimeException;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static boolean addTask(String input)
            throws EmptyInputException,
            EmptyTimeException,
            DateTimeException,
            NumberFormatException {
        String[] taskArray = input.split(" ",2);
        if (taskArray.length < 2) {
            throw new EmptyInputException();
        }
        String taskType = taskArray[0].toLowerCase();
        String taskDetails = taskArray[1];

        switch (taskType) {
            case "todo":
                addTodo(taskDetails);
                return true;
            case "deadline":
                try{
                    addDeadline(taskDetails);
                    return true;
                } catch (CommandNotFoundException e) {
                    System.out.println(Messages.deadlineError);
                    break;
                }
            case "event":
                try{
                    addEvent(taskDetails);
                    return true;
                } catch (CommandNotFoundException e) {
                    System.out.println(Messages.eventError);
                    break;
                }
            default:
                // do nothing
        }
        return false;
    }

    public static void addTodo(String input){
        Todo task = new Todo(input);
        TaskList.tasks.add(task);
        TextUi.echoAddTask(task);
    }

    public static void addDeadline(String input) throws EmptyInputException, EmptyTimeException, CommandNotFoundException {
        // input format: <task> /by <DD/MM/YYYY HHMM(24H)>
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

        Deadline task = new Deadline(description, Parser.parseDateTime(dueDateTime));
        TaskList.tasks.add(task);
        TextUi.echoAddTask(task);
    }

    public static void addEvent(String input) throws EmptyInputException, EmptyTimeException, CommandNotFoundException {
        // input format: <event> /from <DD/MM/YYYY HHMM(24H)> /to <DD/MM/YYYY HHMM(24H)>
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

        Event task = new Event(description, Parser.parseDateTime(startDateTime), Parser.parseDateTime(endDateTime));
        TaskList.tasks.add(task);
        TextUi.echoAddTask(task);
    }

    public static void markTask(Task task) {
        task.setDone(true);
        System.out.println(Messages.divider + System.lineSeparator() +
                "Nice! I've marked this task as done: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                Messages.divider);
    }

    public static void unmarkTask(Task task) {
        task.setDone(false);
        System.out.println(Messages.divider + System.lineSeparator() +
                "Okay, I've marked this task as not done yet: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                Messages.divider);
    }

    public static void deleteTask(Task task) {
        TaskList.tasks.remove(task);
        System.out.println(Messages.divider + System.lineSeparator() +
                "Got it. I've removed this task: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                "Now you have " + TaskList.tasks.size() + " task(s) in the list." + System.lineSeparator() +
                Messages.divider);

    }

    public static void rankTask(Task task, String priority) throws CommandNotFoundException {
        switch (priority.toLowerCase()) {
            case "high":
                task.setPriority(TaskPriority.HIGH);
                break;
            case "medium":
                task.setPriority(TaskPriority.MEDIUM);
                break;
            case "low":
                task.setPriority(TaskPriority.LOW);
                break;
            case "none":
                task.setPriority(TaskPriority.NOT_SET);
                break;
            default:
                throw new CommandNotFoundException();
        }

        TextUi.echoRankTask(task);
    }

    public static void setStatus(String  input) throws EmptyInputException, CommandNotFoundException {
        String[] setStatusArray = input.split(" ");
        if (setStatusArray.length < 2) {
            throw new EmptyInputException();
        }
        String action = setStatusArray[0].toLowerCase();
        int taskIdx = Integer.parseInt(setStatusArray[1]) - 1;

        switch (action) {
            case "mark":
                markTask(TaskList.tasks.get(taskIdx));
                break;
            case "unmark":
                unmarkTask(TaskList.tasks.get(taskIdx));
                break;
            case "delete":
                deleteTask(TaskList.tasks.get(taskIdx));
                break;
            case "rank":
                if (setStatusArray.length < 3) {
                    throw new CommandNotFoundException();
                }
                String priority = setStatusArray[2];
                rankTask(TaskList.tasks.get(taskIdx), priority);
                break;
            default:
                // do nothing
        }
    }
}
