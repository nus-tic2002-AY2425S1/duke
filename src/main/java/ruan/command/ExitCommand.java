package ruan.command;

import ruan.task.*;
import ruan.ui.Ui;
import ruan.storage.Storage;
import ruan.exception.*;

/**
 * Represents command to exit the program
 * Displays a goodbye message and saves the current task list before exiting
 */

public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying a goodbye message and saving the current task list to storage
     * @param tasks TaskList containing the current tasks.
     * @param ui Ui instance used for interacting with the user/displaying message
     * @param storage Storage instance used for saving tasks to the file
     * @throws RuanException If saving the tasks to the file fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RuanException {
        String[] message = {"Bye. Hope to see you again soon!"};
        ui.printMessage(message);
        storage.saveTasks(tasks.getTasks());
    }

    /**
     * Indicates that this command should exit the program
     * @return True, since this command is for exiting the program
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
