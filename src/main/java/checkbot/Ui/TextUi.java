package checkbot.Ui;

import checkbot.Task.*;
import checkbot.Utils.*;

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
        System.out.println(Messages.hello);
    }

    public static void printExit() {
        System.out.println(Messages.exit);
    }

    public static void printCommandNotFound() {
        System.out.println(Messages.commandNotFound);
    }

    public static void printEmptyDescription() {
        System.out.println(Messages.emptyDescription);
    }

    public static void printEmptyTime() {
        System.out.println(Messages.emptyTime);
    }

    /**
     * Takes in a Task type and prints confirmation of task addition in UI.
     *
     * @param task
     */
    public static void echoTask(Task task) {
        System.out.println(Messages.divider + System.lineSeparator() +
                "Got it! I've added this task:" + System.lineSeparator() +
                "  " + task.getListView() + System.lineSeparator() +
                "Now you have " + TaskList.tasks.size() + " task(s) in the list." + System.lineSeparator() +
                Messages.divider);
    }

    public static void printTasks() {
        System.out.println(Messages.divider);
        System.out.println("Here are the task(s) in your list:");
        for (Task task : TaskList.tasks) {
            System.out.println(TaskList.tasks.indexOf(task)+1 + ". " + task.getListView());
        }
        System.out.println(Messages.divider);
    }

    /**
     * Takes in LocalDateTime object and converts into string for UI list view.
     * E.g. of output: 26 OCTOBER 2024, 2:06AM
     *
     * @param dateTime
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
