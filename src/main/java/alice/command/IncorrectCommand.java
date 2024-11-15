package alice.command;

import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

import java.io.IOException;

/**
 * <h1>Incorrect Command</h1>
 * The IncorrectCommand class deals with any exceptions that
 * have occurred when the user inputs the command.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class IncorrectCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Ui.showError("Incorrect number of args!");
    }
}
