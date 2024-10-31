package wkduke.command;

import wkduke.exception.CommandOperationException;
import wkduke.exception.StorageOperationException;
import wkduke.storage.Storage;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

/**
 * Represents an executable command in the application.
 * This is an abstract base class to be extended by specific command types.
 */
public abstract class Command {
    /**
     * Constructs a Command instance.
     */
    protected Command() {
    }

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param taskList The task list to operate on.
     * @param ui       The user interface to interact with the user.
     * @param storage  The storage to save or retrieve data.
     * @throws StorageOperationException if there is an error with storage operations.
     * @throws CommandOperationException if there is an error with command execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws StorageOperationException, CommandOperationException;

    /**
     * Indicates whether this command is an exit command.
     *
     * @return {@code true} if this command should terminate the application; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}