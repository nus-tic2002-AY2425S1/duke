package denny.command;

import denny.task.TaskList;
import denny.ui.Ui;
import denny.storage.Storage;
import denny.exception.DennyException;

/**
 * Abstract base class for all commands in the application.
 * Implements the Command pattern for executing user actions.
 */
public abstract class Command {
    /**
     * Executes the command with the given context.
     * @param tasks The current list of tasks
     * @param ui The user interface for displaying output
     * @param storage The storage manager for persisting changes
     * @throws DennyException if there is an error executing the command
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DennyException;

    /**
     * Checks if this command should exit the application.
     * @return true if the application should exit after this command, false otherwise
     */
    public boolean isExit() {
        return false;
    }
}
