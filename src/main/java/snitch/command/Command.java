package snitch.command;

import snitch.task.TaskList;
import snitch.Ui;
import snitch.Storage;
import snitch.SnitchException;

/**
 * Represents a command that can be executed in the Snitch chatbot.
 * Each command performs a specific action, such as adding or deleting tasks.
 */
public interface Command {

    /**
     * Executes the command.
     *
     * @param tasks   The TaskList containing all current tasks.
     * @param ui      The Ui instance for interacting with the user.
     * @param storage The Storage instance for saving and loading tasks.
     * @throws SnitchException If an error occurs during command execution.
     */
    void execute(TaskList tasks, Ui ui, Storage storage) throws SnitchException;
}