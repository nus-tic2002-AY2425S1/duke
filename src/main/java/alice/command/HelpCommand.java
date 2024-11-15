package alice.command;

import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * <h1>Help Command</h1>
 * The HelpCommand class lists out all the
 * available commands for the user.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class HelpCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
    }
}
