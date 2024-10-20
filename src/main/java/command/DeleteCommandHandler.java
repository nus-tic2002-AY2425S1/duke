package parser;

import exception.DukeException;
import tasks.Task;

import java.util.List;

import static parser.Utility.isValidIndex;
import static parser.Utility.parseTaskIndex;
import static output.OutputHandler.printDeleteItem;

public class DeleteCommandHandler implements CommandHandler{
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
