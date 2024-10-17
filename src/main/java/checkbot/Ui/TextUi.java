package checkbot.Ui;

import java.util.Scanner;

import checkbot.Task.*;
import checkbot.Utils.*;

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
}
