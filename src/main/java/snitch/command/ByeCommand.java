package snitch.command;

import snitch.task.TaskList;
import snitch.Ui;
import snitch.Storage;

/**
 * Represents a command to exit the Snitch chatbot.
 * Displays a goodbye message to the user.
 */
public class ByeCommand implements Command {

    /**
     * Executes the ByeCommand by displaying a goodbye message.
     *
     * @param tasks   The TaskList containing the user's tasks (not used in this command).
     * @param ui      The Ui instance for interacting with the user.
     * @param storage The Storage instance (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }
}