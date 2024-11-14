package starkchatbot.userui;

import starkchatbot.taskmanager.Task;
import starkchatbot.taskmanager.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

import static starkchatbot.userui.InputParser.userInputParser;

public class CommandLineUI {

    private final ArrayList<Task> tasks;

    public CommandLineUI(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private void lineBreak() {
        System.out.println("------------------------------------------------------");
    }

    private void welcomeMsg() {
        lineBreak();
        System.out.println("Hello! I'm StarkChatbot.UserUI.Stark \n" +
                "What can I do for you?");
        lineBreak();
    }

    private void byeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
    }


    public ArrayList<Task> readUserInputs() {
        lineBreak();
        welcomeMsg();
        TaskList taskList = new TaskList(tasks);
        Scanner inputQuery = new Scanner(System.in);

        while (true) {
            try {
                String query = inputQuery.nextLine().trim();

                lineBreak();

                if (query.equalsIgnoreCase("bye")) {
                    break;
                } else {
                    userInputParser(taskList, query);
                }

            } catch (StarkException.InvalidDescriptionException |
                     StarkException.InvalidTaskException |
                     StarkException.InvalidCommandException |
                     StarkException.InvalidIndexException e) {
                System.out.println(e.getMessage());
            }
            lineBreak();
        }
        byeMsg();
        return taskList.getTasks();
    }

}
