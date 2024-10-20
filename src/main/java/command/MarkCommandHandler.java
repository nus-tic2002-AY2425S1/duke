package command;

import exception.DukeException;
import tasks.Task;
import java.util.List;
import static command.Utility.isValidIndex;
import static command.Utility.parseTaskIndex;
import static output.OutputHandler.printMarkDone;

/**
 * Handle the marking of tasks done based on user input
 */
public class MarkCommandHandler implements CommandHandler{
    /**
     * Handles user input to mark a task as done and perform update to TaskList
     * @param userInput User input in String
     * @param tasks The list of tasks to have modification based on the handler
     * @throws DukeException If the index of the task is invalid
     */
    @Override
    public void handle(String userInput, List<Task> tasks) throws DukeException{
        int taskIndex = parseTaskIndex(userInput);
        if(isValidIndex(taskIndex, tasks)){
            tasks.get(taskIndex).markDone();
            printMarkDone(taskIndex,tasks);
        }
        else {
            throw new DukeException("OOPS!! Task number is out of range!");
        }
    }
}
