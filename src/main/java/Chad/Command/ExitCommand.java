package Chad.Command;

import Chad.TaskList.TaskList;
import Chad.Storage.Storage;
import Chad.Ui.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}