package Chad.Command;

import Chad.Exception.ChadException;
import Chad.Storage.Storage;
import Chad.TaskList.Task;
import Chad.TaskList.TaskList;
import Chad.Ui.Ui;

public class AddCommand extends Command {
    private final Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }



    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChadException {
        tasks.addTask(taskToAdd);
        ui.showAddTask(taskToAdd, tasks.getNoOfTask());
        storage.save(tasks.toString());
    }
}

