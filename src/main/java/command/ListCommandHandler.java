package command;

import tasks.Task;
import java.util.List;
import static output.OutputHandler.printList;

/**
 * Handles the listing of tasks based on user inputs
 */
public class ListCommandHandler implements CommandHandler{
    /**
     * Handle user input and print list of tasks
     * @param userInput User input in String
     * @param tasks List of tasks objects
     */
    @Override
    public void handle(String userInput, List<Task> tasks){
        printList(tasks);
    }
}
