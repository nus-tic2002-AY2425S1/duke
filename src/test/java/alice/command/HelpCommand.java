package alice.command;

import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

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
