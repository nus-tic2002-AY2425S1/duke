package Chad.Command;

import Chad.Exception.ChadException;
import Chad.Storage.Storage;
import Chad.TaskList.TaskList;
import Chad.Ui.Ui;

public abstract class Command {
    
/**
 * Executes a specific action involving the provided task list, user interface, 
 * and storage. The exact behavior is defined in subclasses implementing this method.
 *
 * @param tasks   The task list that this method operates on.
 * @param ui      The user interface component used for interacting with the user.
 * @param storage The storage mechanism for persisting task data.
 * @throws ChadException If an error occurs during execution.
 */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChadException;

    public boolean isExit() {
        return false;
    }
}