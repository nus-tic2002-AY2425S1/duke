package checkbot.ui;

import checkbot.task.Task;
import checkbot.task.TaskList;
import checkbot.utils.Messages;

import java.time.LocalDateTime;
import java.util.Scanner;

public class TextUi {
    public static Scanner scanInput = new Scanner(System.in);

    public static String readInput() {
        String input;
        input = scanInput.nextLine();
        return input;
    }

    public static void printHello() {
        System.out.println(Messages.HELLO);
    }

    public static void printExit() {
        System.out.println(Messages.EXIT);
    }

    public static void printHelp() {
        System.out.println(Messages.HELP);
    }

    public static void printCommandNotFound() {
        System.out.println(Messages.COMMAND_NOT_FOUND);
    }

    public static void printEmptyDescription() {
        System.out.println(Messages.EMPTY_DESCRIPTION);
    }

    public static void printEmptyTime() {
        System.out.println(Messages.EMPTY_TIME);
    }

    /**
     * Takes in a Task type and prints confirmation of task addition.
     *
     * @param task Added task
     */
    public static void echoAddTask(Task task) {
        System.out.println(Messages.DIVIDER + System.lineSeparator() +
                "Got it! I've added this task:" + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                "Now you have " + TaskList.tasks.size() + " task(s) in the list." + System.lineSeparator() +
                Messages.DIVIDER);
    }

    /**
     * Takes in a task type and prints confirmation of priority change.
     *
     * @param task Task with changed priority
     */
    public static void echoRankTask(Task task) {
        System.out.println(Messages.DIVIDER + System.lineSeparator() +
                "Got it! I've changed the priority of this task to: " +
                task.getPriorityString() + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                Messages.DIVIDER);
    }

    /**
     * Prints all tasks in TaskList.tasks.
     */
    public static void printTasks() {
        System.out.println(Messages.DIVIDER);
        System.out.println("Here are the task(s) in your list:");
        for (Task task : TaskList.tasks) {
            System.out.println(TaskList.tasks.indexOf(task)+1 + ". " + task.getListView());
        }
        System.out.println(Messages.DIVIDER);
    }

    /**
     * Prints a list of tasks with description containing input.
     *
     * @param input String to match with
     */
    public static void printMatchingTasks(String input) {
        boolean isFound = false;

        System.out.println(Messages.DIVIDER);
        System.out.println("Here are the matching task(s) in your list:");

        for (Task task : TaskList.tasks) {
            if (task.getDescription().contains(input)) {
                System.out.println(TaskList.tasks.indexOf(task)+1 + ". " + task.getListView());
                isFound = true;
            }
        }

        if (!isFound) {
            System.out.println("Sorry, there are no matching tasks.");
        }

        System.out.println(Messages.DIVIDER);
    }
    
    /**
     * Takes in LocalDateTime object and converts into string for UI list view.
     * E.g. of output: 26 OCTOBER 2024, 2:06AM
     *
     * @param dateTime LocalDateTime object
     * @return String type dateTime in DD MONTH YYYY, HH:MM(AM/PM)
     */
    public static String printDateTime(LocalDateTime dateTime) {
        int day = dateTime.getDayOfMonth();
        String month = dateTime.getMonth().toString();
        int year = dateTime.getYear();
        String minute = String.format("%02d", dateTime.getMinute());
        int hour;
        String meridiem;

        if (dateTime.getHour() == 0) {
            hour = 12;
            meridiem = "AM";
        } else if (dateTime.getHour() == 12) {
            hour = 12;
            meridiem = "PM";
        } else if (dateTime.getHour() > 12) {
            hour = dateTime.getHour() - 12;
            meridiem = "PM";
        } else {
            hour = dateTime.getHour();
            meridiem = "AM";
        }

        return day + " " + month + " " + year + ", " + hour + ":" + minute + meridiem;
    }
}
