package checkbot.task;

import checkbot.exception.CommandNotFoundException;
import checkbot.exception.InvalidInputException;
import checkbot.parser.Parser;
import checkbot.ui.TextUi;
import checkbot.utils.Messages;

import java.time.DateTimeException;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds todo task.
     *
     * @param input Task description
     * @return Todo object
     */
    public static Todo addTodo(String input){
        Todo task = new Todo(input);
        TaskList.tasks.add(task);
        return task;
    }

    /**
     * Adds deadline task.
     *
     * @param input description /by datetime
     * @return Deadline object
     * @throws CommandNotFoundException Missing /by command
     * @throws InvalidInputException Empty description / time
     * @throws NumberFormatException Wrong format of datetime
     * @throws NullPointerException Wrong format of datetime
     * @throws DateTimeException Invalid datetime
     */
    public static Deadline addDeadline(String input)
            throws CommandNotFoundException, InvalidInputException,
            NumberFormatException, IndexOutOfBoundsException, DateTimeException {
        // input format: <task> /by <DD/MM/YYYY HHMM(24H)>
        if (!input.contains("/by")){
            throw new CommandNotFoundException(Messages.DEADLINE_ERROR);
        }

        String[] deadlineArray = input.split("/by",2);
        String description = deadlineArray[0].trim();
        String dueDateTime = deadlineArray[1].trim();

        if (description.isEmpty()) {
            throw new InvalidInputException(Messages.EMPTY_DESCRIPTION);
        }
        if (dueDateTime.isEmpty()) {
            throw new InvalidInputException(Messages.EMPTY_TIME);
        }

        Deadline task = new Deadline(description, Parser.parseDateTime(dueDateTime));
        TaskList.tasks.add(task);
        return task;
    }

    /**
     * Adds event task.
     *
     * @param input description /from datetime /to datetime
     * @return Event object
     * @throws CommandNotFoundException Missing /from or /to command
     * @throws InvalidInputException Empty description / time
     * @throws NumberFormatException Wrong format of datetime
     * @throws NullPointerException Wrong format of datetime
     * @throws DateTimeException Invalid datetime
     */
    public static Event addEvent(String input)
            throws CommandNotFoundException, InvalidInputException,
            NumberFormatException, IndexOutOfBoundsException, DateTimeException {
        // input format: <event> /from <DD/MM/YYYY HHMM(24H)> /to <DD/MM/YYYY HHMM(24H)>
        if (!input.contains("/from") || !input.contains("/to")){
            throw new CommandNotFoundException(Messages.EVENT_ERROR);
        }

        String[] eventArray = input.split("/from",2);
        String[] dateTimeArray = eventArray[1].split("/to",2);
        String description = eventArray[0].trim();

        if (description.isEmpty()) {
            throw new InvalidInputException(Messages.EMPTY_DESCRIPTION);
        }

        String startDateTime = dateTimeArray[0].trim();
        String endDateTime = dateTimeArray[1].trim();

        if (startDateTime.isEmpty() || endDateTime.isEmpty()) {
            throw new InvalidInputException(Messages.EMPTY_TIME);
        }

        Event task = new Event(description, Parser.parseDateTime(startDateTime), Parser.parseDateTime(endDateTime));
        TaskList.tasks.add(task);
        return task;
    }

    /**
     * Adds task into tasks.
     *
     * @param input task command
     * @return task object
     * @throws DateTimeException Invalid datetime
     * @throws NumberFormatException Wrong format of datetime
     * @throws NullPointerException Wrong format of datetime
     * @throws InvalidInputException Empty description
     */
    public static Task addTask(String input)
            throws DateTimeException, NumberFormatException, IndexOutOfBoundsException, InvalidInputException {
        String[] taskArray = input.split(" ",2);

        if (taskArray.length < 2) {
            throw new InvalidInputException(Messages.EMPTY_DESCRIPTION);
        }

        String taskType = taskArray[0].toLowerCase();
        String taskDetails = taskArray[1];

        try {
            switch (taskType) {
            case "todo":
                return addTodo(taskDetails);
            case "deadline":
                return addDeadline(taskDetails);
            case "event":
                return addEvent(taskDetails);
            default:
                System.out.println("Invalid task type: " + taskType);
            }
        } catch (CommandNotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Marks task as done. Prints confirmation message.
     */
    public static void markTask(Task task) {
        task.setDone(true);
        System.out.println(Messages.DIVIDER + System.lineSeparator() +
                "Nice! I've marked this task as done: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                Messages.DIVIDER);
    }

    /**
     * Marks task as not done. Prints confirmation message.
     */
    public static void unmarkTask(Task task) {
        task.setDone(false);
        System.out.println(Messages.DIVIDER + System.lineSeparator() +
                "Okay, I've marked this task as not done yet: " + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                Messages.DIVIDER);
    }

    /**
     * Deletes task from tasks. Prints confirmation message.
     */
    public static void deleteTask(Task task) {
        TaskList.tasks.remove(task);
        System.out.println(Messages.DIVIDER + System.lineSeparator() +
                "Got it. I've removed this task: " + System.lineSeparator() +
                "  " + task.getDescription() + System.lineSeparator() +
                "Now you have " + TaskList.tasks.size() + " task(s) in the list." + System.lineSeparator() +
                Messages.DIVIDER);

    }

    /**
     * Sets task's priority. Prints confirmation message.
     *
     * @param task Task to set priority
     * @param priority priority
     * @throws CommandNotFoundException Invalid priority
     */
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
            throw new CommandNotFoundException(Messages.INVALID_PRIORITY);
        }

        TextUi.echoRankTask(task);
    }

    /**
     * Sets status of task.
     *
     * @param input Command
     * @throws CommandNotFoundException Missing input for priority command
     * @throws IndexOutOfBoundsException Invalid task number
     * @throws InvalidInputException Empty task number
     */
    public static void setStatus(String  input)
            throws CommandNotFoundException, IndexOutOfBoundsException, InvalidInputException {
        String[] setStatusArray = input.split(" ");

        if (setStatusArray.length < 2) {
            throw new InvalidInputException(Messages.EMPTY_NUMBER);
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
                throw new CommandNotFoundException(Messages.COMMAND_NOT_FOUND);
            }
            String priority = setStatusArray[2];
            rankTask(TaskList.tasks.get(taskIdx), priority);
            break;
        default:
            // do nothing
        }
    }
}
