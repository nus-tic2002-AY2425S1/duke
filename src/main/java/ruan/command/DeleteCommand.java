package ruan.command;

import ruan.task.*;
import ruan.ui.Ui;
import ruan.storage.Storage;
import ruan.exception.*;

/**
 * Represents a command to delete a task from the task list.
 */

public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs DeleteCommand with the specified index of the task to be deleted
     * @param index The index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing a task from the task list at the specified index
     * Displays a message confirming the task removal and updates the task list in storage
     * @param tasks TaskList from which the task will be removed
     * @param ui Ui instance used for interacting with the user/displaying message
     * @param storage Storage instance used for saving tasks to the file
     * @throws RuanException If the task index is out of bounds or if saving fails
     */
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
