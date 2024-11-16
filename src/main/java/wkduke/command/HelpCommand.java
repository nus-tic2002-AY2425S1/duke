package wkduke.command;

import wkduke.common.Messages;
import wkduke.storage.Storage;
import wkduke.task.TaskList;
import wkduke.ui.Ui;

/**
 * Represents a command to display help information for all available commands.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    /**
     * Executes the help command by displaying the list of available commands to the user.
     *
     * @param taskList The task list (not used in this command).
     * @param ui       The user interface for displaying messages to the user.
     * @param storage  The storage being used (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert ui != null : "Precondition failed: 'ui' cannot be null";
        ui.printMessages(Messages.MESSAGE_AVAILABLE_COMMAND);
    }
}
