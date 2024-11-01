package Chad.Command;

import Chad.Exception.ChadException;
import Chad.Storage.Storage;
import Chad.TaskList.TaskList;
import Chad.Ui.Ui;

public class UnmarkTask extends Command {
    private int taskIndex; // The index of the task to unmark

    public UnmarkTask(int taskIndex) {
        this.taskIndex = taskIndex; // Capture the index from the input
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChadException {
        if (taskIndex < 0 || taskIndex >= tasks.getNoOfTask()) {
            throw new ChadException("Invalid task index: " + (taskIndex + 1));
        }
        tasks.unmarkTask(taskIndex); // Unmark the specified task
        ui.showDeleteTask(tasks.getTaskById(taskIndex),tasks.getNoOfTask()); // Show the result
        storage.save(tasks.toString()); // Optionally save changes to the storage if required
    }
}