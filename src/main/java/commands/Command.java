package commands;

import exception.CommandException;
import exception.StorageOperationException;
import exception.TaskException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an abstract command in the application.
 * 
 * <p>
 * This class serves as a base for specific command implementations. 
 * Each command can execute a certain action on a given task list, user interface, and storage. 
 * </p>
 */
public abstract class Command {

    /**
     * Constructs a Command instance.
     */
    protected Command() {
    }

    /**
     * Executes the command with the provided task list, user interface, and storage.
     * @param taskList The list of tasks to operate on.
     * @param ui The user interface to handle interactions with the user.
     * @param storage The storage manager to handle interactions with (reading from and writing to) the task file.
     * 
     * @throws CommandException if an error occurs during command execution.
     * @throws StorageOperationException if an error occurs while accessing (loading tasks from / saving tasks from) storage.
     * @throws TaskException if an error related to the task occurs.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws CommandException, StorageOperationException;

    /**
     * Indicates whether the command is a bye command, i.e. a command that terminates the application.
     * 
     * @return true if the command signals the application to exit; false otherwise.
     */
    public boolean isBye() {
        return false;
    }
}
