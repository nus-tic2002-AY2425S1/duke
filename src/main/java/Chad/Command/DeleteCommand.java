package Chad.Command;

import Chad.Exception.ChadException;
import Chad.Storage.Storage;
import Chad.TaskList.Task;
import Chad.TaskList.TaskList;
import Chad.Ui.Ui;

public class DeleteCommand extends Command {
    private final int taskIndex;
    private Task taskToDelete;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTaskById(taskIndex);
        tasks.deleteTask(taskIndex);
        ui.showDeleteTask(task, tasks.getNoOfTask());
        try {
            storage.save(tasks.toString());
        } catch (ChadException e) {
            //do sth here
        }

    }

}