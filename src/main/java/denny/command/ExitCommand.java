package denny.command;

import denny.task.TaskList;
import denny.task.Task;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;

/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * @param tasks The current list of tasks
     * @param ui The user interface for displaying output
     * @param storage The storage manager for persisting changes
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage();
    }

    /**
     * Indicates that this command should exit the application.
     * @return true, as this command should always exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
