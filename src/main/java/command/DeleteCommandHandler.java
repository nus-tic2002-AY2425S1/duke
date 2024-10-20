package command;

import exception.DukeException;
import tasks.Task;

import java.util.List;

import static command.Utility.isValidIndex;
import static command.Utility.parseTaskIndex;
import static output.OutputHandler.printDeleteItem;

/**
 *  Handles deletion of tasks based on user input.
 */
public class DeleteCommandHandler implements CommandHandler{
    /**
     * Handle user input to delete a task and updates the TaskList.
     * @param userInput User input in String
     * @param tasks List of tasks objects
     * @throws DukeException If the index of the task is invalid
     */
    @Override
    public void handle(String userInput, List<Task> tasks) throws DukeException {
        int taskIndex = parseTaskIndex(userInput);
        if (isValidIndex(taskIndex, tasks)) {
            printDeleteItem(taskIndex,tasks);
            tasks.remove(taskIndex);
        } else {
            throw new DukeException("OOPS!! Task number is out of range!");
        }
    }
}
