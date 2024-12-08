package ruan.command;

import ruan.task.*;
import ruan.ui.Ui;
import ruan.storage.Storage;
import ruan.exception.*;

/**
 * Represents an abstract command that can be executed
 * All commands should extend from this class and provide an implementation of the execute method
 */

public abstract class Command {

    /**
     * Executes the command with the provided TaskList, Ui, and Storage.
     * @param tasks TaskList where tasks can be manipulated
     * @param ui Ui object for user interaction/displaying message
     * @param storage Storage object used to save or load tasks
     * @throws RuanException If any issues occur during the execution of the command
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws RuanException;

     /**
     * Indicates whether this command should exit the application.
     * By default, returns false. Override to provide a different behavior.
     * @return True if the command should terminate the application
     */
    public boolean isExit() {
        return false;
    }
}





