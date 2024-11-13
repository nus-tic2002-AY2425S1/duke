package alice.command;

import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

import java.io.IOException;

/**
 * <h1>Exit Command</h1>
 * The ExitCommand class saves the ongoing data into txt file
 * and exits the application upon the user calling the command.
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class ExitCommand extends Command {

    /**
     * This method is set to true as this command will trigger the exit of the program.
     */
    public boolean isExit(){
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.writeToFile("tasks.txt", tasks.toString());
        ui.showEnding();
    }

}
