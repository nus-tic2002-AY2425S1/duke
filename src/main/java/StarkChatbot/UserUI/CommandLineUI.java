package StarkChatbot.UserUI;

import StarkChatbot.TaskManager.Task;
import StarkChatbot.TaskManager.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

import static StarkChatbot.UserUI.InputParser.userInputParser;

public class CommandLineUI {

    private ArrayList<Task> tasks;

    public CommandLineUI(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private void lineBreak() {
        System.out.println("------------------------------------------------------");
    }

    private void welcomeMsg() {
        lineBreak();
        System.out.println("Hello! I'm Stark \n" +
                "What can I do for you?");
        lineBreak();
    }

    private void byeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
    }


    public ArrayList<Task> readUserInputs() {
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
