package command;

import exception.DukeException;
import tasks.Task;

import java.util.List;

import static command.Utility.isValidIndex;
import static command.Utility.parseTaskIndex;
import static output.OutputHandler.printDeleteItem;
import static output.OutputHandler.printUpdateItem;
import static parser.TaskParser.parseUpdate;

/**
 *  Handles deletion of tasks based on user input.
 */
public class UpdateCommandHandler implements CommandHandler{
    /**
     * Handle user input to update a task
     * @param userInput User input in String
     * @param tasks List of tasks objects
     * @throws DukeException If the index of the task is invalid
     */
    @Override
    public void handle(String userInput, List<Task> tasks) throws DukeException {
        int taskIndex = parseTaskIndex(userInput);
        String updateString = parseUpdate(userInput);
        tasks.get(taskIndex).update(updateString);
        printUpdateItem(taskIndex,tasks);
    }
}
