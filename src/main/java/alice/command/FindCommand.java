package alice.command;

import alice.exception.NoArgsException;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

import java.io.IOException;

/**
 * <h1>Find Command</h1>
 * The FindCommand class finds tasks that contain the search term(s)
 * <p>
 *
 * @author  Jarrel Bin
 * @version 1.0
 * @since   2024-11-02
 */
public class FindCommand extends Command{

    public FindCommand(String action, String instruction) {
        super(action,instruction);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, NoArgsException {
        assert !getInstruction().isEmpty() : "Search word(s) shouldn't be empty";
        tasks.printTasks(getInstruction());
    }

}
