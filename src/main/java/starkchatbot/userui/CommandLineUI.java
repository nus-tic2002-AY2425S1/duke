package starkchatbot.userui;

import starkchatbot.taskmanager.Task;
import starkchatbot.taskmanager.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

import static starkchatbot.userui.InputParser.userInputParser;

public class CommandLineUI {

    private final ArrayList<Task> tasks;

    /**
     * Initializes the CommandLineUI with a list of tasks.
     *
     * @param tasks the list of tasks to manage and display to the user.
     */
    public CommandLineUI(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private void lineBreak() {
        System.out.println("------------------------------------------------------");
    }

    private void welcomeMsg() {
        lineBreak();
        System.out.println("Hello! I'm Stark Chatbot.\n" +
                "What can I do for you?");
        lineBreak();
    }

    private void byeMsg() {
        System.out.println("Bye. Hope to see you again soon!");
        lineBreak();
    }


    /**
     * Reads user inputs, processes the commands, and manages the task lists based
     * on the inputs provided. Ends when the user enters "bye".
     *
     * @return the updated list of tasks after processing user inputs to Stark class.
     */
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
