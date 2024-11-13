package alice.command;

import alice.exception.NoArgsException;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

import java.io.IOException;

/**
 * <h1>Unknown Command</h1>
 * The UnknownCommand class allows the program to
 * display an error when the program doesn't recognise it.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class UnknownCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoArgsException {
        ui.showError("Please put an instruction I can understand :(");
    }
}
