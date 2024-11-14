package Chad.Command;

import Chad.Exception.ChadException;
import Chad.Storage.Storage;
import Chad.TaskList.TaskList;
import Chad.Ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChadException {
        ui.showTaskList(tasks); // UI method to display the task list
    }

}