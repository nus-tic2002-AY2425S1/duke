package commands;

import common.Constants;
import exception.CommandException;
import exception.StorageOperationException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Represents an abstract command in the application.
 * This class serves as a base for specific command implementations.
 * Each command can execute a certain action on a given task list, user interface, and storage.
 */
public abstract class Command {

    protected static final String SPACE = Constants.SPACE;
    protected static final String OPEN_ANGLE_BRACKET = Constants.OPEN_ANGLE_BRACKET;
    protected static final String CLOSE_ANGLE_BRACKET = Constants.CLOSE_ANGLE_BRACKET;

    /**
     * Executes the command with the provided task list, user interface, and storage.
     *
     * @param taskList The list of tasks to operate on.
     * @param ui       The user interface to handle interactions with the user.
     * @param storage  The storage manager to handle interactions with (reading from and writing to) the task file.
     * @throws CommandException          if an error occurs during command execution.
     * @throws StorageOperationException if an error occurs while accessing (loading / saving tasks from) storage.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage)
        throws CommandException, StorageOperationException;

    /**
     * Indicates whether the command is a bye command, i.e. a command that terminates the application.
     *
     * @return true if the command signals the application to exit; false otherwise.
     */
    public boolean isBye() {
        return false;
    }

    public void assertExecuteParams(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null : "Task list must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
    }
}
