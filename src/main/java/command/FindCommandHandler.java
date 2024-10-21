package command;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

import static output.OutputHandler.printList;

/**
 * Handle find command
 */
public class FindCommandHandler implements CommandHandler{
    /**
     * Takes in userInput and list of task and print list of task that matches user input substring
     * @param userInput User input in String
     * @param tasks The list of tasks current available
     */
    @Override
    public void handle(String userInput, List<Task> tasks){
        String[] parts = userInput.split(" ", 2);
        List<Task> findList = new ArrayList<>();
        for(Task task : tasks){
            if(task.getDescription().contains(parts[1].trim())) {
                findList.add(task);
            }
        }
        printList(findList);
    }
}
