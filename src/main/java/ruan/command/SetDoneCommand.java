package ruan.command;

import ruan.task.*;
import ruan.ui.Ui;
import ruan.storage.Storage;
import ruan.exception.*;

/**
 * Represents a command to update the completion status of a task
 * This class combines the function mark done & unmark done
 * while still adhering to the Single Responsibility Principle by only updating the task status
 */

public class SetDoneCommand extends Command {

    private int index;
    private boolean isDone;

    /**
     * Constructs SetDoneCommand with the specified index and completion status
     * @param index Index of the task to update
     * @param isDone True if the task is being marked as done
     */
    public SetDoneCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Executes the command by updating the completion status of a task in the task list
     * Displays a message confirming the updated status and saves the current task list to file
     * @param tasks TaskList containing the tasks
     * @param ui Ui instance used for interacting with the user/displaying message
     * @param storage Storage instance used for saving tasks to the file
     * @throws RuanException If the specified task index is invalid
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RuanException {
        try {
            tasks.setDone(index, isDone);
            String acknowledgeString = isDone ? "Nice! " : "OK, ";
            String actionString = isDone ? "marked as done" : "marked as not done";
            String[] message = {
                acknowledgeString + "I've " + actionString + " the following task:",
                "  " + tasks.getTaskDescription(index)
            };
            ui.printMessage(message);

            storage.saveTasks(tasks.getTasks());

        } catch (IndexOutOfBoundsException e) {
            throw new RuanException(ErrorType.INVALID_TASK_NUMBER);
        }
    }

}
