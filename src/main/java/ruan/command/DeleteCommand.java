package ruan.command;

import ruan.task.*;
import ruan.ui.*;
import ruan.storage.*;
import ruan.exception.*;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RuanException {
        try {
            
            //get task description before deletion, to be display at message
            String taskDescription = tasks.getTaskDescription(index);
            tasks.deleteTask(index);

            String[] message = {
                "Noted. I've removed this task:",
                "  " + taskDescription,
                "Now you have " + tasks.size() + " tasks in the list."
            };
            ui.printMessage(message);

            // save latest task list to the file
            storage.saveTasks(tasks.getTasks());

        } catch (IndexOutOfBoundsException e) {
            throw new RuanException(ErrorType.INVALID_TASK_NUMBER);
        }
    }
    
}
